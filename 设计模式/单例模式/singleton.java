//饿汉式
public class Singleton {
	private static Singleton instance = new Singleton();
	public static Singleton getInstance() {
		return instance;
	}
}

//懒汉式
class Singleton1 {
	private static Singleton1 instance;
	public static synchronized Singleton1 getInstance(){
		if (instance == null) {
			instance = new Singleton1();
		}
		return instance;
	}
}

//instance=new Singleton()创建对象的过程分为：1分配对象内存空间  2初始化对象  3变量指向分配的内存地址
//双重锁
class Singleton2 {
	private static volatile Singleton2 instance; //volatile修饰的变量 每次修改时都会清除各个线程中的变量值缓存，使得各线程都从主内存重新调用该变量
	public static Singleton2 getInstance (){	//使用volatile是因为创建对象的后两个步骤可能会被优化重排序 volatile禁止重排序
		if (instance == null) {//重排序后instance不为null  对象却没有初始化完成
			synchronized (Singleton2.class) {
				if (instance == null) {   
					instance = new Singleton2();
				}
			}
		}
		return instance;
	}
}


//基于类初始化的解决方案
class InstanceFactory{
	private static class InstanceHolder{
		static Singleton instance = new Singleton();//类初始化是是同步执行的保证只初始化一次，  此时创建对象同样会重排序优化，但是同步下对其他线程不看见
	} 
	
	public static Singleton getInstance(){
		return InstanceHolder.instance;//调用类的静态字段会触发初始化类  
	}
}
