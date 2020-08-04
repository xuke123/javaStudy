# ThreadLocal原理


![概述图](https://img-blog.csdnimg.cn/20190829172740246.png)

- 每个线程都有一个ThreadlocalMap对象
- map的key 是一个ThreadLocl变量,值为ThreadLocalMap.Entry变量, 里面value为具体值,而ThreadLocal使用WeakReference充当key值

- 开放地址法


## TODO

- [ ] 解决hash冲突的办法

## 参考资料

- [解决hash冲突办法](https://www.jianshu.com/p/4d3cb99d7580)
- [为啥要用开放地址法](https://www.ctolib.com/topics-128810.html)

