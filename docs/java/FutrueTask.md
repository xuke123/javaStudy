# FutrueTask

线程池拒绝策略为DiscardPolicy或DiscardOldestPolicy时,在FutrueTask对象上调用get()将导致线程一直拥塞,尽量使用带超时参数get方法

Future有状态\
可能是以下几种状态: NEW COMPLETING NORMAL EXCEPTIONAL CANCELLED INTERRUPTING INTERRUPTED

