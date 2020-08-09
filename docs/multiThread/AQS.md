# AQS概述

抽象队列同步器(抽象同步队列),一个FIFO双向队列,内部通过head和tail记录队首和队尾元素,元素类型是Node

## Node节点元素含义:

* thread变量用来存放进入AQS队列中线程;
* SHARED标识线程是获取共享变量被拥塞挂起的
* EXELUSIVE 标识线程是获取独占资源时被挂起后
* waitStatus:CANCELLED(线程被取消了) SIGNAL(线程需要被唤醒) CONDITION(线程在条件队列中等待) PROPAGATE(释放共享资源时需通知其他节点)
* pre:当前节点的前驱节点
* next:当前节点的后继节点

## 单一状态信息state

线程同步的关键其实就是对state进行操作,根据state是否属于一个线程,操作state方式分为独占方式和共享方式

* ReetrantLock 当前线程获取锁的可重入次数
* ReetrantReadWriteLock: state 高16位表示读状态,低16位标识的线程写锁的可重入次数
* semaphore:表示当前可用信号量的个数
* CountDownlatch:表示计数器当前的值

## ConditionObject 结合锁的实现线程同步

* ConditionObject可以访问AQS对象内部变量(state AQS队列等)
* CO是条件变量,每个条件变量对应一个条件队列(单向链表队列),用来存放调用条件变量的await方法后被拥塞的线程

