# 迁移

--------------------

## [表迁移](https://blog.csdn.net/piaoruiqing/article/details/102769188)

1. 业务层迁移

   在业务层进行硬编码, 数据双写, 以某个时间点进行划分, 新产生的数据同时写入新表, 运行一段时间后将旧数据迁移至新表. 成本极高, 与业务耦合严重, 不考虑.

2. 连接层
3. 触发器
4. 数据库日志

    从某一时间点T备份数据库, 将备份库的数据迁移至新表, 从时间点T读取日志, 恢复到新表, 并持续写入.

5. 伪装成从库
   
   方案5较好,成熟开源中间件[Canal](https://github.com/alibaba/canal)