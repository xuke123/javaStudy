# InnoDB事务原理

>[原文](https://zhuanlan.zhihu.com/p/48327345)
----------------------

## 概述

ACID ACD通过redoLog和UndoLog, 隔离性通过锁实现

## 一 Redo log

1. 构成
   * 内存中重做日志缓冲
   * 重做日志文件
  
   目的是处于效率考虑
2. 恢复操作
   启动时不管上次数据运行是否正常关闭,都尝试进行数据恢复操作,每次从checkpoint点开始恢复日志部分

## 二 Undo log

1. 两大功能

    a. 实现事务回滚 从undoLog 中逻辑记录读取相应记录并进行回滚

    b. 实现MVCC
        当某一行被事务锁定,可以通过log分析出该行记录以前的数据是什么,从而提供该行的版本信息,从来实现一致性非锁定读取

2. 与redoLog异同

    a. 存储逻辑日志 如delete对应insert

3. InnoDB为每一行隐藏三个字段以实现MVCC
   * 6字节事务ID 每处理一个事务,值+1
   * 7字节回滚指针 指向回滚段一条unlog一个记录
   * 隐藏ID
  
4. 举例
   
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




