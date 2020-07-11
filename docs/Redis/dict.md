# 字典

概述

![字典](https://raw.githubusercontent.com/xuke123/tuChuang/master/20200710231234.png)

rehash主要步骤:

1. 为ht[1]分配空间,让字典同时持有ht[0]和ht[1]两个hash表
2. 在字典维持一个索引计数器变量rehashidx,并将值设置为0,表示rehash工作开始
3. 在rehash进行过程中,每次除了增删改查外,还顺带将ht[0]对应rehashidx索引上键值对rehash到ht[1],当rehash工作完成后,程序将rehashidx属性值增一.
4. 所有字典操作不断执行,最终某个时间点,ht[0]值全部rehash到ht[1],rehashidx属性设置为-1,表示rehash操作完成