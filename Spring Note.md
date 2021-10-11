[toc]

# 1、Spring

## 1.1、简介

+ Spring：给行业带来的春天
+ 2002年，首次推出了spring框架的雏形，interface21框架
+ Spring框架以interface21框架为基础，经过重新设计，并不断丰富其内涵，于2004年3月24日发布了1.0版本
+ Rod Johnson，springFrame work创始人，是悉尼大学的音乐学博士
+ spring理念：使现有的技术更加容易使用，本身是个大杂烩



> spring设计哲学节选：

+ > 强大的向后兼容性

+ > **解决企业应用开发的复杂性**



SSH: Struct2 + Spring + Hibernates（全自动）

SSM: SpringMVC + Spring + Mybatis (半自动，更灵活)

```html
<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.3.9</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>5.3.9</version>
</dependency>
```



## 1.2、优点

+ spring是一个开源的免费的框架（容器）
+ spring是一个轻量级的、<u>非入侵式（下载代码以后不会改变你原有代码的任何情况</u>）的框架
+ Ioc 控制反转  AOP面向切面编程
+ 支持事务的处理（声明式事务），对框架整合的支持



==总结一句话：Spring就是一个轻量级的控制反转（Ioc）和面向切面编程（AOP）的框架==

## 1.3、组成

![image-20211008212050250](/Users/duanyihan/Library/Application Support/typora-user-images/image-20211008212050250.png)

**核心容器（Spring Core）**

　　核心容器提供Spring框架的基本功能。Spring以bean的方式组织和管理Java应用中的各个组件及其关系。Spring使用BeanFactory来产生和管理Bean，它是工厂模式的实现。BeanFactory使用控制反转(IoC)模式将应用的配置和依赖性规范与实际的应用程序代码分开。

**应用上下文（Spring Context）**

　　Spring上下文是一个配置文件，向Spring框架提供上下文信息。Spring上下文包括企业服务，如JNDI、EJB、电子邮件、国际化、校验和调度功能。

**Spring面向切面编程（Spring AOP）**

　　通过配置管理特性，Spring AOP 模块直接将面向方面的编程功能集成到了 Spring框架中。所以，可以很容易地使 Spring框架管理的任何对象支持 AOP。Spring AOP 模块为基于 Spring 的应用程序中的对象提供了事务管理服务。通过使用 Spring AOP，不用依赖 EJB 组件，就可以将声明性事务管理集成到应用程序中。

**JDBC和DAO模块（Spring DAO）**

　　JDBC、DAO的抽象层提供了有意义的异常层次结构，可用该结构来管理异常处理，和不同数据库供应商所抛出的错误信息。异常层次结构简化了错误处理，并且极大的降低了需要编写的代码数量，比如打开和关闭链接。

**对象实体映射（Spring ORM）**

　　Spring框架插入了若干个ORM框架，从而提供了ORM对象的关系工具，其中包括了Hibernate、JDO和 IBatis SQL Map等，所有这些都遵从Spring的通用事物和DAO异常层次结构。

**Web模块（Spring Web）**

　　Web上下文模块建立在应用程序上下文模块之上，为基于web的应用程序提供了上下文。所以Spring框架支持与Struts集成，web模块还简化了处理多部分请求以及将请求参数绑定到域对象的工作。

**MVC模块（Spring Web MVC）**

　　MVC框架是一个全功能的构建Web应用程序的MVC实现。通过策略接口，MVC框架变成为高度可配置的。MVC容纳了大量视图技术，其中包括JSP、POI等，模型来有JavaBean来构成，存放于m当中，而视图是一个街口，负责实现模型，控制器表示逻辑代码，由c的事情。Spring框架的功能可以用在任何J2EE服务器当中，大多数功能也适用于不受管理的环境。Spring的核心要点就是支持不绑定到特定J2EE服务的可重用业务和数据的访问的对象，毫无疑问这样的对象可以在不同的J2EE环境，独立应用程序和测试环境之间重用。



## 1.4、拓展

现代化的开发，就是基于spring的开发

![image-20211008230018771](/Users/duanyihan/Library/Application Support/typora-user-images/image-20211008230018771.png)

+ springboot ：快速开发的脚手架，基于springboot，可以快速地开发单个微服务，约定大于配置！
+ springcloud：基于springboot实现。

学习springboot的前提，需要完全掌握spring和springmvc。



**弊端：发展了太久以后，违背了原来的理念，配置十分繁琐，人称“配置地狱”**



# 2、IOC 理论推导

1. UserDao接口
2. UserDaoImpl实现类
3. UserService业务接口
4. UserServiceImpl业务实现类



在我们之前的业务中，用户的需求可能会影响我们原来的代码，我们需要根据用户的需求去修改原来的代码。如果代码量非常大，修改一次的成本代价非常昂贵！



我们使用一个set接口实现

```java
public class UserServiceImpl implements UserService{
    private UserDao userDao = new UserDaoOracleImpl();

    //利用set进行动态实现值的注入
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public void getUser() {
        userDao.getUser();
    }
}
```

之前是程序主动创建对象，控制权是在程序猿上。

**使用set注入后，程序不再具有主动性，而是变成了被动的接受对象。**



这种思想，从本质上解决了问题，我们程序员不用再管理对象的创建。系统的耦合性大大降低，可以更加专注在业务的实现。这是Ioc的原型。

![image-20211008232744435](/Users/duanyihan/Library/Application Support/typora-user-images/image-20211008232744435.png)



