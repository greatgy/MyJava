####mysql事务隔离级别

	read-uncommitted(读未提交)会有脏读问题，即A事务修改了数据未提交，B事务读到了，A再次回滚则B读到的为脏数据
	read-committed(读已提交)解决脏读问题，但是A事务内在B事务提交前后两次查询结果不一致	
	repeatable-read(可重复读  侧重于修改) 通过锁住行实现 一个事务读未提交时 不允许另一个事务写操作， 存在幻读问题即一个事务读取范围内数据时，另一个事务增加或删除一行数据，会出现数据行不一致	
	serializable(串行化  侧重于增加和删除)解决幻读问题  通过锁住表实现
####分库分表

	水平拆分：就是将一个表的数据分散到多个表，表结构都是一样的
	垂直拆分：将很多字段的表拆分到多个表上，每个表结构不一样包含部分字段
	range：按照时间拆分，每个时间段一个表，容易产生热点数据最新的数据访问量最高，扩容容易
	hash：某个字段hash一下均匀分散，数据量和请求压力均匀，扩容麻烦需要迁移数据
####mysql优化

	逻辑代码优化 减少数据库访问   
	频繁查询的使用redis等缓存 
	表的字段使用合理的类型 varchar长度合理	
	在 where及order by涉及的列上建立索引
	优化sql避免索引失效
	避免select * ;
	避免在列上使用函数和计算
	避免使用！= 、not in等否定操作
	避免使用or连接条件
	避免数据类型不一致
	索引不会包含有Null值的列
	模糊查询双%会索引失效

## sql语句
	
##### in和exists
	in 适用于子查询表小，外层表大的情况（select * from A where cc in (select cc from B)）
	exists 适用于外表小而内表大的情况（select * from A where exists(select cc from B where cc=A.cc)）
	内外表大小相当是in和exists效率相差不大
	
	not in会全表扫描     not exists的子查询会使用到索引
	not exists效率高于not in