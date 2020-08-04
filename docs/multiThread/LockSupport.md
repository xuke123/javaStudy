# LockSupport

## 作用

挂起和唤醒线程,创建锁和其他同步类的基础\
每个线程都会关联一个许可证,默认没有
unsafe类实现的

## 几个方法

### park()

* park() 拿到许可时,方法直接返回,否则,禁止参与线程调度,拥塞挂起
* 调用interrupt()方法,阻塞线程可能会被虚假唤醒,调用park 最好在循环条件判断方法中
* park被中断时不会抛出interruptedException

### unpark(Thread thread)

### void parkNanos(long nanos)
