# 优化

1. explain
   * id
    `id相同,执行顺序从上到下;id不同,大的优先被执行;id为null时表示一个结果集`
   * select_type
     ![图片](https://raw.githubusercontent.com/xuke123/tuChuang/master/20200706214527.png)
   * table 查询的数据表
   * **type(非常重要，可以看到有没有走索引) 访问类型**
         1. ALL 扫描全表数据
         1. index 遍历索引
         1. range 索引范围查找
         1. index_subquery 在子查询中使用 ref
         1. unique_subquery 在子查询中使用 eq_ref
         1. ref_or_null 对Null进行索引的优化的 ref
         1. fulltext 使用全文索引
         1. ref 使用非唯一索引查找数据
         1. eq_ref 在join查询中使用PRIMARY KEYorUNIQUE NOT NULL索引关联 
   * possible_keys 可能使用的索引
   * key 显示MySQL在查询中实际使用的索引，若没有使用索引，显示为NULL
   * key_length 索引长度
   * extra
       1. Using index 使用覆盖索引
       2. Using where 使用了用where子句来过滤结果集
       3. Using filesort 使用文件排序，使用非索引列进行排序时出现，非常消耗性能，尽量优化。
       4. Using temporary 使用了临时表 sql优化的目标可以参考阿里开发手册

           【推荐】SQL性能优化的目标：至少要达到 range 级别，要求是ref级别，如果可以是consts最好。 
           说明： 
            1） consts 单表中最多只有一个匹配行（主键或者唯一索引），在优化阶段即可读取到数据。 
            2） ref 指的是使用普通的索引（normal index）。 
            3） range 对索引进行范围检索。 
            反例：explain表的结果，type=index，索引物理文件全扫描，速度非常慢，这个index级别比较range还低，与全表扫描是小巫见大巫。
2. 大表查询优化

   * 优化schema,sql语句+索引
   * 加二级缓存,memcached redis
   * 主从复制,读写分离
   * 垂直拆分
   * 水平拆分

3. 超大分页处理

   * select * from table where age > 20 limit 1000000,10 转化成
      1. select * from table where id in (select id from table where age > 20 limit 1000000,10)
      2. 连续id:select * from table where id > 1000000 limit 10
   * 不要写这样需求
   * 也可使用缓存
  
4. 主键

   * 主键是数据库确保数据行在整张表唯一性的保障,设置主键之后,增删该查可能更加的快速和范围安全
   * 为啥使用自增主键而不是UUID

5. 字段不能null

   null值占用更多字节,会在程序中造成很多预期不符的情况

6. 尽量使用char而不是varchar
7. 优化查询过程中数据访问
   TODO

   [ ] mysql查询过程
   [ ] select * 和select id

   * 访问数据过多导致查询性能下降
     1. 确定应用程序是否检索了大量自己不需要数据,可能是太多行或者列
     2. Mysql是否在分析大量不必要数据行
     3. 总是返回所有列,避免使用select *
     4. 重复查询相同数据,使用缓存
     5. 是否扫描了额外的记录
     6. 尽量使用覆盖索引
     7. 改变数据库和表结构
     8. 重写SQL,让优化器以更优的方式执行查询

8. 优化长难的查询语句
9. 优化特定类型查询语句
    * count(*)会忽略所有列直接统计所有行数,不要使用count(列名)
    * MyISAM 没有where条件 count(*)非常快
    * 有where条件时,MyISAMcount统计不见得比其他快
    * 可用使用explain 查询近似值,代替count(*)
    * 增加汇总表
    * 使用缓存
  
10. 优化关联查询
11. 优化子查询
12. 优化limit分页
    * Limit偏移量大时,查询效率较低
    * 可以记录上次查询的id,从下次查询时直接根据id来查询
13. 优化UNION查询
14. 优化WHERE子句

    * 对查询进行优化,应避免全表扫描,首先应考虑在where及order by涉及列上建立索引
    * 应避免正在where子句对字段进行null判定,否则会放弃索引走全表扫描
    * 应该避免在where上句上使用!= <> 否则引擎会放弃索引走全表扫描
    * 避免使用or做连接条件 会放弃索引
    * in 和 not in 慎用,避免全表扫描
    * select id from t where name like '%李%' 全表扫描,可以考虑使用全文检索
    * where子句使用参数也会全表扫描
    * 避免在where子句对字段进行表达式操作
    * 避免在where子句上对字段进行函数操作
    * 不在where子句上=左边进行函数,算术运算或表达式运算


Delete truncate drop区别和联系

![区别](https://raw.githubusercontent.com/xuke123/tuChuang/master/20200709222953.png)


区别及应用场景

　　　　in 和 exists的区别: 如果子查询得出的结果集记录较少，主查询中的表较大且又有索引时应该用in, 反之如果外层的主查询记录较少，子查询中的表大，又有索引时使用exists。其实我们区分in和exists主要是造成了驱动顺序的改变(这是性能变化的关键)，如果是exists，那么以外层表为驱动表，先被访问，如果是IN，那么先执行子查询，所以我们会以驱动表的快速返回为目标，那么就会考虑到索引及结果集的关系了 ，另外IN时不对NULL进行处理。

　　　　in 是把外表和内表作hash 连接，而exists是对外表作loop循环，每次loop循环再对内表进行查询。一直以来认为exists比in效率高的说法是不准确的。

not in 和not exists

　　　　如果查询语句使用了not in 那么内外表都进行全表扫描，没有用到索引；而not extsts 的子查询依然能用到表上的索引。所以无论那个表大，用not exists都比not in要快