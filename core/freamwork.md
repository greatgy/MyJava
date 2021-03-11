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



##### spring生命周期

	- 实例化，创建bean对象

	- 设置对象属性

	- 初始化：如果实现了aware接口 通过aware接口拿到spring容器资源

	  	如果实现了beanPostProcessor 回调postProcessBeforeInitialzation和postProcesseAfterInitialzation 方法

	  ​     如果配置了init-method 执行init-method配置的方法

	- 销毁：如果配置了destory-method 会执行destory-method方法

##### 循环依赖

	通过三级缓存拿到未初始化的对象，前提是两个对象不都是用构造器注入

	一级缓存保存实例化并初始化过的对象

	二级缓存保存实例化 没有初始化过的对象

	三级缓存 对象实例化之后加入三级缓存

	流程：A对象实例化之后加入三级缓存 设置属性发现需要依赖B对象，从一到三级缓存依次查询B对象

	未查到B对象，实例化发现依赖A对象 从三级缓存中查询到A 把A移动到二级缓存，B移动到一级缓存，接着创建A对象 从一级缓存中拿到B对象完成创建 A移动到一级缓存


## SpringMVC
