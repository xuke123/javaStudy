# SynchronizedList

```java
    public void testArrayList(){

        //典型装饰模式,挺好的!!!!
        List<Object> objects = Collections.synchronizedList(new ArrayList<>());

        objects.size();
    }
```

实际相当于在每个接口机上Synchronized 关键字
