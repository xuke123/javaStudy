# Spring事务管理

Spring提供了底层事务源无关的事务抽象,声明性事务的功能,可以让程序从事务代码解放出来

JDBC对事务的支持

无保存点事务

![JDBC事务支持](https://raw.githubusercontent.com/xuke123/tuChuang/master/20200708225103.png)

带保存点事务

![带保存点JDBC事务支持](https://raw.githubusercontent.com/xuke123/tuChuang/master/20200708225214.png)

ThreadLocal基础知识
> Spring通过各种模板类降低了开发者使用各种数据持久化技术难度,这些模板类是线程安全的,模板类手段是ThreadLocal,InheritableThreadLocal继承于ThreadLocal,会自动为子线程复制一份

Spring直接使用底层数据源管理事务

Spring通过事务传播行为控制当前事务如何传播到被嵌套调用的目标服务接口方法中

![事务传播](https://raw.githubusercontent.com/xuke123/tuChuang/master/20200708232305.png)

# 实现事务控制的三种方式

编程式事务 代码中手动开启,手动提交,手动回滚

声明式事务 AOP配置事务,切入点可能出错

![声明式事务](https://raw.githubusercontent.com/xuke123/tuChuang/master/20200709191155.png)

注解事务 @Trainsactional
   Spring 默认回滚是捕获RuntimeException以及子类异常
   ![配置](https://raw.githubusercontent.com/xuke123/tuChuang/master/20200709095732.png)
   ![事务上参数](https://raw.githubusercontent.com/xuke123/tuChuang/master/20200709095847.png)
   @Trainsactional可以在接口定义和接口方法,类定义和类的public方法上,主要不要在接口上,注解默认不会被继承(继承的话,可听过XML)
   ![](https://raw.githubusercontent.com/xuke123/tuChuang/master/20200709100442.png)

不回滚的原因
（1）声明式事务配置切入点表达式写错了，没切中Service中的方法
（2）Service方法中，把异常给try catch了，但catch里面只是打印了异常信息，没有手动抛出RuntimeException异常
（3）Service方法中，抛出的异常不属于运行时异常（如IO异常），因为Spring默认情况下是捕获到运行时异常就回滚


Spring事务管理是基于接口代理或动态字节码技术
![代理类型](https://raw.githubusercontent.com/xuke123/tuChuang/master/20200709130829.png)

接口代理只能是public 或public final修饰符
cglib 扩展被增强类,动态创建其子类进行aop增强植入的, final static final 不能被子类覆盖,不能增强,但protected可以
不能被Spring进行AOP事务增强,是指这些方法不能启动事务,但是外层方法事务上下文依旧可以传播到这些方法中