IOC是spring框架的核心内容，可以使用xml配置，也可以使用注解配置。

Spring容器在初始化时先读取配置文件，根据配置文件或者元数据创建与组织对象存入容器中，程序使用时再从Ioc容器中取出需要的对象。

<img src="/Users/duanyihan/Library/Application Support/typora-user-images/image-20211008233056544.png" alt="image-20211008233056544" style="zoom:50%;" />

**控制反转是一种通过描述（XML或注解）并通过第三方去生产或者获取特定对象的方式。在spring中实现控制反转的是IoC容器，其实现方式是依赖注入DI**



# 3、HelloSpring

bean必须得有set方法才能IoC成功.

**对象由Spring来创建，管理，装配。**



# 4、IoC创建对象的方式

**狂神的类**

```java
public class User {
    private String name;

//    public User(){
//        System.out.println("User的无参构造");
//    }
    public User(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void show(){
        System.out.println("name=" + name);
    }
}
```



1.使用了无参构造创建对象，默认

```html
<!--无参构造注册-->
<bean id="user" class="com.yihan.pojo.User">
	<property name="name" value="yihan"/>
</bean>
```



2.假设我们要使用有参构造

```html
<!-- 有参构造注册index-->
<bean id="user" class="com.yihan.pojo.User">
    <constructor-arg index="0" value="yihan"/>
</bean>
```

```html
<!-- 有参构造注册 类型, 不建议使用，引用类型必须全限定名字-->
<bean id="user" class="com.yihan.pojo.User">
    <constructor-arg type="java.lang.String" value="yihan best!"/>
</bean>

```

```html
<!-- 有参构造注册 直接通过参数名-->
<bean id="user" class="com.yihan.pojo.User">
    <constructor-arg name="name" value="yihan best"/>
</bean>

```

_____

**官网给的例子**

https://docs.spring.io/spring-framework/docs/5.3.2/reference/html/core.html#beans-some-examples

**被托管类之无参**

```java
public class ExampleBean {

    private AnotherBean beanOne;

    private YetAnotherBean beanTwo;

    private int i;

    public void setBeanOne(AnotherBean beanOne) {
        this.beanOne = beanOne;
    }

    public void setBeanTwo(YetAnotherBean beanTwo) {
        this.beanTwo = beanTwo;
    }

    public void setIntegerProperty(int i) {
        this.i = i;
    }
}
```

无参构造配置

```java
<bean id="exampleBean" class="examples.ExampleBean">
    <!-- constructor injection using the nested ref element -->
    <constructor-arg>
        <ref bean="anotherExampleBean"/>
    </constructor-arg>

    <!-- constructor injection using the neater ref attribute -->
    <constructor-arg ref="yetAnotherBean"/>

    <constructor-arg type="int" value="1"/>
</bean>

<bean id="anotherExampleBean" class="examples.AnotherBean"/>
<bean id="yetAnotherBean" class="examples.YetAnotherBean"/>
```

被托管类之有参

```java
public class ExampleBean {

    private AnotherBean beanOne;

    private YetAnotherBean beanTwo;

    private int i;

    public ExampleBean(
        AnotherBean anotherBean, YetAnotherBean yetAnotherBean, int i) {
        this.beanOne = anotherBean;
        this.beanTwo = yetAnotherBean;
        this.i = i;
    }
}
```

有参配置

```html
<bean id="exampleBean" class="examples.ExampleBean" factory-method="createInstance">
    <constructor-arg ref="anotherExampleBean"/>
    <constructor-arg ref="yetAnotherBean"/>
    <constructor-arg value="1"/>
</bean>

<bean id="anotherExampleBean" class="examples.AnotherBean"/>
<bean id="yetAnotherBean" class="examples.YetAnotherBean"/>
```

------

**官网举例之配置类in detail**

```html
<bean id="myDataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
    <!-- results in a setDriverClassName(String) call -->
    <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
    <property name="url" value="jdbc:mysql://localhost:3306/mydb"/>
    <property name="username" value="root"/>
    <property name="password" value="misterkaoli"/>
</bean>
```

```html
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:p="http://www.springframework.org/schema/p"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
    https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="myDataSource" class="org.apache.commons.dbcp.BasicDataSource"
        destroy-method="close"
        p:driverClassName="com.mysql.jdbc.Driver"
        p:url="jdbc:mysql://localhost:3306/mydb"
        p:username="root"
        p:password="misterkaoli"/>

</beans>
```

```html
<bean id="mappings"
    class="org.springframework.context.support.PropertySourcesPlaceholderConfigurer">

    <!-- typed as a java.util.Properties -->
    <property name="properties">
        <value>
            jdbc.driver.className=com.mysql.jdbc.Driver
            jdbc.url=jdbc:mysql://localhost:3306/mydb
        </value>
    </property>
</bean>
```

----

**Bean实例化之代码案例演示**

```java
public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        User user = (User) context.getBean("user");
        user.show();
    }
}

```

这里只get了User类，但是实际在beans.xml中注册了UserT的无参构造，在这个test被运行的时候，userT也被创建了，**说明在这个容器初始化的时候，bean就已经被实例化了**。



同时，如果对同一个类getBean两次，实例化的都是一个对象。

```java
public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        User user = (User) context.getBean("user");
        User user2 = (User) context.getBean("user");
        System.out.println(user == user2);//true
        user.show();
    }
}
```



==总结：==

在配置文件加载的时候，容器中管理的对象就已经初始化了。



# 5、Spring配置说明



## **5.1、别名 alias**

