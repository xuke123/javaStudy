## 线上问题排查
  ![csdn](https://blog.csdn.net/weixin_45674354/article/details/103698638)

#### CPU负载高
1. top找到对应进程
2. top -Hp 对应进程找到对应线程
3. 将线程pid 转化为16进程线程nid
> printf '%x\n' 66 
4. jstack 得到对应堆栈信息jstack pid|grep nid   
   a. 重点关心WAITING  TIMED_WAITING BLOCKED线程

   b. 使用cat jstack.log | grep "java.lang.Thread.State" | sort -nr | uniq -c 得到整体把握也可
5. 观察GC频率
   jstack -gc pid 1000
6. 上下文切换次数 
   全局:vmstat
   特定:pidstat -w pid

#### 磁盘
1. 查看文件系统状态 df -hl
2. 查看具体 iostat -dkx 1 nvme0n1
3. iotop 定位文件读写来源   
   a. sudo iotop(ubuntu需要sudo)    
   b. 可以使用readlink -f 定位到具体进程
   c. cat /proc/pid/io 查看具体进程情况的
   b. 还可以用lsof -p pid 查看具体线程读写情况

#### 内存
 主要是OOM GC问题,堆外内存
1. 先用free确认一下是否为内存问题
2. OOM问题
   a



