

    没有什么方案是完美的，先更新数据库，然后删除缓存，删除缓存时，把key放到消息队列，直到删除成功，或者订阅binlog去删除缓存，对于用户金额这样的数据，当下单的时候直接从数据库读取后校验，一切以数据库为准，此方案数据不一致的概率极低，既然没有完美的方案，这样就挺好了。

    mybatis 二级缓存