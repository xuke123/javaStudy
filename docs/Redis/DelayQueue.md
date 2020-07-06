# 队列

## [参考](https://blog.csdn.net/ruanhao1203/article/details/88679900?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522159399906319195162558019%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=159399906319195162558019&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~first_rank_ecpm_v3~rank_ctr_v4-6-88679900.ecpm_v1_rank_ctr_v4&utm_term=redis+%E5%BB%B6%E6%97%B6%E9%98%9F%E5%88%97)

异步消息队列

* Redis中的 list（列表）实现异步消息队列，使用rpush / lpush 操作插入队列消息，使用 lpop 和  rpop 来出队消息。
* 使用命令 blpop、brpop，b（blocking，阻塞）
* 空闲自动断开注意捕获异常
* 锁冲突处理

  1. 直接抛出异常,通知用户稍后重试
  1. sleep一会再重试
  1. 将请求转移至延时队列,过一会再试

延迟队列实现

* 延迟队列可以使用 zset（有序列表）实现，我们将消息序列化成一个字符串作为列表的value，这个消息的到期处理时间作为score，然后多个线程轮询zset 获取到期的任务进行执行，多线程保证了可靠性，因为多个线程，需要考虑并发执行的问题，一个任务不能被多次执行
