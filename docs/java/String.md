# String

## \+重载

- 反编译指令:javap -c one.class

## 为什么要用StringBuilder

平时用字符串直接相加作连接，也可以，但在循环内部最好还是用 StringBuilder 作字符串的连接，其实在循环次数较小的情况下我们也可以用 String 直接来相加连接，但有时我们根本不能确定循环次数的大小，所以最好还是老老实实的用 StringBuilder 。

## Spring SpringBuilder SpringBuffer区别



## 参考资料

- [1] [String对\+号重载](https://blog.csdn.net/Dextrad_ihacker/article/details/53055709)
- [2] [为什么要用StringBuilder](https://blog.csdn.net/shfqbluestone/article/details/34188325)
