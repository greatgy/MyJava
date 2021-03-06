####mysql事务隔离级别

	read-uncommitted(读未提交)会有脏读问题，即A事务修改了数据未提交，B事务读到了，A再次回滚则B读到的为脏数据
	read-committed(读已提交)解决脏读问题，但是A事务内在B事务提交前后两次查询结果不一致	
	repeatable-read(可重复读  侧重于修改) 通过锁住行实现 一个事务读未提交时 不允许另一个事务写操作， 存在幻读问题即一个事务读取范围内数据时，另一个事务增加或删除一行数据，会出现数据行不一致	
	serializable(串行化  侧重于增加和删除)解决幻读问题  通过锁住表实现
	
innodb存储引擎默认的隔离级别是Repeated Read
		
		读取的数据都是其他事务已提交的数据
		事务内，相同的连续读得到的是相同的结果
		不会出现insert幻读现象
		
####分库分表

	水平拆分：就是将一个表的数据分散到多个表，表结构都是一样的
	垂直拆分：将很多字段的表拆分到多个表上，每个表结构不一样包含部分字段
	range：按照时间拆分，每个时间段一个表，容易产生热点数据最新的数据访问量最高，扩容容易
	hash：某个字段hash一下均匀分散，数据量和请求压力均匀，扩容麻烦需要迁移数据
####mysql优化

	逻辑代码优化 减少数据库访问   
	频繁查询的使用redis等缓存 
	
	索引优化：
	模糊查询双%会索引失效
	负向查询不会命中索引
        列上有计算不会命中索引
	前导模糊查询不会命中索引
	强制类型转化不会命中索引
	索引列not null，索引列不会存null值 当is not null时不会包含null值行
	避免select * ，减少传输量
	可以使用连接代替子查询，避免在内存中产生临时表

## sql语句
	
##### in和exists
	in 适用于子查询表小，外层表大的情况（select * from A where cc in (select cc from B)）
	exists 适用于外表小而内表大的情况（select * from A where exists(select cc from B where cc=A.cc)）
	内外表大小相当是in和exists效率相差不大
	
	not in会全表扫描     not exists的子查询会使用到索引
	not exists效率高于not in
	
##### explain
	type值：
		const:使用唯一索引或者主键等值查询
		ref:使用二级索引等值查询
		range:索引范围查询
		index：索引全表扫描，分组、排序等行为
		all: 数据全表扫描，效率最低
		
		
#### 存储引擎
	INNODB:支持事务、行锁、外键  主键索引是聚集索引，叶子节点存储数据（主键索引文件即是数据文件）
	MYISAM:不支持事务、行锁、外键  非聚集索引，叶子节点存储的是数据地址（一级索引、二级索引无区别）   
		记录表的行数，无条件count(*)时直接返回，有条件时和innodb引擎一样需要按行扫描

#### 事务属性
	A(atomic):原子性  事务内的一组操作要么全部成功，要么全部失败  （通过undo.log实现）
	C(consistency):一致性 数据库总是从一个一致性状态到另一个一致性状态
	I(isolation):隔离性 一个事务的修改在最终提交前对其他事务是不可见的 （通过mvvc实现）
	D(duration):持久性  事务提交对数据的改变持久性的（通过redo.log实现, 对数据的修改会记录在redo.log 事务提交的时候通过redo.log刷盘，宕机可以从redo.log恢复）
	s 通过undo日志保证原子性，redo日志保证持久性，可重复读的隔离级别保证隔离性和数据一致性
	索引列not null，索引列不会存null值 当is not null时不会包含null值行
	索引列not null，索引列不会存null值 当is not null时不会包含null值行
