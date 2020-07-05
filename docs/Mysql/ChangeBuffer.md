## InsertBuffer

目的:二级索引一般不是顺序插入,如果直接插入,伴随大量随机IO,为了降低这部分随机IO,使用insertBuffer

条件:

* 索引是辅助索引
* 索引不是唯一的

过程: 在对辅助索引插入或更新操作时,首先判断页的索引页是否在缓存池中,若在,直接插入,否则,放入InsertBuffer中
然的在某个时机将InsertBuffer和辅助索引页合并

可能问题:

* 宕机时,大量insertBuffer导致恢复时间较长
* 占用缓存可能会比较大
* 索引不能是唯一原因,插入时需要判定插入是否唯一,InnoDB就必须读取对应的页

## changeBuffer
InsertBuffer升级版:DML操作,InsertBuffer,Delete Buffer,Purge Buffer

![概述](https://raw.githubusercontent.com/xuke123/tuChuang/master/20200705163013.png)

## 合并

* 辅助索引页被读到缓冲池时
* Insert Buffer Bitmap页被追踪到该辅助索引页无可用空间
* Master Thread
