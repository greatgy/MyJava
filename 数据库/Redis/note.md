####redis基本数据类型

	string：普通的set和get，做简单的kv缓存
	hashmap:类似map的一种结构，可以将结构化的数据，比如一个对象缓存在redis  key=150 value={“id”: 150,“name”: “zhangsan”,“age”: 20}
	list:有序列表  key=某大v value=[zhangsan, lisi, wangwu]   （链表结构）
	set：去重集合
	sorted set:去重可根据score排序   （跳表结构）
####redis对key删除

	定期删除+惰性删除：定期随机抽取设置了过期时间的key，如已过期就删除 存在很多过期数据未被删掉 惰性删除即在查询时看是否过期，过期删除
	大量未即时查的过期数据使用内存淘汰机制  通常使用allkeys-lru删除最近最少使用的
####redis持久化方式

	AOF:对每条写入命令作为日志写入文件，redis重启时通过回放写入指令来构建数据集
	RDB:对数据进行周期性的持久化会生成多个文件，redis重启恢复的速度快但有可能丢失文件
info memory查看redis内存   keys * 查看所有key
