# 事务注解

## 属性

- 事务传播行为(propagation)
- 事务隔离级别(isolation)
- 读写事务属性(readOnly)
- 超时时间(timeout)
- 回滚设置
  - rollbackFor
  - rollbackForClassName
  - noRollbackFor
  - noRollbackForClassName

## 事务传播行为

[这篇文章挺棒的](https://www.jianshu.com/p/760399781b78)

事务传播行为,用来描述由某个事务传播行为修饰的方法被嵌套进另一个方法时事务如何传播

- PROPAGATION_REQUIRED: 没有事务,则新创建一个事务;如果存在一个事务,就加入到这个事务中.
  - 外围方法未开启事务时,内部方法开启自己事务时,双方不受影响
  - 外围方法开启事务时,内部方法开启事务时,所有内部事务都会回滚
- PROPAGATION_SUPPORTS: 如果 Context 存在事务，则将代码加入到 Context 的事务中执行，如果 Context 中没有事务，则使用 非事务 的方式执行。
- PROPAGATION_MANDATORY:使用当前事务,如果当前没有事务,则抛出异常
  -强制性要求事务。该级别的特性是，当要以事务的方式执行代码时，要求 Context 中必须已经存在事务，否则就会抛出异常！使用 MANDATORY 强制事务，可以有效地控制 “必须以事务执行的代码，却忘记给它加上事务控制” 这种情况的发生。举个简单的例子：有一个方法，对这个方法的要求是一旦被调用，该方法就必须包含在事务中才能正常执行，那么这个方法就适合设置为 PROPAGATION_MANDATORY 强制事务传播行为，从而在代码层面加以控制。

- PROPAGATION_REQUIRES_NEW:新建一个事务,如果当前存在事务,就把当前事务挂起
  - 外围没有事务,两个事务独立运行,之间互不影响
  - 内部方法依旧单独开启事务,与外部事务相互独立,互不影响,只会回滚单前一个事务的语句
- PROPAGATION_NESTED:若当前存在事务,就在嵌套事务内执行;如果当前没有事务,就执行PROPAGATION_REQUIRED相类似的
  - 外围无事务,修饰内部方法会开启自己的事务,且开启事务相互独立,互不干扰
  - 外围方法开启事务的情况下Propagation.NESTED修饰的内部方法属于外部事务的子事务，外围主事务回滚，子事务一定回滚，而内部子事务可以单独回滚而不影响外围主事务和其他子事务
- PROPAGATION_NEVER 以事务方式执行,如果当前存在事务,就抛出异常
  - 对事务要求更严格，不能出现事务！该级别的特点是，设置了该级别的代码，在执行前一旦发现 Context 中有事务存在，就会抛出 Runtime 异常，强制停止执行，有我无他！
- PROPAGATION_NOT_SUPPORT:以非事务方式执行,如果当前存在事务,就把当前事务挂起
  - 不支持事务。该级别的特点是，如果发现当前 Context 中有事务存在，则挂起该事务，然后执行逻辑代码，执行完毕后，恢复先前挂起的 Context 事务。这个传播行为的事务，可以缩小事务处理过程的范围。举个简单例子，在一个事务中，需要调用一段非核心业务的逻辑操作 1000 次，如果将这段逻辑放在事务中，会导致该事务的范围变大、生命周期变长，为了避免因事务范围扩大、周期变长而引发一些的事先没有考虑到的异常情况发生，可以将这段逻辑设置为 NOT_SUPPORTED 不支持事务传播行为。

父事务会在子事务进入之前创建一个 save point；
子事务 rollback ，父事务只会回滚到 save point，而不会回滚整个父事务；
父事务 commit 之前，必须先 commit 子事务。

## 只读事务

只读事务不会修改任何数据,资源管理器可在此基础做一些优化措施,提高运行性能

## 事务失效情景

@Transactional 可作用在接口,类,类方法上

- 作用在非public方法上
- 事务传播行为属性设置错误

   1. PROPAGATION_SUPPORTS  如果当前存在事务,则加入到该事务;没有事务以非实物方式运行
   2. NOT_SUPPORTED 以非事务方式运行,如果当前存在事务,则把当前事务挂起
   3. PROPAGATION_NEVER 以非事务方式运行,如果当前存在事务,则抛出异常

- @Transactional 注解属性 rollbackFor 设置错误
  
  Spring 默认只有在unchecked(继承自RuntimeException的异常)或Error回滚事务

  ![java异常与Spring默认回滚异常](https://pic3.zhimg.com/80/v2-f12130716651131ba42e34a78d976a2c_720w.jpg)
  
- 同一个类中方法调用，导致@Transactional失效????

  开发中避免不了会对同一个类里面的方法调用，比如有一个类Test，它的一个方法A，A再调用本类的方法B（不论方法B是用public还是private修饰），但方法A没有声明注解事务，而B方法有。则外部调用方法A之后，方法B的事务是不会起作用的。这也是经常犯错误的一个地方。

  那为啥会出现这种情况？其实这还是由于使用Spring AOP代理造成的，因为只有当事务方法被当前类以外的代码调用时，才会由Spring生成的代理对象来管理。

  ```java
  //@Transactional
    @GetMapping("/test")
    private Integer A() throws Exception {
        CityInfoDict cityInfoDict = new CityInfoDict();
        cityInfoDict.setCityName("2");
        /**
         * B 插入字段为 3的数据
         */
        this.insertB();
        /**
         * A 插入字段为 2的数据
         */
        int insert = cityInfoDictMapper.insert(cityInfoDict);

        return insert;
    }

    @Transactional()
    public Integer insertB() throws Exception {
        CityInfoDict cityInfoDict = new CityInfoDict();
        cityInfoDict.setCityName("3");
        cityInfoDict.setParentCityId(3);

        return cityInfoDictMapper.insert(cityInfoDict);
    }
  ```

- 异常被你的 catch“吃了”导致@Transactional失效

- 数据库引擎不支持事务

### 针对同一个类中事务失效的处理办法

- 类或方法上 加上@Transactional注解
- 编程式事务
- 重构走类外调用
- AopContext.currentProxy\
  ((Service)AopContext.currentProxy()).B()
- 依赖自身
- Spring getBean调用这个方法
- 只要通过this调用问题就不大

## 参考资料

- [Spring事务失效场景](https://zhuanlan.zhihu.com/p/145897825)