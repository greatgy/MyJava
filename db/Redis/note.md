reids

	服务端有个redisServer对象
	redisServer中有redisDb对象数组（多少个库就多少个对象）
	redisDb对象中有一个字典保存所有的key-value对
	一个字典保存key-过期时间对
		
	过期键删除策略：定期删除+惰性删除
		定期删除：间隔一段时间查看设置了过期时间的键是否过期，淘汰
		惰性删除：查询时，先判断是否键过期，淘汰
		大量未即时查的过期数据使用内存淘汰机制  通常使用allkeys-lru删除最近最少使用的
		
	同时开启aof和rdb时，服务器崩溃重启后优先加载aof文件（aof写入频率高，丢失概率小）
	
redis基本数据类型

	string：普通的set和get，做简单的kv缓存
	hashmap:类似map的一种结构，可以将结构化的数据，比如一个对象缓存在redis  key=150 value={“id”: 150,“name”: “zhangsan”,“age”: 20}
	list:有序列表  key=某大v value=[zhangsan, lisi, wangwu]   （链表结构）
	set：去重集合
	sorted set:去重可根据score排序   （跳表结构）

	info memory查看redis内存   keys * 查看所有key

RDB持久化方式

	后台一个子进程通过bgsave命令，生成rdb文件
	服务端可以配置多个条件比如60 100这样时间段内发生多少次写入 触发bgsave
	多个配置是在redisServer对象中有个数组类型参数
	生成Rdb文件时过期键不会保存，服务器载入rdb文件时过期键也不会载入

AOF方式

	每个事件循环结束后都会将写入命令写入aof缓冲区
	根据配置决定什么时刻同步到文件
	always：每次事件循环结束都会同步到磁盘（fsync命令同步到磁盘）
	everysec：每隔1秒同步到磁盘（默认值）
	no：不会主动同步到磁盘，有系统自己决定

	aof文件过大达到配置的大小时，回台bgrewriteaof子线程触发重写aof
	重写期间发生的写入命令会保存到aof重写缓冲区，当重写完成后将缓冲区部分写入到写的aof文件，并且用新的文件替换旧的文件

		
缓存一致性问题
	
	先改数据库再删缓存，放到一个事务中
