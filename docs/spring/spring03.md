# 夯实 AOP 编程

> [!NOTE]
>
> 介绍有关 AOP 切面编程相关概念及其术语，以及基本代码实现及其实现原理

# 一、AOP 概述

AOP全称【Aspect Oriented Programming】意为 **面向切面编程**，通过预编译和运行期间通过 **动态代理** 来实现程序功能统一维护的技术。AOP 思想是 OOP【面向对象】的延续，在 OOP 中以类【class】作为基本单元, 而 AOP 中的基本单元是 Aspect【切面】，AOP 是软件行业的热点，也是 Spring 框架中的一个重要内容。

## 1.1 什么是 AOP

在大多数的业务中都需要验证用户是否登录才可以访问接口内容，如果没有登录或没有权限就会做出对应提示，比如下方的添加用户和修改用户的功能：

![image.png](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407110839406.png)

![image.png](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407110839571.png)

从以上流程可以看出，判断权限和记录日志在方法中都需要调用，如果每个流程中都加入判断权限和记录日志的代码有两个弊端：

- 每个业务代码中都需要调用相同的权限判断功能比较 **重复**，比较冗余
- 业务代码中调用非业务功能，增加 **耦合**，**污染** 业务流程

由此 AOP 思想就出现了，理想的架构就是将 **权限判断**、**记录日志** 这些共用功能抽离到一个 **切片** 中，等到需要时再 **织入** 对象中去，从而改变其原有的行为。

![image.png](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407110840060.png)

> [!TIP]
>
> **AOP** 其实只是 **OOP** 的补充而已。OOP 从横向上区分出一个个的类来，而 AOP 则从纵向上向对象中加入特定的代码。有了AOP，OOP 变得立体了。从技术上来说，AOP 基本上是通过代理模式实现。

## 1.2 AOP 相关术语

![image.png](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407110841669.png)

### 1.2.1 通知/增强 Advice

就是增强的功能，比如我们的程序中需要添加日志，事务等功能，我们当然需要将代码写好，封装到中，我们写好的这个方法就是通知，也可以称为 **增强**，通知分为前置通知，后置通知，环绕通知，异常通知等，说明加在连接点的什么位置，什么时候调用

### 1.2.2 连接点 Join point

可以增强的功能，比如添加、修改、删除等方法都可以被增强。

### 1.2.3 切点 pointcut

实际上增强的方法，如上实际增强添加、删除功能，这个添加、删除就是切点。也称为切入点。需要使用表达式配置

### 1.2.4 切面 Aspect

切点、增强所在的那个类叫切面，这些代码需要编写出来，这个配置的类就是切面

### 1.2.5 引入 Introduction

允许我们向现有的类添加新方法属性。这不就是把切面（也就是新方法属性：通知定义的）用到目标类中嘛

### 1.2.6 目标对象

引入中所提到的目标类，也就是要被通知的对象，也就是真正的业务逻辑，他可以在毫不知情的情况下，被咱们织入切面。而自己专注于业务本身的逻辑。

### 1.2.7 代理 proxy

AOP 通过代理模式实现增强，会创建出来一个代理对象，融合了目标对象和增强，执行时使用的是这个新的代理对象。在 Spring AOP 中, 一个 AOP 代理是一个 JDK 动态代理对象或 CGLIB 代理对象

### 1.2.8 织入 weaving

把 **切面** 应用到 **目标对象** 来创建 **新的代理对象** 的过程。根据不同的实现技术, AOP 织入有三种方式:

- **编译器织入**：这要求有特殊的 Java 编译器
- **类装载期织入**：这需要有特殊的类装载器
- **动态代理织入**：在运行期为目标类添加增强生成代理类的方式

Spring 采用动态代理织入, 而 AspectJ 采用编译器织入和类装载期织入

# 二、代码实现

以用户模块为例，有添加、修改、删除、查询方法，对其中方法按照一定规则增强

## 2.1 基本实现

1. 引入依赖

Spring 中要使用 AOP 需要引入aspectjweaver 依赖，如果是 SpringBoot 引入spring-boot-starter-aop 依赖，就已经包含了aspectjweaver

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

![image-20240711084855660](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407110848654.png)

2. 接口和实现类

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private int id;
    private String name;
    private int age;
    private String email;
}
```

```java
public interface AccountService {
    List<Account> list();

