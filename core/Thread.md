## 线程

#### 线程的状态
	new 初始状态，线程被创建还没有调用start()方法
	runnable 运行状态，java中将就绪和运行统称为运行中
	blocked 阻塞状态，表示线程阻塞于锁
	waiting 等待状态，线程进入等待状态，表示需要等待其他线程做出通知或中断
	time_waiting 超时等待状态，不同于waiting可以指定时间自动返回
	terminated 终止转态，线程已经执行结束

中断是线程的一个标识位属性，表示一个运行中的线程是否被其他线程进行了中断操作，线程被中断的结果取决于程序本身

Synchronized和ReenTrantLock的区别：

	Synchronized可以修饰代码块、实例方法、静态方法，依赖于JVM隐式的加锁，底层通过字节码指令（monitorenter、monitorexit）和同步访问标志来实现
	Synchronized的重量级锁是通过对象内部的一个叫做监视器锁（monitor）来实现的，监视器锁本质又是依赖于底层的操作系统的Mutex Lock（互斥锁）来实现的。而操作系统实现线程之间的切换需要从用户态转换到核心态，这个成本非常高，状态之间的转换需要相对比较长的时间，这就是为什么Synchronized效率低的原因
	ReenTrantLock需要显式的获取释放锁，lock的释放锁通常写在finally中，lock的话还有tryLock()并且可以设置阻塞时间，lock还可以设置为公平锁, tryLock方式阻塞可被中断 通过aqs的state
	

##### CAS

	四个参数：Object、offset、expectValue、targetValue
	比较对象的内存偏移量offset位置的值和期望值比较 相同则用目标值赋值 否则返回false
	aba问题 通过版本号或者时间戳解决
	并发高时可能自旋时间长 消耗cpu
	

#### volatile

	在多核cpu环境下，保证线程间的可见性，对volatile修饰变量的修改操作会增加一条lock前缀的指令
	有两个实现原则	
	lock前缀的指令会使处理器缓存回写到系统缓存
	处理器缓存写入系统缓存会使其他处理器缓存了改内存地址的数据失效

### ReentrantLock
	
	尝试获取锁，获取失败构建节点加入队列，在队列中自旋获取锁，如果前驱节点是唤醒的则当前节点通过Lock.park()阻塞， 节点获取到锁后state加1，释放锁后state减1， 并且通过unpark唤醒后继节点参与竞争锁，获取到锁后如果线程中断过则响应中断
