## Mybatis
#### mybatis缓存：

	 一级缓存面向sqlSession, 默认开启
	 二级缓存 Mapper(namespace) 面向mapper,多个sqlSession共用
   

$是字符串替换，#为预编译 mybatis在处理#{}时，会将sql中的#{}替换为?号，调用PreparedStatement的set方法来赋值

#### MyBatis处理日期时间：

	MyBatis处理日期时间有两种的jdbcType  jdbcType=DATE  jdbcType=TIMESTAMP
	将java.util.Date当做参数传递给Mapper的时候 能够表示MySQL的三种字段类型date datetime timestamp
	不管MySQL的日期字段类型是date、datetime或者timestamp中的哪一种，MyBatis都能够自动做出类型转换
	唯一的不同点是指定jdbcType=DATE的时候，MyBatis会自动截取掉时间
	总之不手动指定参数的jdbcType必然没有问题。


## Spring
#### spring bean的作用域
	singleton: Spring IOC容器中只有一个bean以单例模式存在，为默认值
	prototype: 每次调用getBean()都返回一个新的实例
	request: 每次http请求创建新的实例，仅适用于WebApplicationContext环境
	session: 同一个http session共享一个实例，不同session使用不同的bean,仅适用于WebApplicationContext环境
	global session: 仅适用于WebApplicationContext环境


IOC:对象获得依赖对象的过程由new创建变为有spring注入，由主动行为变成被动行为，控制权颠倒了，就是控制反转的由来
依赖注入: setter注入和构造器注入

#### spring 循环依赖问题
	通过引入一个map缓存，用来保存创建了对象实例，但是没有初始化属性的对象
	创建A对象时发现依赖B对象，通过getBean获取B对象，发现B对象为null，创建B对象同时发现依赖A对象 getBean查找为null 会去缓存map中查找 找到A对象的实例
	不用构造器注入用setter注入
## SpringMVC
