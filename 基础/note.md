#### session

	1、在用户登录成功时，创建session对象，保存用户信息
	2、将此session的sessionid保存到cookie中
	3、同时将sessionid于session对应关系存储到应用域中，以便后面可以根据sessionid来获取到session
	4、在用户关闭浏览器，重新打开浏览器访问网站时，读取用户的cookie，得到sessionid
	5、根据sessionid获取到第3步存储到应用域中的session对象
	6、从session中读取用户信息

抽象类可以有非抽象方法，接口必须全是抽象方法
接口中变量必须是public statci final修饰，抽象类没要求
接口中不能有静态方法和代码块
抽象类不能用来创建对象
抽象类必须被子类重写抽象方法（子类是抽象类除外）

static方法内部不能调用非静态方法/变量
重载不能只靠返回值类型不同

public void showKeyValue1(Generic<? extends Number> obj){  //普通方法
public <T extends Number> T showKeyName(Generic<T> container){//泛型方法
public class Generic<T extends Number>{//泛型类

switch 表达式的值必须是整型 byte shot char都可以隐式的转为int,String使用hashcode转为int值，枚举类型


#### 一致性hash算法：
	将所有哈希值分布到一个圆环上范围是0~2^32-1，将节点hash后分布到换上，当节点比较少时多次hash生成多个节点成为虚拟节点，数据hash分布到环上按照顺时针方向定位到节点
	
