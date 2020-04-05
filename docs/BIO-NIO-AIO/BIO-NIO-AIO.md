# 概述
-----------------------
Java 中的 BIO、NIO和 AIO 理解为是 Java 语言对操作系统的各种 IO 模型的封装。程序员在使用这些 API 的时候，不需要关心操作系统层面的知识，也不需要根据不同操作系统编写不同的代码。只需要使用Java的API就可以了。
同步与异步,拥塞与非拥塞

### 传统IO 与伪异步IO

### NIO与BIO区别联系
```
 1. 非拥塞IO
 2. buffer 缓存区
 3. channel通道
 4. selector 选择器
```
### NIO读写数据方式
  从channel开始 都会创建缓存区
### NIO核心组件
  channel 通道
  buffer缓存区
  selector选择器
### 原生NIO主要缺点
  底层为epoll 有bug;维护成本较高
 所以出现netty
 ### AIO
  
  
