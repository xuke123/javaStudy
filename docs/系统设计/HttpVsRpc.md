# 有http请求了,为什么还需要RPC

HTTP协议，以其中的Restful规范为代表，其优势很大。它可读性好，且可以得到防火墙的支持、跨语言的支持

但http首先是有用信息占比少，毕竟HTTP工作在第七层，包含了大量的HTTP头等信息。其次是效率低，还是因为第七层的缘故。还有，其可读性似乎没有必要，因为我们可以引入网关增加可读性。此外，使用HTTP协议调用远程方法比较复杂，要封装各种参数名和参数值。主要是效率低、封装调用复杂。当存在大量的服务间调用时，这些缺点变得更为突出。

![http与rpc之间关系](https://pic4.zhimg.com/80/v2-f79abd8e489337fafc7aafe75799b599_720w.jpg)


