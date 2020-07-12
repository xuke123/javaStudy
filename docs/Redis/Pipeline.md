# 管道

## Redis事务与pipeline功能区别

* pipeline选择客户端缓冲，multi选择服务端缓冲
* 请求次数的不一致，multi需要每个命令都发送一次给服务端，pipeline最后一次性发送给服务端，请求次数相对于multi减少