```html
    <!-- 别名配置-->
    <alias name="user" alias="userAlia"/>
```



## **5.2、 Bean的配置**

```html
    <!--
    id: bean的唯一标识符，相当于我们学的对象名
    class： bean 对象所对应的全限定名 包名+类型
    name：也是别名，name可以同时取多个名字
    -->
<bean id="UserT" class="com.yihan.pojo.UserT" name="user2 u2,u3;u4">
    <property name="name" value="yihan"/>
</bean>
```



## **5.3、Import**

这个import，一般用于团队开发使用，他可以将多个配置文件，导入合并为一个。

假设，现在项目中有多个人开发，这三个人负责不同的类的开发，不同的类需要注册在不同的bean中，我们可以利用import将所有人的xml文件合并为一个总的，一般使用applicationContext

```html
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/context
    http://www.springframework.org/schema/context/spring-context.xsd">

    <import resource="Beans.xml"/>
    <import resource="Beans2.xml"/>
    <import resource="Beans3.xml"/>
</beans>
```



# 6、依赖注入



## 6.1、构造器注入

前面已经提到了。



## 6.2、Set方式注入（重点）

+ 依赖注入：set注入。
    + 依赖：bean对象的创建依赖于容器
    + 注入：bean对象中的所有属性，由容器来注入



【环境搭建】

1.复杂类型

```java
package com.yihan.pojo;

public class Address {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

```



2.真实测试对象

```java
package com.yihan.pojo;

import java.util.*;

public class Student {
    private String name;
    private Address address;
    private String[] books;
    private List<String> hobbies;
    private Map<String, String> card;
    private Set<String> games;
    private String wife; // null
    private Properties info;
}

```

3.beans.xml

```html
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/context
    http://www.springframework.org/schema/context/spring-context.xsd">

    <bean id="address" class="com.yihan.pojo.Address">
        <property name="address" value="xian"/>
    </bean>
    <bean id="student" class="com.yihan.pojo.Student">
        <!--第一种，普通值注入, value-->
        <property name="name" value="yihan"/>
        <!--第二种，Bean注入, ref-->
        <property name="address" ref="address"/>
        <!--第三种，数组注入-->
        <property name="books">
            <array>
                <value>红楼梦</value>
                <value>西游记</value>
                <value>水浒传</value>
                <value>三国演义</value>
            </array>
        </property>
        <!--第四种，list注入-->
        <property name="hobbies">
            <list>
                <value>听歌</value>
                <value>看电影</value>
                <value>敲代码</value>
            </list>
        </property>
        <!--第五种，map注入 entry-->
        <property name="card">
            <map>
                <entry key="身份证" value="123456123456"/>
                <entry key="银行卡" value="bankCard9527"/>
            </map>
        </property>
        <!--第六种，set注入-->
        <property name="games">
            <set>
                <value>LOL</value>
                <value>COC</value>
                <value>BOB</value>
            </set>
        </property>
        <!--第七种，null注入-->
        <property name="wife">
            <null/>
        </property>
        <!--第八种，properties注入-->
        <property name="info">
            <props>
                <prop key="学号">201206283</prop>
                <prop key="性别">男</prop>
            </props>
        </property>
    </bean>

</beans>
```



4.测试类

```java
public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        // 第一种普通值注入
        Student student = (Student) context.getBean("student");
        System.out.println(student.toString());
    }
}
```



## 6.3、 拓展方式注入

c-namespace and p-namespace

c命名空间其实对应的就是constructor-arg，p空间对应的是set注入，property 

**配置文件**

```html
    <!--P命名空间注入，可以直接注入属性的值:properties-->
    <bean id="user" class="com.yihan.pojo.User" p:name="yihan" p:age="10"/>
    <!--c命名空间注入，可以通过constructor注入-->
    <bean id="user2" class="com.yihan.pojo.User" c:age="18" c:name="duanZiKai"/>
```

**测试类**

```java
@Test
public void test2(){
    ApplicationContext context = new ClassPathXmlApplicationContext("userBeans.xml");
    User user = (User) context.getBean("user2");
    System.out.println(user);
}
```

**注意点**

两个命名空间不能直接使用，必须导入xml约束

```html
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:c="http://www.springframework.org/schema/c"
```



## 6.4、Bean作用域 （Scope）

![image-20211009135525209](/Users/duanyihan/Library/Application Support/typora-user-images/image-20211009135525209.png)



1.单例模式（spring默认机制）：都只是一个对象 scope = "singleton"  省资源

2.原型模式：每一个都是单独的对象 scope = "prototype" 适合多线程

3.其余的request、session、application、websocket 这些只能在web开发中使用



# 7、Bean的自动装配

+ 自动装配式是Spring满足bean依赖的一种方式
+ Spring会在上下文中自动寻找，并自动给bean装配属性



在Spring中有三种装配的方式：

1. 在xml中显示的配置

		2. 在java中显示配置
  		3. **隐式自动装配（重要）**



## 7.1测试

环境搭建 ：一个人有两个宠物

## 7.2 自动装配

### **AutoWired ByName**

![image-20211009141553509](/Users/duanyihan/Library/Application Support/typora-user-images/image-20211009141553509.png)

