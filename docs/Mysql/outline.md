1. MySQL实例是单进程多线程架构的数据库

   找对应线程 `ps -ef|grep mysqld`

2. 找配置文件

   `mysql --help|grep my.cnf`

3. 存储引擎是基于表的,而不是数据库的
4. InnoDB进程
   * Master Thread
   > 将缓冲池中数据异步刷新到磁盘,脏页刷新,合并插入缓存,UNDO页回收等
   * IO Thread
   > 主要负责AIO异步请求回调处理
   * Purge Thread
   > 回收使用并分配的Undo页
   * Page Cleaner Thread
   > 刷新脏页到磁盘,减轻master线程负担
5. 内存
    ### [缓存池](https://juejin.im/post/5e240847f265da3e1824c579)
  
     ![缓存池数据](https://raw.githubusercontent.com/xuke123/tuChuang/master/20200704231508.png)
   * 可多实例,checkpoint技术
   * 查看指令`SHOW ENGINE INNODB STATUS`
   * LRUList FreeList FlushList
     ![LRU算法](https://raw.githubusercontent.com/xuke123/tuChuang/master/20200704232734.png)
     - 原因: 索引或者数据扫描读出数据只读一次,可能将热点数据刷出缓存
   * 重做日志缓存,便于随后刷新重做日志文件中
6. checkpoint技术
   * 作用
     * 缩短数据库恢复时间
        - 数据库宕机,数据库不需要重做所有日志,只需将chekpoint之后重做日志恢复就好
     * 缓存池不够用时,将脏页刷新到磁盘
       - LRU溢出脏页,强制执行checkpoint,将脏页刷回磁盘
     * 重做日志不可用,刷新脏页
       - 重做日志是重复使用,如果重做日志中有脏页,这部分也
  触发checkpoint
    * 分类
        - Sharp Checkpoint 数据库关闭时将所有脏页刷回磁盘中
        - Fuzzy Checkpoint 部分刷入磁盘
          + Master Thread Checkpoint 每秒或者每十秒刷新到内存中
          + FLUSH_LRU_LIST Checkpoint LRU缓存必须确保一定的空闲空间,刷出脏页时,就触发checkpoint
          + Async/Sync Flush Checkpoint 重做日志不可用时,需要强制将一些页面的刷新到磁盘
          + Dirty Page too much 脏页过多
7. Master Thread
    
     