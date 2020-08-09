# ConcurrentHashMap

## 注意事项

* put() 方法判断如何key值存在,使用value值覆盖原值并返回原值(没有就返回null)
* putIfAbsent(K key,V value) 如果key值存在就返回原值,不做覆盖,不存在key值,放在value值 返回null
* 判断key值存在和放入是原子性操作
