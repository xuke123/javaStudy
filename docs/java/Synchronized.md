# Synchronized

## 实现

同步块 使用monitorenter和monitorexit指令,同步方法则使用修饰符上ACC_SYNCHRONIZED完成,本质是对一个对象的监视器monitor获取,过程具有排他性

未获得对象监视器线程会拥塞在入口处,进入BLOCKED状态

![对象,监视器,同步队列和执行线程之间关系](../pic/并发/synchronized_1.png)