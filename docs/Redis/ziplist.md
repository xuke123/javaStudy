压缩列表

![压缩列表](https://raw.githubusercontent.com/xuke123/tuChuang/master/20200711150803.png)

zlbytes 整个压缩列表占用内存字节数,内存重分配或者计算zlend的位置时使用
zltail  计算压缩列表距离对压缩列表起始地址多少字节 ,这可以计算从表为节点地址
zllen 计算压缩列表节点数量
entryx 列表节点
zlend 表明压缩列表末尾

注意节点元素是固定大小的

![压缩列表节点](https://raw.githubusercontent.com/xuke123/tuChuang/master/20200711151337.png)

更新过程
![更新问题](https://raw.githubusercontent.com/xuke123/tuChuang/master/20200711151852.png)

连锁更新问题

问题描述:previous_entry_length长度可以用一个字节保存,也可以使用三个字节保存,当连续节点字节介于250~253字节时,会触发连环更新,但是概率比较低


处理,元素较少时,比较节省空间


字符串对象


