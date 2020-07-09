# InnoDB事务原理

>[原文](https://zhuanlan.zhihu.com/p/48327345)
----------------------

## 概述

ACID ACD通过redoLog和UndoLog, 隔离性通过锁实现

* redo重做日志,保证事务原子性和持久性
* undo用来保证事务的一致性
  
  redo顺序写入,运行时不需要对redo文件进行读取操作,undo是随机读写的

  InnoDB事务隔离级别是可重复读的,事务隔离级别可以使用锁来实现,InnoDB在此技术上基于undolog MVCC实现了不同隔离级别

## 一 Redo log

1. 构成
   * 内存中重做日志缓冲
   * 重做日志文件
  
   目的是处于效率考虑
2. 恢复操作
   启动时不管上次数据运行是否正常关闭,都尝试进行数据恢复操作,每次从checkpoint点开始恢复日志部分
3. 重做日志没有使用O_DIRECT选项,要先写入文件系统缓存,再使用fsyns再写入磁盘,fsync效率影响事务提交性能;可以选择关闭fsync提高效率,但不会保证事务持久性
4. 与Binlog区别
   * binlog是数据库层面,redolog是InnoDB的
   * binlog存储逻辑日志,即修改的SQL,redo存储物理日志,即修改数据情况
   * binlog 只在事务时,一次写入,redo 在事务进行中不断写入
   * redo是的逻辑日志,恢复速度要快很多
   * binlog 二进制日志不是幂等的
  
5. redolog不需要双写:重写日志logblock和扇区一样都是512字节


## 二 Undo log

1. 两大功能

    a. 实现事务回滚 从undoLog 中逻辑记录读取相应记录并进行回滚

    b. 实现MVCC
        当某一行被事务锁定,可以通过log分析出该行记录以前的数据是什么,从而提供该行的版本信息,从来实现一致性非锁定读取

2. 与redoLog异同

    a. 存储逻辑日志 如delete对应insert
    b. undo日志会伴随redolog产生

2. 两种
   * insert undo log 事务提交之后会直接删除
   * update undo log

3. InnoDB为每一行隐藏三个字段以实现MVCC
   * 6字节事务ID 每处理一个事务,值+1
   * 7字节回滚指针 指向回滚段一条unlog一个记录
   * 隐藏ID

4. purge
   delete或update 并不会直接删除原来的数据
   * 删除操作只会将deleteflag设置1,记录仍在B+树中,随后purge操作删除完成
   * MVCC要求数据必须要保存记录之前的版本
   * undolog和history列表关系
     ![](https://raw.githubusercontent.com/xuke123/tuChuang/master/20200705093146.png)
     好处: 先从History list找到undolog 再从undoPage找undoLog 避免了大量随机读取操作,提高了purge效率
5. group commit
   * 事务提交过程
     + 修改内存中事务对应的信息,并且将日志写入重做日志缓冲
     + 调用fsync将确保日志都从重做日志缓冲写入磁盘 --> 多个合并提交groupcommit
  
  
6. 举例
   
   ### 1. 流程

   1. 初始数据行

    ![](https://raw.githubusercontent.com/xuke123/tuChuang/master/20200704211950.png)

   2. 事务1更改该行的各字段值
   
   ![](https://raw.githubusercontent.com/xuke123/tuChuang/master/20200704211832.png)

   ```
    a. 使用排他锁锁定该行
   b. 记录redoLog 
   c. 记录该行修改的copy值到undoLog
   d.修改当前行,填写事务编号,使回滚指针指向undo Log中修改前行
   ```
   1. 事务2修改该行的值

   ![再次修改](https://raw.githubusercontent.com/xuke123/tuChuang/master/20200704211411.png)
    
    同事务1, 此时undolog是两条记录 ,使用回滚指针连在一起

### 2.具体规则
* InnoDB的MVCC,通过上面每行记录后隐藏的列实现的,保存了行的创建时间和过期时间(创建时间),并不是具体时间,而是系统版本号.
* SELECT
* Innodb:只会查找版本早于当前事务版本数据行;行的删除版本
* Insert:为插入每一行保存当前系统的版本号作为行版本号
* DELETE:删除的每一行保存当前系统版本号作为行删除版本
* Update:Innodb插入一行新记录,保存当前系统版本号作为行版本号,同时保存当前系统版本号到原来行作为行删除标识
* 其他:

5. UndoLog删除策略

   purge线程 会查询那些比现在最古老活动事务还早的Undolog,并删除他们

## 事务控制语句
## 隐式提交的SQL语句
   TRUNCATE TABLE 是DDL 但它不支持回滚操作
## 事务统计
    TPS方法(com_commit+com_rollback)/time 隐式提交和回滚不会统计在内
## 分布式事务
   * 外部XA事务:
> 多个独立事务资源参与到一个全局的事务中,此时事务隔离级别必须是序列化读
> > 角色: 资源管理器(MySQL数据库),事务管理器(客户端),应用程序
> >　概述：两段式提交
> > 过程: 所有参与全局事务的节点都开始准备,第二阶段，事务管理器告诉资源管理器
ＲＯＬＬＢＡＣＫ还是　ＣＯＭＭＩＴ
   * 内部XA事务:
> 存储引擎与插件间或存储引擎之间,最常见binlog和InnoDB之间
  ![](https://raw.githubusercontent.com/xuke123/tuChuang/master/20200705100142.png)

## 不好的事务习惯
   * 不要在一个循环中反复提交操作,隐式和显示都不好,会不断提交重做日志
   * 不要使用自动提交,不同语言处理自动提交策略不同,最好显示指明
   * 不要使用自动回滚,这样可以得治错误的具体原因
## 长事务
   大事务分成小批量事务完成

