import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

//cglib是针对类来实现代理的，原理是对指定的业务类生成一个子类，并覆盖其中业务方法实现代理。因为采用的是继承，所以不能对final修饰的类进行代理。
public class cglib {
	static class Hello{
		void sayHello(){
			System.out.println("hello");
		}
	}
	
	static class DynamicProxy implements MethodInterceptor {
		Object obj;
		
		Object bind(Object originalObj){
			obj = originalObj;
			Enhancer enhancer = new Enhancer();//创建加强器，用来创建动态代理类
			enhancer.setSuperclass(originalObj.getClass());//为加强器指定要代理的业务类（即：为下面生成的代理类指定父类）
			//设置回调：对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实现intercept()方法进行拦截
			enhancer.setCallback(this);
			return enhancer.create();
		}
		@Override
		public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
			System.out.println("你好");
			methodProxy.invokeSuper(object, args);
			System.out.println("world");
			return null;
		}
	}
	
	public static void main(String[] args) {
		Hello hello = (Hello) new DynamicProxy().bind(new Hello());
		hello.sayHello();
	}
}
