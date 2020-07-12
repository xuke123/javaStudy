# 慢查询日志

概述

1. redis慢查询日志功能用于记录执行时间超过指定时长的命令
2. 所有慢查询日志保存在服务器状态的slowlog链表中,每个链表节点包含slowlogentry结构
3. 打印和删除慢查询日志可遍历slowlog链表完成
4. slowlog长度就是慢查询日志数量
5. 新增会加在slowlog链表头,超过slowlog-max-len选项值,多打印的日志会被删除
