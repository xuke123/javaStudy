# 异常

概述

![概述](https://raw.githubusercontent.com/xuke123/tuChuang/master/20200708094243.png)

上图有错误,类型转化错误属于运行时异常
IO异常不属于运行时异常

* Throwable 所有异常和错误的基类
* Error(错误)  Virtual MachineError(OOM SOE) NoClassDefFoundError(类定义错误)
* Exception(异常) 分运行时异常(编译器不会检查)和编译时异常(ClassNotFoundException,IOException 不检查编译器过不去)

受检异常和非受检异常

* 受检异常: 除 RuntimeException 及其子类外，其他的 Exception 异常都属于受检异常 编译器提示需要捕获的
* 非受检异常:该类异常包括运行时异常（RuntimeException极其子类）和错误（Error）

常见异常处理方式

* 直接抛出异常
* 封装异常再抛出
* 捕获异常
* 自定义异常
* try-catch-finally
* try-with-resource

JVM处理异常办法

>在一个方法中如果发生异常，这个方法会创建一个异常对象，并转交给 JVM，该异常对象包含异常名称，异常描述以及异常发生时应用程序的状态。创建异常对象并转交给 JVM 的过程称为抛出异常。可能有一系列的方法调用，最终才进入抛出异常的方法，这一系列方法调用的有序列表叫做调用栈。
>
>JVM 会顺着调用栈去查找看是否有可以处理异常的代码，如果有，则调用异常处理代码。当 JVM 发现可以处理异常的代码时，会把发生的异常传递给它。***如果 JVM 没有找到可以处理该异常的代码块，JVM 就会将该异常转交给默认的异常处理器（默认处理器为 JVM 的一部分），默认异常处理器打印出异常信息并终止应用程序。***

NoClassDefFoundError 和ClassNotFoundException 区别

>NoClassDefFoundError 是一个 Error 类型的异常，是由 JVM 引起的，不应该尝试捕获这个异常。
>
>引起该异常的原因是 JVM 或 ClassLoader 尝试加载某类时在内存中找不到该类的定义，该动作发生在运行期间，即编译时该类存在，但是在运行时却找不到了，可能是变异后被删除了等原因导致；
>
>ClassNotFoundException 是一个受查异常，需要显式地使用 try-catch 对其进行捕获和处理，或在方法签名中用 throws 关键字进行声明。当使用 Class.forName, ClassLoader.loadClass 或 ClassLoader.findSystemClass 动态加载类到内存的时候，通过传入的类路径参数没有找到该类，就会抛出该异常；另一种抛出该异常的可能原因是某个类已经由一个类加载器加载至内存中，另一个加载器又尝试去加载它。

异常实践

* 使用finally代码块
* 优先 明确的异常
* 对异常进行文档说明
  > 注解@throws
* 使用描述性消息描述异常
* 优先捕获最具体的异常
* 不要捕获Throwable异常
* 不要忽略异常
* 不要记录并抛出异常
* 包装异常时,不要抛弃原始的异常
* 不要异常控制程序的流程
* 使用标准异常,尽量不要自己定制
* 异常会影响性能
  > 仅在异常情况下使用异常;在可恢复的异常情况下,使用异常
