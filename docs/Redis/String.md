# 动态字符串SDS

概述

* Redis 键值对,键和值是一个SDS
* SDS被用作缓冲区(buffer)  AOF缓冲区,以及客户端状态中输入缓冲区

结构
    ![定义](https://raw.githubusercontent.com/xuke123/tuChuang/master/20200706125001.png)

优点

概述

* 常数时间获得字符串长度
* 杜绝了缓存区溢出问题
* 减少修改字符创时带来内存重分配次数
  1. 空间预分配 (提前多分配空间)
  2. 惰性空间释放
* 二进制安全,存储二进制数据
* 兼容部分C字符串函数

![概述](https://raw.githubusercontent.com/xuke123/tuChuang/master/20200706130642.png)

