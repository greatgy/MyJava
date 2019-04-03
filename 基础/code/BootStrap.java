
public class BootStrap {
	static class Parent{
		static int a =5;
		int b = 6;

		static{
			System.out.println("parent static code block");
		}

		{
			System.out.println("parent code block");
		}

		public Parent(){//子类继承了父类时 父类如果有参数构造参数，则父类无参构造器必须写(或者在子类构造器中显示的调用父类构造器)
			System.out.println("parent constractor");
		}

		public static void Test(){
			System.out.println("1");
		}

		public void test2(){
			System.out.println("2" + b);
		}
	}
	static class Child extends Parent{
		static int a =7;
		int b = 8;

		static{
			System.out.println("child static code block");
		}

		{
			System.out.println("child code block");
		}

		public Child(){
			System.out.println("child constractor");
		}

		public Child(String name){
			super.test2();
			System.out.println(name);
		}

		public static void Test(){
			System.out.println("3");
		}

		public void test2(){
			System.out.println("4" + b);
		}
		//当使用子类对象调用方法使用同名变量，是按照方法来判断使用哪一个变量，调用父类的方法，使用的是父类中的变量   ，  调用子类的方法，使用的是子类中的变量
	}

	public static void main(String[] args){ //加载顺序为父类静态代码块、子类静态代码块、父类普通代码块、父类默认构造器、子类普通代码块、子类构造器
		Parent p = new Child("456");		//子类会默认调用父类的无参构造方法（初始化子类之前会初始化父类）
		p.Test();
		p.test2();//类的成员方法是动态绑定的
		System.out.println(p.a);
		System.out.println(p.b);//类的成员变量不是动态绑定的
	}
}
