# TreadLocal

## 应用

1. 线程间数据隔离
2. Spring采用ThreadLocal保证单个线程数据操作使用的是同一个数据库连接,业务层使用事务不需要感知并管理connection对象,管理多个事务配置间的切换,挂起和的恢复\
注:Spring的事务主要是ThreadLocal和AOP去做实现的
3. 解决SimpleDataFormat 线程安全问题(内部使用Calendar)\
   线程池加上ThreadLocal包装SimpleDataFormat，再调用initialValue让每个线程有一个SimpleDataFormat的副本，从而解决了线程安全的问题，也提高了性能。
4. 上下文信息处理
5. cookie，session等数据隔离都是通过ThreadLocal去做实现的

## 底层原理

![内部结构](https://imgconvert.csdnimg.cn/aHR0cHM6Ly90dmExLnNpbmFpbWcuY24vbGFyZ2UvMDA3UzhaSWxseTFnaDRmeTZndncwajMwdzAwOTNqc3UuanBn?x-oss-process=image/format,png)

- Entry是继承WeakReference（弱引用).
- 使用开放地址法
- 一个线程可能存在多个ThreadLocal

## 存储位置

在Java中，栈内存归属于单个线程，每个线程都会有一个栈内存，其存储的变量只能在其所属线程中可见，即栈内存可以理解成线程的私有内存，而堆内存中的对象对所有线程可见，堆内存中的对象可以被所有线程访问。

但Threadlocal 位于堆上，只是通过一些技巧将可见性修改成了线程可见。

## 线程共享

InheritableThreadLocal可以实现多个线程访问ThreadLocal的值

如果线程的inheritThreadLocals变量不为空，比如我们上面的例子，而且父线程的inheritThreadLocals也存在，那么我就把父线程的inheritThreadLocals给当前线程的inheritThreadLocals。

## 内存泄露

ThreadLocal在没有外部强引用时，发生GC时会被回收，如果创建ThreadLocal的线程一直持续运行，那么这个Entry对象中的value就有可能一直得不到回收，发生内存泄露。

就比如线程池里面的线程，线程都是复用的，那么之前的线程实例处理完之后，出于复用的目的线程依然存活，所以，ThreadLocal设定的value值被持有，导致内存泄露。

按照道理一个线程使用完，ThreadLocalMap是应该要被清空的，但是现在线程被复用了。

避免:remove的源码很简单，找到对应的值全部置空，这样在垃圾回收器回收的时候，会自动把他们回收掉。

## 参考资料

- [ThreadLocal面试资料](https://blog.csdn.net/qq_35190492/article/details/107599875)