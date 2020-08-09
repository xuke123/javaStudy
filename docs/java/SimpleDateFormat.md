# SimpleDateFormat

## 线程安全问题

全局变量:sdf,起几个线程sdf.parse 对应字符串,如果没有解析好,会出现NumberFormatException

原因: SimpleDateFormat维护一个Calendar对象,这个对象不是线程安全的,这个对象Calendar一些字段没有做线程安全保护

## 解决线程安全问题

1. 每次使用都new SimpleDateFormat
2. sdf.parse时 加锁
3. 使用ThreadLocal 每个线程都只用一个

   ```java
   static ThreadLocal<DateFormat> safeSdf=new ThreadLocal<DateFormat>(){
       @Override
       protected SimpleDateFormat initialValue(){
           return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       }
   }
   ```

4. 使用java8 LocalDate LocalTime LocalDateTime 

- 和 SimpleDateFormat 相比，DateTimeFormatter 是线程安全
- Instant 的精确度更高，可以精确到纳秒级。
- Duration 可以便捷得到时间段内的天数、小时数等。
- LocalDateTime 能够快速地获取年、月、日、下一月等。
- TemporalAdjusters 类中包含许多常用的静态方法，避免自己编写工具类。

## 参考资料

- [Java8 新特性LocalDate 线程安全的时间处理类](https://blog.csdn.net/wusd1256/article/details/102934354)
- [为什么不建议使用Date](https://www.cnblogs.com/wupeixuan/p/11511915.html)
