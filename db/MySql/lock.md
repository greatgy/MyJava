MVCC
		
    核心原理是：
    		 我们每行数据实际有两个隐藏的列 创建版本号和删除版本号，每创建一个新的事务版本号都会递增
		 Mvcc的查找原理是读取创建版本号小于等于当前版本号，并且删除版本号大于当前版本号或者为空 的数据
		 保证事务读取到的数据是事务开始时的数据或者自己修改的数据
    		（写操作时，将数据拷贝一份，版本号区分，写任务操作的是拷贝的数据直至提交事务 并发读任务继续读取旧版本数据，不阻塞）
		
redo日志
		
    事务提交后需要刷新数据到磁盘，但是磁盘随机写性能低，每次都刷盘影响吞吐量
		优化是将修改行为记录到redo日志中，再定期刷入磁盘(变为磁盘顺序写),数据库崩溃重启后会重做redo日志，保证事务对数据的影响刷到磁盘
		redo日志保障已提交事务的ACID特性
		
	每次写入logbuffer，write系统调用后写入os Cache，fsync后刷入磁盘
	每秒write一次OS cache，同时fsync刷磁盘，性能好；
	每次都write入OS cache，同时fsync刷磁盘，一致性好；
	每次都write入OS cache，每秒fsync刷磁盘，折衷；（推荐）
	
	
redo

		记录物理数据变化
		循环使用文件内容会被覆盖
		保证事务的持久性
		事务开始时写入，状态为prepare，binlog写入完成事务提交变为comit，崩溃恢复时只恢复commit状态数据

binlog

		记录逻辑变化
		文件追加的形式
		用户主从复制、ES和mysql同步
		事务提交时写入



undo日志
		
    事务未提交时，会将修改前的旧版本数据存到undo日志中，当事务回滚或数据库崩溃时通过undo日志恢复未提交事务对数据的影响 
    		数据行记录有个一个字段回滚指针，指向undolog的上一个版本数据
		对于insert操作，undo中记录新增记录主键，回滚时直接删除
		对于update和delete操作，undo中记录修改前数据，回滚时恢复
		undo日志保障未提交事务不会对ACID特性产生影响
		
快照读
		
    一致性不加锁的读
		这里的一致性指的是事务读取到的数据要么是事务开始时就存在的数据，要么是事务执行期间自身插入或修改的数据
		未显示加锁的读都是快照读
		
自增锁(Auto-inc Locks)
		
    自增锁是表级别的锁，针对事务插入AUTO_INCREMENT类型的列。一个事务插入记录时，其他事务的插入阻塞，保证第一个事务插入的主键连续
    
    
 #### 锁

  		锁分为共享锁和排他锁
		读锁是共享的 可以通过lock in share mode实现 这时只能读不能写
		写操作是排他的 会阻塞其他的读写操作 分为表锁和行锁两种
		表锁会锁定整张表 阻塞其他用户对该表的所有读写 比如alter修改表结构的时候
		行锁分为乐观锁和悲观锁，悲观锁通过for update实现，乐观锁通过版本号实现
    
解决幻读问题

	select for update
	update
	delete
	这些sql语句都是应用临键锁解决幻读问题
	主键或者唯一索引的等值操作是记录锁
	主键或者唯一索引的范围或者二级索引的操作是临键锁
	未命中索引的情况下，退化为表锁

	mvvc解决了对于普通读语句的幻读问题
	每启动一个事务，事务都有一个递增的事务id
	行记录会有一个创建版本、删除版本
	对于insert  新插入的行记录创建版本为当前事务id
	对于update 新插入行记录创建版本为当前事务id，同时老的记录删除版本为当前事务id
	对于delete  行记录的删除版本为当前事务id

	读取的数据需满足两个条件：
	新建版本号小于等于当前版本号，删除版本号为空或者大于当前版本号
