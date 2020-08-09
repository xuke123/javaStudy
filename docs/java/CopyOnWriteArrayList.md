# CopyOnWriteArrayList

## 主要思想

写时复制策略,底层是一个复制的数据快照写操作

## 应用场景

多读少写

每次更改时都会生成一个快照版本
迭代器 其实也是读时复制,直接在快照版本上操作

## 其他容器

CopyOnWriteArraySet

