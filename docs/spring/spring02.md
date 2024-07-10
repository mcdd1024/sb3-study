# 夯实 IOC 容器

>[!NOTE]
> 学习如何通过 IOC 将对象交与 Spring 进行管理，学习常见的几种 IOC 容器
> 学习 SpringBoot 如何管理配置文件
> 依赖注入、条件装配

# 一、IOC 概述

IOC 的全称为 Inversion of Control，意为 **控制反转**，有的地方称为 IOC 容器，它是一项对象生成、获取和管理的技术，Java 作为一门面向对象的语言，对象的管理就至关重要。在传统的 Java 编程中，对象一般通过 new 构建，而 Spring 通过描述来构建对象。

项目中会有各种各样的对象，这些对象也不是独立存在互不相关，Spring 提供了**依赖注入** Dependency Injection，DI 来管理各个对象之间的关系。

常见的对象关系有：依赖、关联、聚合、组合、继承、实现。

# 二、SpringBoot 中常见的集中 IOC 容器

Spring 的 IOC 容器用来创建、存储、销毁对象并维护对象之间的关系，IOC 容器也称为 **Bean 容器** 该容器体系结构如下图所示：

![BeanFactory.png](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407102203814.png)

Spring 通过工厂模式创建对象，涉及到的接口和类非常多，其中我们经常打交道的有：

- **BeanFactory**：定义一系列获取 Bean 的方法，根据不同参数、条件去获取 Bean，是整个 IOC 容器体系的根接口
- **ApplicationContext**：再 BeanFactory 基础上扩展了事件发布、资源加载、环境参数、国际化消息等功能

![image-20240710220744057](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407102207199.png)

- **ClassPathXmlApplicationContext**：从类路径上读取 Spring 声明文件来创建Bean
- **FileSystemXmlApplicationContext**：从文件中 Spring 声明文件来创建 Bean
- **AnnotationConfigApplicationContext**：通过注解来创建 Bean，需要指定要识别的包名，会识别通过诸如 @Configuration、@Component、@Service、@Bean 等描述的类

接下来简单通过以上三种方式去创建、获取和使用 Bean，后边就统一使用注解的方式操作了，这也是 SpringBoot 所推荐的方式

## 2.1 ClassPathXmlApplicationContext

通过最原始的 xml 文件管理 Bean，需要使用 **ClassPathXmlApplicationContext** 容器

![image-20240710221030911](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407102210018.png)

1. entity

```java
@Data
public class Student {
    private String name;
    private int age;
    private String email;
}
```

2. application-context.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="cbq" class="jzxy.cbq.entity.Student">
        <property name="name" value="cbq"/>
        <property name="age" value="22"/>
        <property name="email" value="2024cbq@gmail.com"/>
    </bean>
</beans>
```

3. 测试

```java
    /**
     * 通过 classPathXML 方式获取 context
     */
    private static void getContextByClassPathXML() {
        log.info("============ getContextByClassPathXML ===============\n");
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("application-context.xml");
        Student student = context.getBean(Student.class);
        System.out.println("student = " + student + "\n");
        log.info("============ getContextByClassPathXML ===============\n");
    }

```

![image-20240710221112390](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407102211438.png)

## 2.2 FileSystemXmlApplicationContext

使用 **FileSystemXmlApplicationContext** 的话参数中填写文件路径，其余相同

```java
    /**
     * 通过 fileSystemXML 方式获取 context
     */
    private static void getContextByFileSystemXML() {
        log.info("============ getContextByFileSystemXML ===============\n");
        FileSystemXmlApplicationContext context = 
                new FileSystemXmlApplicationContext("E:\\code-dev\\sb3-study\\demo02-ioc\\src\\main\\resources\\application-context.xml");
        Student student = context.getBean(Student.class);
        System.out.println("student = " + student + "\n");
        log.info("============ getContextByFileSystemXML ===============\n");
    }