![(/Users/duanyihan/Library/Application Support/typora-user-images/image-20211009141516179.png)

![image-20211009141533569](/Users/duanyihan/Library/Application Support/typora-user-images/image-20211009141533569.png)

```html
    <bean id="people" class="com.yihan.pojo.People" autowire="byName"><!--名字id必须得唯一-->
        <property name="name" value="yihan"/>
    </bean>
```



>        很多人以为，byName的自动装配是跟类中的属性有关，其实不是，使用byName进行自动装配时，是利用Java的反射机制获取自动装配类中的set方法名，去掉set后将其首字母小写再到IOC容器中查找是否有对应的beanId，如果有则查看该bean的类型与set方法的参数类型是否匹配，匹配上了则调用set方法进行依赖注入。因此，byName的自动装配跟set方法名和参数类型有关，跟属性名无关。可以尝试将setHelloWorldEnglish中的H小写，如：sethelloWorldEnglish，其他不变，依然可以注入成功。也可以任意更改set方法名和beanId标识进行自动装配测试。
>        ————————————————
>        版权声明：本文为CSDN博主「_云卷云舒_」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
>        原文链接：https://blog.csdn.net/Alexshi5/article/details/84000678

### AutoWired ByType

```html
    <!--会自动在容器中上下文查找， 和自己对象属性类型相同的bean-->
    <bean id="people" class="com.yihan.pojo.People" autowire="byType"><!--保证type必须全局唯一-->
        <property name="name" value="yihan"/>
    </bean>
```



小结：

+ ByName的时候必须保证所有bean的id唯一，并且这个bean需要和自动注入的属性的set后面的literal一致
+ ByType的时候必须保证bean的全局class唯一，并且这个bean需要和自动注入的属性的类型一致



## 7.3、使用注解开发

自Jdk1.5支持注解，Spring2.5支持注解

要使用注解须知：

1. 导入约束 context约束
2. 配置注解的支持：xmlns:context="http://www.springframework.org/schema/context"



@Autowired

直接在属性上使用, 且可以直接忽略set方法；

也可以在set方式上使用；



 科普

@Nullable 这个字段可以为null

```java

@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {
    boolean required() default true;
}
```

在@Autowired自动装配的环境比较复杂的时候，无法通过一个@Autowired完成功能，可以使用@Qualifier(value="dog222")，此处value为bean id，配合@Autowired一起使用，指定一个特定的bean 对象的注入



@Resource也可以，但是没有spring人性化，旁边没有图标提示，需要名字或type有一个合法

@Resource(name="cat2")也是可以指定特定的bean



小结：

**共同点**

@Resource @Autowired

+ @Resource和@Autowired都可以作为注入属性的修饰，在接口仅有单一实现类时，两个注解的修饰效果相同，可以互相替换，不影响使用。

**不同点**

- @Resource是Java自己的注解，@Resource有两个属性是比较重要的，分是name和type；Spring将@Resource注解的name属性解析为bean的名字，而type属性则解析为bean的类型。所以如果使用name属性，则使用byName的自动注入策略，而使用type属性时则使用byType自动注入策略。如果既不指定name也不指定type属性，这时将通过反射机制使用byName自动注入策略。
- @Autowired是spring的注解，是spring2.5版本引入的，**Autowired只根据type进行注入**，不会去匹配name。如果涉及到type无法辨别注入对象时，那需要依赖@Qualifier或@Primary注解一起来修饰。



作者：程序员良许
链接：https://www.zhihu.com/question/39356740/answer/2038996124
来源：知乎
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。



# 8、使用注解开发

**类注入**

@Component组件，放在类熵，说明这个类被spring管理了，相当于在xml注册了



**属性注入**

@Value 用在@Component注解的类的字段上，给对应的bean实例赋值；也可以放在set方法上



**@Component 衍生**

dao : @Repository

service: @Service

Controller: @Controller

这几个注解的功能和@Component都是一样的，都是讲代表某个类注册到容器中，装配bean



**作用域**

@Scope，上面提到过，单例，原型等



小结：xml与注解

+ xml 更加万能，适用于任何场景，维护简单方便
+ 注解 不是自己的类使用不了，维护相对麻烦（每个类都得改）



**最佳实践：**

==**xml用来管理bean，注解只负责完成属性的注入**==

注意，记得开启注解生效的驱动，annotation-config



# 9、使用java的方式配置Spring

我们现在要完全不使spring的xml配置，全权交给java来做。

JavaConfig是spring的一个子项目，在spring4之后，他成为了一个核心功能



 **实体类**

```java
@Component // 类被spring接管了
public class User {
    private String name;

    public String getName() {
        return name;
    }
    @Value("YIHAN") //属性注入值
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
```

**配置类**

```java
@Configuration //这个也会被spring容器托管。因为他也属于一个@Component，只不过代表他是一个配置类，和之前看到的beans.xml一样
@ComponentScan("com.yihan.pojo")
@Import(MyConfig2.class)
public class MyConfig {

    // 注册一个bean，就相当于我们之前写的bean标签
    // 这个方法的名字就相当于bean标签中的id实行；
    // 方法的返回值就相当于bean标签的class属性
    @Bean
    public User getUser(){
        return new User();
    }
}
```

**测试类**

```java
public class MyTest {
    public static void main(String[] args) {
        //如果完全使用了配置类方式去做，我们就只能痛殴AnnotationConfig上下文来获取容器，通过配置类的class对象加载
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        User getUser = (User) context.getBean("getUser"); //  如果改成user则会爆异常
        System.out.println(getUser.getName());
    }
}
```

这样纯Java的配置方法，在springboot中随处可见



# 10、代理模式

为什么要学习代理模式？因为这是SpringAOP的底层【SpringAOP 和SpringMVC】

代理模式：

+ 静态代理
+ 动态代理



## 10.1、静态代理

角色分析

+ 抽象角色：一般会使用借口或者抽象类来解决
+ 真实角色：被代理的角色
+ 代理角色：代理真实角色，代理真实角色后，我们一般会做一些附属操作
+ 客户：访问代理对象的人

**代码**

接口

```java
package com.yihan.demo01;

//出租房
public interface Rent {

    public void rent();
}
```



真实角色

```java
package com.yihan.demo01;

//房东
public class Host implements Rent{
    @Override
    public void rent() {
        System.out.println("房东要出租房子");
    }
}

```

代理角色

```java
package com.yihan.demo01;

public class Proxy implements Rent{
    private Host host;

    public Proxy() {
    }

    public Proxy(Host host) {
        this.host = host;
    }

    @Override
    public void rent() {
        seeHouse();
       host.rent();
       fare();
       contract();
    }

    //看房子
    public void seeHouse(){
        System.out.println("看房子");
    }
    //收中介费
    public void fare(){
        System.out.println("收中介费");
    }
    //签合同
    public void contract(){
        System.out.println("签合同");
    }
}

```



客服端访问代理角色

```java
package com.yihan.demo01;

public class Client {
    public static void main(String[] args) {
        // 房东要出租房子
        Host host = new Host();
        // 代理帮房东出组房子，但一般会多一些附属操作
        Proxy proxy = new Proxy(host);


        // 你不用面对房东，直接找代理即可
        proxy.rent();
    }
}

```



**好处**

+ 可以使真实角色的操作更加纯粹，不用去关注一些公共的业务
+ 公共业务就交给了代理角色，实现了业务的分工
+ 公共业务发生扩展的时候，方便集中管理

**缺点**

+ 一个真实角色对应一个代理角色；代码量会翻倍，开发效率变低



## 10.2、再理解

代码对应spring-08-demo02

![image-20211009163654814](/Users/duanyihan/Library/Application Support/typora-user-images/image-20211009163654814.png)

AOP 的底层，横向开发



## 10.3、动态代理

+ 动态代理和静态代理角色是一样的
+ 动态代理的代理类是动态生成的，不是我们直接写好的
+ 分为两大类：基于接口的代理和基于类的动态代理
    + 基于接口---JDK动态代理【我们在这里使用】
    + 基于类---cglib
    + java字节码实现 JAVAsist（不在tomcat里面，在JBOSS应用服务器项目中）



需要了解两个类：Proxy 代理，InvocationHanldler 调用处理程序

**Proxy**

![image-20211009164524739](/Users/duanyihan/Library/Application Support/typora-user-images/image-20211009164524739.png)

**InvocationHandler**

![image-20211009164500101](/Users/duanyihan/Library/Application Support/typora-user-images/image-20211009164500101.png)



![image-20211009171418793](/Users/duanyihan/Library/Application Support/typora-user-images/image-20211009171418793.png)



动态代理类的好处：

+ 可以使真实角色的操作更加纯粹！不用去关注一些公共业务
+ 公共业务交给代理角色，实现了业务的分工
+ 公共业务发生扩展的时候，方便集中管理
+ 一个动态代理类代理的是一个接口，一般就是对应的一类业务



# 11、AOP

## 11.1 、什么是AOP

通过预编译的方式和运行期间动态代理实现程序的统一维护的一共技术。AOP是OOP的延续，是软件开发中的一个热点，也是Spring框架中的一个重要内容，是函数是编程的一种衍生范型。利用AOP可以对业务逻辑的各个部门进行隔离，从而使得业务逻辑个部分之间的耦合性较低，提高程序的可用性，同时提到了开发效率。

![image-20211009205542184](/Users/duanyihan/Library/Application Support/typora-user-images/image-20211009205542184.png)

## 11.2、 AOP在Spring中的作用

**==提供声明式事务，允许用户自定义切面==**

+ 横切关注点：跨越应用程序多个模块的方法或功能。与我们业务逻辑无关的，但是我们需要关注的部分，就是横切关注点。如日志，安全，缓存，事务等等……
+ 切面（Aspect）：横切关注点被模块化的特殊对象，他是一个类
+ 通知（Advice）：切面必须要完成的工作，他是一个方法
+ 目标（Target）：被通知对象
+ 代理（proxy）：向目标对象应用通知之后创建的对象
+ 切入点（PointCut）：切面通知执行的地点的定义
+ 连接点（JointPoint）：与切入点匹配的执行点

![image-20211009210219883](/Users/duanyihan/Library/Application Support/typora-user-images/image-20211009210219883.png)

SpringAOP中，通过Advice定义横切逻辑，Spring中支持5种类型的Advice

![image-20211009210412742](/Users/duanyihan/Library/Application Support/typora-user-images/image-20211009210412742.png)



**方式一：使用Spring的AOP接口【主要是springAPI】**

```html
    <bean id="userService" class="com.yihan.service.UserServiceImpl"/>
    <bean id="log" class="com.yihan.log.Log"/>
    <bean id="afterLog" class="com.yihan.log.AfterLog"/>
    <!--第一种方式AOP SpringAOP原生接口-->
    <!--配置AOP：需要导入aop的约束-->
    <aop:config>
        <!--切入点, execution:要执行的位置-->
        <aop:pointcut id="pointcut" expression="execution(* com.yihan.service.UserServiceImpl.*(..))"/>
        <!--执行环绕增加-->
        <aop:advisor advice-ref="log" pointcut-ref="pointcut"/>
        <aop:advisor advice-ref="afterLog" pointcut-ref="pointcut"/>
    </aop:config>
```

```java
public class AfterLog implements AfterReturningAdvice {
    // returnValue: 返回值
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("执行了" + method.getName() + "的方法， 返回的结果为：" + returnValue);
    }
}

```

```java
public class Log implements MethodBeforeAdvice {
    //method：要执行的目标对象的方法
    // args：参数
    // target：目标对象
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println(target.getClass().getName()+ "的" + method.getName() + "方法被执行了");
    }
}

```



**方式二：使用自定义类实现AOP【主要是切面定义】**

```html
    <bean id="diy" class="com.yihan.diy.DiyPointcut"/>
    <aop:config>
        <!--要引用的类-->
        <aop:aspect ref="diy">
            <!--切入点-->
            <aop:pointcut id="point" expression="execution(* com.yihan.service.UserServiceImpl.*(..))"/>
            <!--通知-->
            <aop:before method="before" pointcut-ref="point"/>
            <aop:after method="after" pointcut-ref="point"/>
        </aop:aspect>
    </aop:config>
```

```java
package com.yihan.diy;

public class DiyPointcut {

    public void before(){
        System.out.println("=====方法执行前==========");
    }

    public void after(){
        System.out.println("======方法执行后==========");
    }
}

```

**方式三：使用注解实现AOP**

```html
    <!--方式三-->
    <bean id="annotationPointcut" class="com.yihan.diy.AnnotationPointcut"/>
    <!--开启注解支持       第二个参数基本不用  false: JDK 默认         true：cglib-->
    <aop:aspectj-autoproxy proxy-target-class="false"/>
```



```java
@Aspect // 标注这个类是个切面
public class AnnotationPointcut {

    @Before("execution(* com.yihan.service.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("======方法执行前========");
    }

    @After("execution(* com.yihan.service.UserServiceImpl.*(..))")
    public void after(){
        System.out.println("======方法执行后========");
    }
    //在环绕增强中，我们可以给定一个参数，代表我们要获取处理切入的点
    @Around("execution(* com.yihan.service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("======方法执行环绕前========");

        // 执行方法
        Object proceed = joinPoint.proceed();

        System.out.println("======方法执行环绕后========");
    }
}
```



# 12、整合Mybatis

步骤：

 1. 导入相关jar 包

    + junit
    + mybatis
    + Mysql 
    + spring相关
    + aop织入
    + Mybatis-spring 

    ```html
    <?xml version="1.0" encoding="UTF-8"?>
    <project xmlns="http://maven.apache.org/POM/4.0.0"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
        <parent>
            <artifactId>spring-study</artifactId>
            <groupId>org.example</groupId>
            <version>1.0-SNAPSHOT</version>
        </parent>
        <modelVersion>4.0.0</modelVersion>
    
        <artifactId>spring-10-mybatis</artifactId>
        <dependencies>
            <dependency>
                <groupId>junit</groupId>
                <artifactId>junit</artifactId>
                <version>4.13.2</version>
                <scope>test</scope>
            </dependency>
    
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>8.0.25</version>
            </dependency>
            <dependency>
                <groupId>org.mybatis</groupId>
                <artifactId>mybatis</artifactId>
                <version>3.5.6</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-webmvc</artifactId>
                <version>5.3.10</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-jdbc</artifactId>
                <version>5.2.6.RELEASE</version>
            </dependency>
            <dependency>
                <groupId>org.aspectj</groupId>
                <artifactId>aspectjweaver</artifactId>
                <version>1.9.4</version>
            </dependency>
            <dependency>
                <groupId>org.mybatis</groupId>
                <artifactId>mybatis-spring</artifactId>
                <version>2.0.5</version>
            </dependency>
        </dependencies>
        
        <build>
            <resources>
                <resource>
                    <directory>src/main/java</directory>
                    <includes>
                        <include>**/*.xml</include>
                    </includes>
                    <filtering>true</filtering>
                </resource>
            </resources>
        </build>
    
        <properties>
            <maven.compiler.source>8</maven.compiler.source>
            <maven.compiler.target>8</maven.compiler.target>
        </properties>
    
    </project>
    ```

    

 2. 编写配置文件

 3. 测试



## 12.1、回忆Mybatis

1. 编写实体类

    ```java
    @Data
    public class User {
        private int id;
        private String name;
        private String pwd;
    }
    ```

    

2. 编写核心配置文件

    ```html
    <?xml version="1.0" encoding="utf-8"?>
    <!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-config.dtd">
    <configuration>
    
        <typeAliases>
            <package name="com.yihan.pojo"/>
        </typeAliases>
    
        <environments default="development">
            <environment id="development">
                <transactionManager type="JDBC"></transactionManager>
                <dataSource type="POOLED">
                    <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                    <property name="url" value="jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC"/>
                    <property name="username" value="root"/>
                    <property name="password" value="123456"/>
                </dataSource>
            </environment>
        </environments>
    
        <mappers>
            <mapper class="com.yihan.mapper.UserMapper"/>
        </mappers>
    
    
    </configuration>
    
    
    ```

    

3. 编写接口

```java
public interface UserMapper {
    public List<User> selectUser();
}
```

4. 编写Mapper.xml

    ```html
    <?xml version="1.0" encoding="utf-8"?>
    <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    <mapper namespace="com.yihan.mapper.UserMapper">
    
        <select id="selectUser" resultType="user">
            select * from mybatis.user;
        </select>
    </mapper>
    
    
    ```

    

5. 测试

    ```java
    import com.yihan.mapper.UserMapper;
    import com.yihan.pojo.User;
    import org.apache.ibatis.io.Resources;
    import org.apache.ibatis.session.SqlSession;
    import org.apache.ibatis.session.SqlSessionFactory;
    import org.apache.ibatis.session.SqlSessionFactoryBuilder;
    import org.junit.Test;
    
    import java.io.IOException;
    import java.io.InputStream;
    import java.util.List;
    
    public class MyTest {
        @Test
        public void test() throws IOException {
            String resources = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resources);
    
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sessionFactory.openSession(true);
    
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = mapper.selectUser();
    
            for(User user: userList){
                System.out.println(user);
            }
    
            sqlSession.close();
        }
    }
    
    ```

    



小结：实体类、mybatis核心配置、接口、接口对应的mapper、注册mapper

## 12.1、Spring整合Mybatis

Spring-dao.xml基本可以把原来mybatis.xml中的内容接管了，但是狂神个人习惯还是会在mybatis.xml中留几个内容

这个是原来的mybatis内容

```html
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <typeAliases>
        <package name="com.yihan.pojo"/>
    </typeAliases>

    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"></transactionManager>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC"/>
                <property name="username" value="root"/>
                <property name="password" value="123456"/>
            </dataSource>
        </environment>
    </environments>

    <mappers>
        <mapper class="com.yihan.mapper.UserMapper"/>
    </mappers>


</configuration>

```

Spring-dao.xml

```html
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/aop
    http://www.springframework.org/schema/aop/spring-aop.xsd">

    <!-- DataSource: 使用Spring的数据源替换Mybatis配置 c3p0 dbcp druid
    这里我们使用spring提供的JDBC-->
    <bean id="datasource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC"/>
        <property name="username" value="root"/>
        <property name="password" value="123456"/>
    </bean>

    <!--sqlSessionFactory-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="datasource"/>
        <!--绑定mybatis配置文件-->
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <property name="mapperLocations" value="classpath:com/yihan/mapper/*.xml"/>
    </bean>

</beans>
```

被接管后的mybatis.xml

```html
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <typeAliases>
        <package name="com.yihan.pojo"/>
    </typeAliases>

    <!--设置,eg 日志或其他-->
<!--    <settings>-->
<!--        <setting name="" value=""/>-->
<!--    </settings>-->

</configuration>

```

总结：

第一步，编写数据源配置

第二步，sqlSessionFactory

第三步，sqlSessionTemplate

第四步，需要给接口加实现类

第五步，将自己写的实现类，注入到Spring中

最后测试使用。 



# 13、事务

## 1、回顾事务

+ 把一组业务当成一个业务来做，要么都成功，要么都失败
+ 事务在项目开发中，十分的重要，涉及到数据的一致性问题，不能马虎
+ 确保完整性和一致性



事务ACID原则

> **ACID**，是指[数据库管理系统](https://zh.wikipedia.org/wiki/数据库管理系统)（[DBMS](https://zh.wikipedia.org/wiki/DBMS)）在写入或更新资料的过程中，为保证[事务](https://zh.wikipedia.org/wiki/数据库事务)（transaction）是正确可靠的，所必须具备的四个特性：[原子性](https://zh.wikipedia.org/w/index.php?title=原子性&action=edit&redlink=1)（atomicity，或称不可分割性）、[一致性](https://zh.wikipedia.org/wiki/一致性_(数据库))（consistency）、[隔离性](https://zh.wikipedia.org/wiki/隔離性)（isolation，又称独立性）、[持久性](https://zh.wikipedia.org/w/index.php?title=持久性&action=edit&redlink=1)（durability）。
>
> 在数据库系统中，一个事务是指：由一系列数据库操作组成的一个完整的逻辑过程。例如银行转帐，从原账户扣除金额，以及向目标账户添加金额，这两个数据库操作的总和，构成一个完整的逻辑过程，不可拆分。这个过程被称为一个事务，具有ACID特性。ACID的概念在[ISO](https://zh.wikipedia.org/wiki/ISO)/IEC 10026-1:1992文件的第四段内有所说明。

+ 原子性
+ 一致性
+ 隔离性
+ 持久性: 事务一旦提交，无论系统发生什么问题，结果都不会被影响，被持久化的写到存储器中

> - 原子性（Atomicity）：一个事务（transaction）中的所有操作，或者全部完成，或者全部不完成，不会结束在中间某个环节。事务在执行过程中发生错误，会被[回滚](https://zh.wikipedia.org/wiki/回滚_(数据管理))（Rollback）到事务开始前的状态，就像这个事务从来没有执行过一样。即，事务不可分割、不可约简。[[1\]](https://zh.wikipedia.org/wiki/ACID#cite_note-acid-1)
> - [一致性](https://zh.wikipedia.org/wiki/一致性_(数据库))（Consistency）：在事务开始之前和事务结束以后，数据库的完整性没有被破坏。这表示写入的资料必须完全符合所有的预设[约束](https://zh.wikipedia.org/wiki/数据完整性)、[触发器](https://zh.wikipedia.org/wiki/触发器_(数据库))、[级联回滚](https://zh.wikipedia.org/wiki/级联回滚)等。[[1\]](https://zh.wikipedia.org/wiki/ACID#cite_note-acid-1)
> - [事务隔离](https://zh.wikipedia.org/wiki/事務隔離)（Isolation）：数据库允许多个并发事务同时对其数据进行读写和修改的能力，隔离性可以防止多个事务并发执行时由于交叉执行而导致数据的不一致。事务隔离分为不同级别，包括未提交读（Read uncommitted）、提交读（read committed）、可重复读（repeatable read）和串行化（Serializable）。[[1\]](https://zh.wikipedia.org/wiki/ACID#cite_note-acid-1)
> - 持久性（Durability）：事务处理结束后，对数据的修改就是永久的，即便系统故障也不会丢失。[[1\]](https://zh.wikipedia.org/wiki/ACID#cite_note-acid-1)

https://zh.wikipedia.org/wiki/ACID



案例

写了一个错误的delete sql语句，并对应到了delete方法中，调用delete方法前插入正确的其他的代码。

一组事务中，之前插入的成功了，删除的失败了。最终的结果在数据库加入了插入的，这个是我们不想要的，需要遵从原子性。



**怎么解决 ?**

## 2、Spring中的事务管理

+ **声明式事务：AOP**
+ 编程式事务：需要在代码中进行事务管理，try catch(){rollback}.



**配置**

```html
xmlns:tx="http://www.springframework.org/schema/tx"
    http://www.springframework.org/schema/tx
    http://www.springframework.org/schema/tx/spring-tx-4.0.xsd">
```



![image-20211011004049830](/Users/duanyihan/Library/Application Support/typora-user-images/image-20211011004049830.png)



**Propagation**

1. 简述
            在声明式的事务处理中，要配置一个切面，其中就用到了propagation，表示打算对这些方法怎么使用事务，是用还是不用，其中propagation有七种配置，REQUIRED、SUPPORTS、MANDATORY、REQUIRES_NEW、NOT_SUPPORTED、NEVER、NESTED。默认是REQUIRED。

2. Spring中七种Propagation类的事务属性详解：

 REQUIRED：支持当前事务，如果当前没有事务，就新建一个事务。这是最常见的选择。 

 SUPPORTS：支持当前事务，如果当前没有事务，就以非事务方式执行。 

 MANDATORY：支持当前事务，如果当前没有事务，就抛出异常。 

 REQUIRES_NEW：新建事务，如果当前存在事务，把当前事务挂起。 

 NOT_SUPPORTED：以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。 

 NEVER：以非事务方式执行，如果当前存在事务，则抛出异常。 

 NESTED：支持当前事务，如果当前事务存在，则执行一个嵌套事务，如果当前没有事务，就新建一个事务。


3. 注意事项
这些配置将影响数据存储，必须根据情况选择。
————————————————
版权声明：本文为CSDN博主「格子间里格子衫」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/sayoko06/article/details/79164858

**UserMapper.xml**

```html
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.yihan.mapper.UserMapper">

    <select id="selectUser" resultType="user">
        select * from mybatis.user;
    </select>

    <insert id="addUser" parameterType="user">
        insert into mybatis.user (id, name, pwd) VALUES (#{id}, #{name}, #{pwd});
    </insert>

    <delete id="deleteUser" parameterType="int">
        delete from mybatis.user where id=#{id};
    </delete>
</mapper>


```
**spring-dao.xml**

```html
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/aop
    http://www.springframework.org/schema/aop/spring-aop.xsd
    http://www.springframework.org/schema/tx
    http://www.springframework.org/schema/tx/spring-tx-4.0.xsd">

    <!-- DataSource: 使用Spring的数据源替换Mybatis配置 c3p0 dbcp druid
    这里我们使用spring提供的JDBC-->
    <bean id="datasource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC"/>
        <property name="username" value="root"/>
        <property name="password" value="123456"/>
    </bean>

    <!--sqlSessionFactory-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="datasource"/>
        <!--绑定mybatis配置文件-->
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <property name="mapperLocations" value="classpath:com/yihan/mapper/*.xml"/>
    </bean>

    <!--sqlSessionFactory就是我们使用的sqlSession-->
    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
        <!--只能使用构造器注入，因为他没有set方法-->
        <constructor-arg index="0" ref="sqlSessionFactory"/>
    </bean>

    <bean id="userMapper" class="com.yihan.mapper.UserMapperImpl">
        <property name="sqlSessionFactory" ref="sqlSessionFactory"/>
    </bean>

    <!--配置声明式事务-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="datasource"/>
    </bean>

    <!--结合AOP，实现事务的织入： 增删改查的时候自动加入事务-->
    <!--配置事务通知-->
    <tx:advice id="txAdvice" transaction-manager="transactionManager">
        <!--给哪些方法配置事务-->
        <!--配置事务的传播特性，new-->
        <tx:attributes>
            <tx:method name="add" propagation="REQUIRED"/>
            <tx:method name="delete" propagation="REQUIRED"/>
            <tx:method name="update" propagation="REQUIRED"/>
            <!--query方法对数据库只能read-->
            <tx:method name="query" read-only="true"/>
            <tx:method name="*" propagation="REQUIRED"/>
        </tx:attributes>
    </tx:advice>

    <!--配置事务切入-->
    <aop:config>
        <aop:pointcut id="txPointcut" expression="execution(* com.yihan.mapper.*.*(..))"/>
        <aop:advisor advice-ref="txAdvice" pointcut-ref="txPointcut"/>
    </aop:config>

</beans>
```



为什么需要事务：

+ 如果不配置事务，可能存在数据提交不一致的情况；
+ 如果我们不在spring中配置声明式事务，就需要在代码中手动配置事务
+ 事务在项目开发中十分重要，涉及到数据的一致性和完整性
