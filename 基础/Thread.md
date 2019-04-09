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
	ReenTrantLock需要显式的获取释放锁，lock的释放锁通常写在finally中，lock的话还有tryLock()并且可以设置阻塞时间，lock还可以设置为公平锁