```

![image-20240710221226537](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407102212574.png)

## 2.3 AnnotationConfigApplicationContext

此处介绍两种创建 Bean 方式，一种是在 AnnotationConfigApplicationContext 的构造方法中传入包名，另一种是在构造方法中传入配置类

### 2.3.1 通过传入包名或 @ComponentScan 创建 Bean

在需要创建的 Bean 上通过 @Component 或者 @Configuration 等注解声明 bean，相当于 bean 标签，通过 @Value 注解注入值。

```java
@Data
@Component("cbq")
public class Student {
    @Value("name-annotation")
    private String name;
    @Value("22")
    private int age;
    @Value("email-annotation")
    private String email;
}
```

在启动类中使用 **AnnotationConfigApplicationContext**，传入包名即可扫描包下所有关于 Bean 的注解

> [!TIP] 注意：
>
> AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("jzxy.cbq"); // 也可以写成 new AnnotationConfigApplicationContext(Demo02IocApplication.class); 前提是需要 Demo02IocApplication.class 上标有 @ComponentScan("jzxy.cbq")

```java
/**
     * 通过 AnnotationConfig 方式获取 context
     */
    private static void getContextByAnnotationConfig() {
        log.info("============ getContextByAnnotationConfig ===============\n");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("jzxy.cbq"); // 也可以写成 new AnnotationConfigApplicationContext(Demo02IocApplication.class); 前提是需要 Demo02IocApplication.class 上标有 @ComponentScan("jzxy.cbq")
        Student student = context.getBean(Student.class);
        System.out.println("student = " + student + "\n");
        log.info("============ getContextByAnnotationConfig ===============\n");
    }
```

![image-20240710222100824](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407102221734.png)

### 2.3.2 通过 @Bean 注解创建 Bean

通过 Bean 注解创建对象的话，类属性值的注入不需要通过 @Value 注解，否则 Bean 注入的值是 Value 注解注入的，而是通过 set 方法进行赋值

1. entity

```java
@Data
public class Book {
    private String name;
    private String author;
    private int price;
}
```

2. 创建配置类并用 @Configuration 注解标记

```java
@Configuration
public class BookConfiguration {

    @Bean
    public Book initBook() {
        Book book = new Book();
        book.setName("明朝那些事儿");
        book.setAuthor("当年明月");
        book.setPrice(100);
        return book;
    }

}
```

3. 测试

```java
    /**
     * 通过 AnnotationConfig 方式获取 context
     */
    private static void getContextByAnnotationConfig() {
        log.info("============ getContextByAnnotationConfig ===============\n");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("jzxy.cbq"); // 也可以写成 new AnnotationConfigApplicationContext(Demo02IocApplication.class); 前提是需要 Demo02IocApplication.class 上标有 @ComponentScan("jzxy.cbq")
        Student student = context.getBean(Student.class); 
        Book book = context.getBean(Book.class); // [!code ++]
        System.out.println("student = " + student + "\n");
        System.out.println("book = " + book + "\n"); // [!code ++]
        log.info("============ getContextByAnnotationConfig ===============\n");
    }
```

![image-20240710221402373](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407102214443.png)

# 三、装配 Bean 的最佳实践

装配 Bean 使用 **全注解 + 配置类的方式** 装配 **自定义Bean**，是目前通用最优的方式了，因为 SpringBoot 用的就是这种方式。如果要修改 SpringBoot 的默认配置，也是通过配置文件或者 JavaConfig 的方式修改。比如通过数据源的案例

1. entity

```java
@Data
public class MySQLDataSource {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
}
```

2. config

配置类一般使用 **@Configuration** 注解描述，配置类通常用来配置系统信息或者诸如：数据库、消息队列、缓存等的第三方组件

```java
@Configuration
public class DataSourceConfiguration {

    @Bean
    public MySQLDataSource initDataSource() {
        MySQLDataSource dataSource = new MySQLDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }
}
```

3. 测试

主类上使用 **@ComponentScan** 注解来配置要扫描的包，所有的类都应该放到此包下

> [!TIP] @SpringBootApplication 注解
>
> 该注解是一个组合注解其中就包含 @ComponentScan 默认情况下会扫描该类所在的包及其子包

![image-20240710223134992](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407102231872.png)

```java
@SpringBootApplication // 该注解是一个组合注解其中就包含 @ComponentScan 默认情况下会扫描该类所在的包及其子包
@Slf4j
public class Demo03BeanManageRecommendWayApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Demo03BeanManageRecommendWayApplication.class, args);

        MySQLDataSource dataSource = context.getBean(MySQLDataSource.class);
        System.out.println("dataSource = " + dataSource);

        log.info("Demo03BeanManageRecommendWayApplication run successful ");
    }
}
```

![image-20240710222844760](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407102228651.png)

> [!NOTE] 小结
>
> - 装配 Bean 时一般在一个主类上通过 @ComponentScan 设置包扫描，然后通过 @Value 进行赋值
> - 如果是第三方的配置通过配置类搭配 set 方法装载 Bean，使用 @Configuration 注解描述类

# 四、配置文件的管理和读取

对于 SpringBoot 项目来说，很多配置项都是做了默认配置的，比如 tomcat 端口号，日志的输出规则，我们如果想要修改默认配置，要么自定义配置类覆盖原配置，要么在配置文件中修改参数，覆盖原配置。

SpringBoot 中的配置文件名字按照约定来说叫 **application.properties** 或者 **application.yml** 两种，放到 **resources** 目录下，这样项目启动时会自动读取，无需做其他配置，贴出两种配置文件，只是写法不同，推荐使用 yml 格式文件，是缩进类型的不需要写重复的公用前缀

## 4.1 配置文件的基本使用

properties 文件的使用

```properties
spring.application.name=Demo04ConfigFile
spring.profiles.active=dev
```

其中 spring.profiles.active 可指定生效的配置文件，其命名规则默认包括：`application-dev.yaml` 、`application-prod.yaml`、`application-test.yaml` 分别用于表示 开发环境、生产环境、测试环境

yaml 文件的使用

```yaml
cbq:
  name: cbq
  age: 22
  hobby:
    - 唱
    - 跳舞
    - Rap
    - 篮球
