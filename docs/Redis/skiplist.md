# 跳跃表

跳跃表优点: 效率可和平衡树相媲美,实现相对简单

基本结构

![跳跃表结构](https://raw.githubusercontent.com/xuke123/tuChuang/master/20200711143650.png)

* header 指向跳跃表的表头节点
* tail 指向跳跃表表尾节点
* level 层级最大那个节点数量
* length 跳跃表长度,目前包含的节点数量
* 层,Ln标识每个节点层
* 后退指针,BW字样标记为节点后退指针
* 分值
* 成员对象