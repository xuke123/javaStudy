# compareTo注意事项

1. 如果是升序算法,有两组算法可以是稳定的
   - <(-1) 与>=(1)
   - <(-1) >(1) =(0)
  
2. 如果降序算法
   - >(-1) <=(1) 稳定算法
   - >=(-1) <(1) 非稳定
   - >(-1) <(1) =(0) 稳定算法

3. jdk排序接口
   - java.lang.Comparable 重写了CompareTo()方法 
   - java.util.Comparator 重写compare(Object o1,Object o2)
   - Comparable和Comparator 比较
      1. CompareTo需要对象本身实现,Comparator的compare 接口耦合度比较低
      1. Comparable和一个具体类绑定在一起，使用比较固定；而Comparator使用比较灵活，可以被用于各个需要比较功能的类；
      1. 如果一个类需要进行多种比较排序，只能使用Comparator，而不能使用Comparable。
      1. 如果实现类没有实现Comparable接口，又想对两个类进行比较或者实现类实现了Comparable接口，但是对compareTo方法内的比较算法不满意，那么就可以实现Comparator接口，自定义一个比较器，写比较算法；

## 参考资料

- [Comparable和Comparator](https://blog.csdn.net/Mr_wxc/article/details/107328572)