idol: cxk
```

我们可以在配置文件中对一些参数进行配置，如 tomcat 端口号等

![image-20240710223654489](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407102236603.png)

下面我们介绍如何读取配置文件中的值，一般有两种方式：

- 通过 @Value 获取
- 通过 @ConfigurationProperties 进行获取

下面我们将通过 @Value 读取 idol 通过 @ConfigurationProperties 读取以 cbq 为前缀的值

```java
@Configuration // 首先需要通过 @Configuration 注册为 Bean 交由 Spring 管理然后才能读取配置文件中的值
@ConfigurationProperties(prefix = "cbq")
@Data
public class CBQ {
    private String name;
    private Integer age;
    @Value("${idol}")
    private String idol;
    private List<String> hobby;
}
```

```java
cbq:
  name: cbq
  age: 22
  hobby:
    - 唱
    - 跳舞
    - Rap
    - 篮球
idol: cxk

```

```java
@SpringBootApplication
@Slf4j
public class Demo04ConfigFileApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Demo04ConfigFileApplication.class, args);

        CBQ cbq = context.getBean(CBQ.class);
        System.out.println("cbq = " + cbq);

        log.info("Demo04ConfigFileApplication run successful");
    }

}
```

![image-20240710224401434](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407102244182.png)

> [!TIP] 注意
>
> 在上面的例子中我们已经通过 @Value 读取 idol 通过 @ConfigurationProperties 读取以 cbq 为前缀的值但需要注意的是：
>
> - 需要赋值的类首先需要通过 @Configuration 注册为 Bean 交由 Spring 管理然后才能读取配置文件中的值
> - 需要有 get|set 方法

## 4.2 读取项目中的自定义配置文件

我们上边读取的文件是 springboot 约定好的文件【application.yml/properties】，如果定义一个 application-db.properties 文件。这个文件不是 SpringBoot 约定好的，此时就需要指定文件读取，下面我们分别介绍

### 4.2.1 properties 文件的读取

1. 创建 application-db.properties

```properties
db.driverClassName=com.mysql.cj.jdbc.Driver
db.url=jdbc:mysql://localhost:3306
db.username=root
db.password=root

```

2. 创建实体类

此处我们通过 @PropertySource("classpath:application-db.properties") 指定自定义文件其中 `classpath:` 用于标明该文件位于 resources 类路径下

```
@Data
@Configuration
@ConfigurationProperties(prefix = "db")
@PropertySource("classpath:application-db.properties")
public class MySQLDataSource {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
}
```

3. 获取值

```java
@SpringBootApplication
@Slf4j
public class Demo04ConfigFileApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Demo04ConfigFileApplication.class, args);

        CBQ cbq = context.getBean(CBQ.class);
        MySQLDataSource dataSource = context.getBean(MySQLDataSource.class); // [!code ++]
        System.out.println("cbq = " + cbq);
        System.out.println("dataSource = " + dataSource); // [!code ++]
        
        log.info("Demo04ConfigFileApplication run successful");
    }

}
```

![image-20240710225104476](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407102251497.png)

### 4.2.2 yaml 文件的读取

1. 创建 application-redis.yaml 文件

```yaml
redis:
  host: 127.0.0.1
  port: 6379
