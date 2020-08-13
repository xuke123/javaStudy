# java深拷贝

## 基本方法

1. 笨的方法，set所有的基本类型字段
2. 实现Cloneable 接口

    重写clone(),拷贝时候调用这个clone()。
    如果某个属性是非基本类型，则同样需要实现Cloneable 接口并重写clone()，否则对象复制之后，复制对象仍然保持引用这个属性对象。

3. 实现Serializable 接口

    拷贝时调用`SerializationUtils.clone()`方法;

4. 使用Jackson序列化

    使用Jackson序列化成string，再反序列化成对象（但是需要提供无参的构造器）

## 参考资料

- [Java commons-lang3 库 SerializationUtils 序列化工具类](https://www.letianbiji.com/apache-commons/commons-lang3-serializationutils.html)
- [java深拷贝的几种方式](https://blog.csdn.net/weixin_44343376/article/details/106764308)
