
# [bigkeys](https://mp.weixin.qq.com/s?spm=a2c6h.12873639.0.0.753b1feeKkJiDx&__biz=Mzg2NTEyNzE0OA==&mid=2247483677&idx=1&sn=5c320b46f0e06ce9369a29909d62b401&chksm=ce5f9e9ef928178834021b6f9b939550ac400abae5c31e1933bafca2f16b23d028cc51813aec&scene=21#wechat_redirect)


- 1.内存空间不均匀：这样会不利于集群对内存的统一管理，存在丢失数据的隐患，下图中的三个节点是同属于一个集群，键值个数也接近，但内存容量相差较多。
- 2.超时阻塞：由于Redis单线程的特性，操作bigkey的通常比较耗时，也就意味着阻塞Redis可能性越大，这样会造成客户端阻塞或者引起故障切换，它们通常出现在慢查询中。
- 3.过期删除:有个bigkey，它安分守己（只执行简单的命令，例如hget、lpop、zscore等），但它设置了过期时间，当它过期后，会被删除，如果没有使用Redis 4.0的过期异步删除(lazyfree-lazy-expire yes)，就会存在阻塞Redis的可能性，而且这个过期删除不会从主节点的慢查询发现（因为这个删除不是客户端产生的，是内部循环事件，可以从latency命令中获取或者从slave节点慢查询发现）。
- 4 .迁移困难 当需要对bigkey进行迁移（例如Redis cluster的迁移slot），实际上是通过migrate命令来完成的，migrate实际上是通过dump + restore + del三个命令组合成原子命令完成，如果是bigkey，可能会使迁移失败，而且较慢的migrate会阻塞Redis。