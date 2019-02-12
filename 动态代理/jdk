import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.xml.bind.Binder;

public class DynamicProxyTest{
	interface IHello{
		void sayHello();
	}
	
	static class Hello implements IHello {
		@Override
		public void sayHello() {
			System.out.println("hello world");
		}
	}
	
	static class DynamicProxy implements InvocationHandler {
		Object object;
		
		Object bind(Object original){
			object = original;
			return Proxy.newProxyInstance(original.getClass().getClassLoader(), original.getClass().getInterfaces(), this);
		}
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("welcome");
			return method.invoke(object, args);
		}
	}
	
	public static void main(String[] args) {
		IHello hello = (IHello) new DynamicProxy().bind(new Hello());
		hello.sayHello();
	}
	
}
