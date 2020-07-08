# 特定查询优化

优化COUNT()查询

* count() 包含列和列的表达式,统计有值的结果值;count(*)会忽略实际列情况,直接统计行;
* 最好使用count(*),意义清晰,性能更好一点
* MyISAM不带where时,count(\*)比较快,如果列不为空,count(col)表达式会被优化为count(\*),带where那就不一定了
* SELECT SUM(IF(color='blue',1,0)) as blue,SUM(IF(color='red',1,0)) as red FROM items;
* SELECT COUNT(color='blue' OR NULL) AS blue,COUNT(color='red' Or NULL) As red FROM items;
* 使用近似值,explain, 或者更特殊的
* 更复杂优化,使用覆盖索引优化,汇总表,缓存等手段

优化关联查询

* 确保ON或USING上有索引, 关联顺序呢是B关联A,那么只需要A索引,索引多了,负担比较重
* GROUP by和order by只涉及一个表中列,mysql有可能使用索引优化这个过程
* 升级SQL需要注意,普通关联可能变成笛卡儿积,不同类型关联产生结果发生变化

优化子查询

* 5.6版本以前,尽可能使用关联查询代替子查询

优化GROUP BY和DISTINCT

* GROUP by 无法使用索引时,会使用临时表或文件排序来做分组
* 关联查询做分组时,并按照查询表中某个列做分组时,使用查找表标识列效率要高 被关联要好一点

优化LIMIT分页

优化SQL_CALC_FOUND_ROWS

优化UNION查询

静态查询分析

使用用户自定义变量