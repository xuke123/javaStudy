# binlog录入格式

statement 

>每一条会修改数据的sql都会记录在binlog中。不需要记录每一行的变化，减少了binlog日志量，节约了IO，提高性能。由于sql的执行是有上下文的，因此在保存的时候需要保存相关的信息，同时还有一些使用了函数之类的语句无法被记录复制

row

> 不记录sql语句上下文相关信息，仅保存哪条记录被修改。记录单元为每一行的改动，基本是可以全部记下来但是由于很多操作，会导致大量行的改动(比如alter table)，因此这种模式的文件保存的信息太多，日志量太大。

mixed
> 一种折中的方案，普通操作使用statement记录，当无法使用statement的时候使用row。


canal [kə'næl]，译意为水道/管道/沟渠，主要用途是基于 MySQL 数据库增量日志解析，提供增量数据订阅和消费

基于日志增量订阅和消费的业务包括

* 数据库镜像
* 数据库实时备份
* 索引构建和实时维护(拆分异构索引、倒排索引等)
* 业务 cache 刷新
* 带业务逻辑的增量数据处理

MySQL主备复制原理
![主从复制](https://raw.githubusercontent.com/xuke123/tuChuang/master/20200710125316.png)

* MySQL master 将数据变更写入二进制日志( binary log, 其中记录叫做二进制日志事件binary log events，可以通过 show binlog events 进行查看)
* MySQL slave 将 master 的 binary log events 拷贝到它的中继日志(relay log)
* MySQL slave 重放 relay log 中事件，将数据变更反映它自己的数据

canal 工作原理

* canal 模拟 MySQL slave 的交互协议，伪装自己为 MySQL slave ，向 MySQL master 发送dump 协议
* MySQL master 收到 dump 请求，开始推送 binary log 给 slave (即 canal )
* canal 解析 binary log 对象(原始为 byte 流)

4. 为什么使用canal?

(1). 更灵活的架构，多机房同步比较简单。

(2). 异构表之间也可以同步，同时，可以控制不同步DDL以免出现数据丢失和不一致。

(3). Canal可以实现一个表一线程，多个表多线程的同步，速度更快。同时会压缩简化要传输的binlog，减少网络压力。

(4). 双A机房同步. 目前mysql的M-M部署结构，不支持解决数据的一致性问题，基于canal的双向复制+一致性算法，可一定程度上解决这个问题，实现双A机房.




