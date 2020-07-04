# 双写缓冲技术
------

1. partial page write问题

   Innodb pageSize为16kb 数据校验以16kb计算,写内存以16kb为单位,操作系统写为4kb,单个页写不保证原子性,中途可能断电或者崩溃,存在部分页写问题,数据混乱,InnoDB无法处理这种错误

   **注意:redolog无法处理何种错误,恢复检查是page checksum,checksum是page最后事务号,部分写时候,page损坏,找不到正确事务号,无法恢复**

2. doublewrite buffer

   它是innoDB在tablespace上的128页(两个区)2M空间,MYSQL先回memcpy到内存doublewrite缓存中,随后每次写入1MB到共享表空间，然后马上调用fsync函数，同步到磁盘上，避免缓冲带来的问题,doublewrite页(2)是顺序写,开销不大,double write buffer(3)写入各表空间文件,是离散写.

    ![全流程](https://raw.githubusercontent.com/xuke123/tuChuang/master/20200704222958.png)

3. 缺点

   多写一次磁盘,存在一定性能损耗

4. 其他

   - 不一定需要double write,slave关闭,可以通过中继日志(space X电脑)
   - 采用其他文件系统有部分写失效防范机制