    int add(Account account);

    int update(Account account);

    int delete(int id);
}


@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Override
    public List<Account> list() {
        log.info("查找用户");
        return List.of();
    }

    @Override
    public int add(Account account) {
        log.info("添加用户");
        return 0;
    }

    @Override
    public int update(Account account) {
        log.info("更新用户");
        return 0;
    }

    @Override
    public int delete(int id) {
        log.info("删除用户");
        return 0;
    }
}
```

3. 增强

先提供一个权限校验增强和一个日志记录增强，会使用一下几个注解：

- **@Aspect**：标明类是一个 **切面** 类，里边写增强的方法和配置切入点
- **@Before**：前置通知，执行方法前执行
- **@After**：后置通知，执行方法后，返回退出前执行
- **@AfterReturning**：后置增强，方法正常退出时执行
- **@AfterThrowing**：异常抛出增强，抛出异常时执行
- **@Around**：环绕增强，方法前后都执行

4. 权限校验

```java
@Aspect
@Component
@Slf4j
public class PermissionsAdvice {

    @Before("execution(* jzxy.cbq.service.AccountService.add(..)) || " +
            "execution(* jzxy.cbq.service.AccountService.update(..)) || " +
            "execution(* jzxy.cbq.service.AccountService.delete(..))")
    public void checkPermission() {
        log.info("=============== 权限校验 =================");
    }
}
```

![image-20240711091128373](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407110911435.png)

5. 日志记录

```java
@Aspect
@Component
@Slf4j
public class LogAdvice {

    @After("execution(* jzxy.cbq.service.AccountService.*(..))")
    public void insertLog() {
        log.info("=============== 日志记录 =================");
    }
}
```

![image-20240711091151031](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407110911032.png)

6. 测试

```java
@SpringBootApplication
@Slf4j
public class Demo07SpringAopApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Demo07SpringAopApplication.class, args);

        AccountService service = context.getBean(AccountService.class);
        Account account = new Account();

        service.add(account);
        service.delete(1);
        service.update(account);
        service.list();

        log.info("Demo07SpringAopApplication run successful ");
    }
}
```

![image-20240711091249192](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407110912126.png)

## 2.2 环绕通知

环绕通知会在方法执行前后分别调用，比如要计算一个方法执行耗时就可以使用环绕通知

```java
@Aspect
@Component
@Slf4j
public class ExecuteTimeAdvice {
    /**
     * 获取执行时间，在执行之前
     */
    @Around("execution(* jzxy.cbq.service.AccountService.*(..))")
    public Object getTimeLong(ProceedingJoinPoint joinPoint) {
        Object result = null;
        long startTime = System.currentTimeMillis();
        // 前置业务代码
        log.info("执行前时间 =========> {}", startTime);
        try {
            // 执行目标方法
            result = joinPoint.proceed();
        } catch (Throwable e) {
            if (log.isDebugEnabled()) {
                log.debug(e.getMessage(), e);
            }
        }
        // 后置业务执行代码
        long endTime = System.currentTimeMillis();
        log.info("执行耗时 =========> {} 毫秒", endTime - startTime);
        return result;
    }
}
```

![image-20240711091614210](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407110916145.png)

## 2.3 切点表达式

上边写的 **execution(* jzxy.cbq.service.AccountService.*(..))** 就是切点表达式，AspectJ ⽀持三种通配符：

- ***** ：匹配任意字符，只匹配⼀个元素（包，类，或⽅法，⽅法参数）
- **..** ：匹配任意字符，可以匹配多个元素 ，在表示类时，必须和 * 联合使⽤
- **+** ：表示按照类型匹配指定类的所有类，必须跟在类名后⾯，如 jzxy.cbq.service.AccountService+，表示继承该类的所有子类包括本身

切点表达式由切点函数组成，其中 **execution()** 是最常⽤的切点函数，⽤来匹配⽅法，语法为：

execution(<修饰符><返回类型><包.类.⽅法(参数)><异常>)

- 修饰符：一般省略

  - public：公共方法

  - *：任意方法

- 返回类型：

  - void：无返回类型

  - 数据类型，如 String、int 等等

  - *：任意类型

- 包：

  - **jzxy.cbq.service**：固定包

  - **jzxy.cbq.\*.service**：jzxy.cbq 包下任意包中的 service 子包

  - **jzxy.cbq..**：jzxy.cbq下的所有子包，包括自己

- 类：

  - UserService：指定类

  - *Impl：以 Impl 结尾的类

  - User*：以 User 开头的类

  - *：任意

- 方法名：不可省略

  - addUser：固定方法

  - add*：以 add 开头的方法

  - *User：以 User 结尾的方法

  - *：任意方法

- 参数：

  - ()：无参

  - (数据类型)：指定一个数据类型

  - (数据类型,数据类型)：指定两个数据类型，其他以此类推

  - (..)：任意参数

- throws：异常类型，一般省略不写

### 2.3.1 表达式示例

- execution(* jzxy.cbq.demo.User.*(..)) ：匹配 User 类⾥的所有⽅法
- execution(* jzxy.cbq.demo.User+.*(..)) ：匹配该类的⼦类包括该类的所有⽅法
- execution(* jzxy.cbq.*.*(..)) ：匹配 jzxy.cbq 包下的所有类的所有⽅法
- execution(* jzxy.cbq..*.*(..)) ：匹配 jzxy.cbq 包下、⼦孙包下所有类的所有⽅法
- execution(* addUser(String, int)) ：匹配 addUser ⽅法，且第⼀个参数类型是 String，第⼆个参数类型是 int

### 2.3.2 其它切点表达式

- arg()：限定连接点方法参数
- @args()：通过连接点方法参数上的注解进行限定
- execution()：用于匹配是连接点的执行方法
- this() ：限制连接点匹配 OP Bean 用为指定的类型
- target：目标对象（即被代理对象）
- @target()：限制目标对象的配置了指定的注解
- within：限制连接点匹配指定的类型
- @within()：限定连接点带有匹配注解类型
- @annotation()：限定带有指定注解的连接点

> [!NOTE]
>
> 当然表达式之间可以通过 ||【或者】或者 &&【并且】连接，所有的表达式可通过 [官网查看](https://docs.spring.io/spring-framework/reference/core/aop/ataspectj/pointcuts.html#aop-pointcuts-designators)

```java
// 匹配指定包中的所有的方法, 但不包括子包
within(jzxy.cbq.service.*)
 
