# 投票

相关指令:

- score
- sadd
- zincrby
- hincrby
- expire
- zadd
- zrevrange
- hgetall
- srem
- zinterstore

文章基本信息:hash

![文章信息存储](../pic/aticle_info.png)

发布时间排序的有序集合 zset

![文章时间排序](../pic/aticle_info_1.png)

评分排序的有序集合 zset

![文章分数排序](../pic/aticle_info_2.png)

文章投票用户集合 set

![单文章投票用户](../pic/aticle_info_3.png)

投票流程

![投票](../pic/aticle_info_4.png)

发布获取文章

![发布文章](../pic/aticle_info_5.png)

获取文章

![获取文章](../pic/aticle_info_6.png)

文章分组

![分组管理](../pic/aticle_info_7.png)

获取分组程序

![获取分组程序](../pic/aticle_info_8.png)

投反对票可以使用SMOVE