```

2. entity

```java
@Data
@Configuration
@ConfigurationProperties(prefix = "redis")
@PropertySource("classpath:application-redis.yaml")
public class Redis {
    private String host;
    private Integer port;
}
```

3. 获取值

```java
@SpringBootApplication
@Slf4j
public class Demo04ConfigFileApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Demo04ConfigFileApplication.class, args);

        CBQ cbq = context.getBean(CBQ.class);
        MySQLDataSource dataSource = context.getBean(MySQLDataSource.class); 
        Redis redis = context.getBean(Redis.class); // [!code ++]
        System.out.println("cbq = " + cbq);
        System.out.println("dataSource = " + dataSource); 
        System.out.println("redis = " + redis); // [!code ++]

        log.info("Demo04ConfigFileApplication run successful");
    }

}
```

![image-20240710225326030](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407102253386.png)

### 4.2.3 通过 Environment 读取

还可以通过 Spring 提供的 Environment 对象获取文件数据，这个对象就霸道了。每一个文件中的值都可以获取到，用法相对较少，因为读取配置文件数据一般用在第三方组件的配置类中，配置类都是相互独立的，不会说读取所有数据，一般也是仅仅获取改配置类所需要的配置数据就可以

```java
public enum LocalEnv {
    JAVA_HOME,
    MVN_HOME,
    GRADLE_HOME
}
```

```java
@SpringBootApplication
@Slf4j
public class Demo04ConfigFileApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Demo04ConfigFileApplication.class, args);

        CBQ cbq = context.getBean(CBQ.class);
        MySQLDataSource dataSource = context.getBean(MySQLDataSource.class);
        Redis redis = context.getBean(Redis.class);
        System.out.println("cbq = " + cbq);
        System.out.println("dataSource = " + dataSource);
        System.out.println("redis = " + redis);

        Environment environment = context.getBean(Environment.class); // [!code ++]

        String java_home = environment.getProperty(String.valueOf(LocalEnv.JAVA_HOME)); // [!code ++]
        String mvn_home = environment.getProperty(String.valueOf(LocalEnv.MVN_HOME)); // [!code ++]
        String gradle_home = environment.getProperty(String.valueOf(LocalEnv.GRADLE_HOME)); // [!code ++]
        System.out.println("java_home = " + java_home); // [!code ++]
        System.out.println("maven_home = " + mvn_home); // [!code ++]
        System.out.println("gradle_home = " + gradle_home); // [!code ++]


        log.info("Demo04ConfigFileApplication run successful");
    }

}
```

![image-20240710225415468](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407102254513.png)

## 4.3 profile

一般软件有开发环境、测试环境、生产环境，不同的环境有些配置可能不同，比如端口、回调地址、数据库地址、redis地址等，SpringBoot 支持加载不同的配置文件，称之为 profile。通常就是创建不同的配置文件，命名格式为 **application-${profile}.yml**

![image-20240710225544901](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407102255940.png)

默认只会加载 application.yml 文件不会加载其他文件。从日志也可以看出，下方红色框中的日志就表明没有激活任何 profile，使用default

![image-20240710225627445](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407102256405.png)

如果希望使用分环境加载，常见的方式有以下三种方式：

- application.yml 文件中通过 spring.profiles.active 配置

![image-20240710225733454](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407102257412.png)

- IDEA 中配置参数，打包时不生效

![image-20240710225719734](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407102257706.png)

- 启动 jar 命令添加 --spring.profiles.active 配置

# 五、依赖注入



# 六、条件装配

条件装配就是在满足相应条件的时候再装载对应的 Bean，比如连接数据库时当数据源存在的时候再加载连接数据库的 Bean，条件装配可以使用 @Conditional 和其派生注解实现

![image-20240710231927102](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407102319428.png)

我们可以通过下面的小栗子体验一下，下面的 DataSourceConfiguration 中 dateCollectionService() 方法被 @ConditionalOnBean(DataSource.class) 标记，即如果 存在 DataSource这样的 bean 才装配 DataCollectionService

```java
@Data
public class DataSource {

    private String driverClassName;
    private String url;
    private String username;
    private String password;

}
```

```java
@Data
public class DataCollectionService {

    private DataSource dataSource;

    public void collect() {
        String url = dataSource.getUrl();
        String driverClassName = dataSource.getDriverClassName();
        System.out.println("连接数据库 ======> ");
        System.out.println("url ====>" + url);
        System.out.println("driverClassName ===>》" + driverClassName);
    }
}
```

```java
@Configuration
public class DataSourceConfiguration {

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    @Bean
    // 当 DataSource 这个 bean 存在时加载
    @ConditionalOnBean(DataSource.class)
    public DataCollectionService dateCollectionService() {
        DataCollectionService dateCollectionService = new DataCollectionService();
        System.out.println("dataSource() ====> " + dataSource());
        dateCollectionService.setDataSource(dataSource());
        return dateCollectionService;
    }

}
```

```java
@SpringBootApplication
@Slf4j
public class Demo06ConditionalDiApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Demo06ConditionalDiApplication.class, args);

        DataCollectionService service = context.getBean(DataCollectionService.class);

        System.out.println("service = " + service);
        service.collect();

        log.info("Demo06ConditionalDiApplication run successful ");
    }
}
```

![image-20240710231824248](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407102318590.png)