// 匹配指定包中的所有的方法, 包括子包
within(jzxy.cbq.service..*)
 
// 匹配当前包中的指定类中的方法
within(UserService)
 
// 匹配一个接口的所有实现类中的实现的方法
within(UserDao+)
 
// 匹配以指定名字结尾的 Bean 中的所有方法
bean(*Service)
 
// 匹配以 Service 或 ServiceImpl 结尾的 bean
bean(*Service || *ServiceImpl)
 
// 匹配名字以 Service 开头, 并且在包 jzxy.cbq.service 中的 bean
bean(*Service) && within(jzxy.cbq.service.*)
```

### 2.3.3 单独定义切点

切点支持单独配置，之后引用到需要该切点的增强

```java
@Aspect
@Component
@Slf4j
public class LogAdvice {
    // 定义切点
    @Pointcut("execution(* jzxy.cbq.service.AccountService.*(..))")
    public void afterLog() {
    }
    // 使用切点到增强上
    @After("afterLog()")
    public void insertLog() {
        log.info("=============== 日志记录 =================");
    }
}
```

![image-20240711145916960](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407111459087.png)

# 三、Spring AOP 原理

AOP 的实现方式其实是代理模式，代理模式是给某一个对象提供一个代理对象，并由代理对象控制对原对象的引用，代理模式分为 **静态代理** 和 **动态代理**

- 静态代理：是由程序员创建或特定工具自动生成源代码，再对其编译。在程序运行之前，代理类 .class 文件就已经被创建了
- 动态代理：在程序运行时通过反射机制动态创建

Spring AOP 的原理是构建在 **动态代理** 基础上，Spring 对 AOP 的支持局限于 **方法级别**。Spring AOP 支持 **JDK Proxy** 和 **CGLIB** 方式实现动态代理。

- 默认情况下，实现了接口的类，使用 AOP 会基于 JDK ⽣成代理类
- 没有实现接⼝的类，会基于 CGLIB ⽣成代理类

## 3.1 静态代理

```java
public interface StaticProxyService {
    void staticProxyMethod();
}
```

```java
@Service
@Slf4j
public class StaticProxyServiceImpl implements StaticProxyService {
    @Override
    public void staticProxyMethod() {
        log.info("=============== 静态代理对象原对象方法 ===============");
    }
}
```

```java
@Service
@Slf4j
@RequiredArgsConstructor
public class StaticProxyServiceProxy implements StaticProxyService {

    private final StaticProxyService staticProxyServiceImpl;

    @Override
    public void staticProxyMethod() {
        log.info("========== 静态代理对象方法 ==========");
        // 调用原对象方法
        staticProxyServiceImpl.staticProxyMethod();
    }
}
```

```java
    private static void staticProxy(ApplicationContext context) {
        StaticProxyServiceProxy proxy = context.getBean(StaticProxyServiceProxy.class);
        proxy.staticProxyMethod();
    }
```

![image-20240711092612628](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407110926825.png)

> [!NOTE] 小结
>
> - 优点：可以做到在符合开闭原则的情况下对目标对象进行功能扩展。
> - 缺点：需要为每一个服务都得创建代理类，工作量太大，不易管理。同时接口一旦发生改变，代理类也得相应修改。

## 3.2 JDK 动态代理

不需要生成实现接口，使用 JDK 的 API 在内存中构建代理对象

```java
public interface JdkProxyService {
    void jdkProxyMethod();
}
```

```java
@Service
@Slf4j
public class JdkProxyServiceImpl implements JdkProxyService {
    @Override
    public void jdkProxyMethod() {
        log.info("=============== JDK 动态代理接口方法 ===============");

    }
}
```

```java
@Service
@Slf4j
@RequiredArgsConstructor
public class JdkProxyServiceProxy implements InvocationHandler {

    private final JdkProxyService target;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        log.info("========== JDK 动态代理 ==========");
        // 调用被代理对象方法
        result = method.invoke(target, args);
        return result;
    }
}
```

```java
    private static void jdkProxy(ApplicationContext context) {
        JdkProxyService service = context.getBean(JdkProxyService.class);
        JdkProxyServiceProxy proxy = context.getBean(JdkProxyServiceProxy.class);
        ClassLoader classLoader = service.getClass().getClassLoader();
        Class<?>[] interfaces = service.getClass().getInterfaces();
        JdkProxyService jdkProxyService = (JdkProxyService)Proxy.newProxyInstance(classLoader, interfaces, proxy);
        jdkProxyService.jdkProxyMethod();
    }
```

![image-20240711144111094](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407111441170.png)

> [!NOTE] 小结
>
> - 实现 InvocationHandler 接口重写 invoke 方法
> - 创建代理对象使用 Proxy 类的 newProxyInstance 方法，传入实现类类加载器，接口和代理类的对象

## 3.3 CGLB 动态代理

```java
public interface CGLIBProxyService {
    void cglIbMethod();
}
```

```java
@Service
@Slf4j
public class CGLIBProxyServiceImpl implements CGLIBProxyService {
    @Override
    public void cglIbMethod() {
        log.info("=============== CGLIB 代理实现类方法 ===============");

    }
}
```

```java
@Slf4j
@RequiredArgsConstructor
public class CGLIBProxyInterceptor implements MethodInterceptor {
    //被代理对象
    private final CGLIBProxyService target;
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        log.info("============= CGLIB 代理方法 =============");
        //通过 cglib 的代理⽅法调⽤
        return proxy.invoke(target, args);
    }
}
```

```java
    private static void cglibProxy(ApplicationContext context) {
        CGLIBProxyService service = context.getBean(CGLIBProxyService.class);
        CGLIBProxyService proxy = (CGLIBProxyService)Enhancer.create(service.getClass(), new CGLIBProxyInterceptor(service));
        proxy.cglIbMethod();
    }
```

![image-20240711144816689](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407111448760.png)

## 3.4 JDK 和 CGLB 实现的区别

- **JDK 实现**：要求被代理类必须实现接⼝，之后是通过 InvocationHandler 及 Proxy，在运⾏时动态的在内存中⽣成了代理类对象，该代理对象是通过实现同样的接⼝实现（类似静态代理接⼝实现的⽅式），只是该代理类是在运⾏期时，动态的织⼊统⼀的业务逻辑字节码来完成。
- **CGLIB 实现**：被代理类可以不实现接⼝，是通过继承被代理类，在运⾏时动态的⽣成代理类对象 (三方框架，一般性能有优势)。
