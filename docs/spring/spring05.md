# 整合日志

> [!NOTE]
>
> - 门面模式介绍
> - SpringBoot 日志结构
> - 日志的基本使用

项目中如果需要输出提示信息一般不建议使用 System.out.println 输出，而是推荐通过日志工具输出，主要原因有以下几点：

- **可配置性**：log 通常允许我们对输出进行更多的配置，例如设置日志级别（如 DEBUG、INFO、WARNING、ERROR 等），这样我们就可以根据需要显示或隐藏不同级别的日志信息。而使用 println 会导致全部信息都被输出，不便于调试和监控。
- **输出目标**：println 通常只会把信息输出到控制台。而 log 可以将日志输出到不同的目标，例如文件、数据库、远程服务器等。这样可以帮助我们更好地存储、检索和分析日志数据。
- **格式化**：log 通常支持对输出信息进行格式化，例如添加时间戳、日志级别、源代码位置等元信息。这些信息在调试和分析问题时非常有用。而使用 println 输出的信息通常较为简单，缺少这些元信息。
- **性能**：log 采用缓冲区和异步进行输出，通常性能较高，而 println 与主线程同步，对性能有一定的影响。
- **跨平台和通用性**：使用 log 可以让代码更具有可移植性和通用性。如果需要将程序移植到其他平台或环境，只需更改日志配置，而无需修改代码中的 println 语句。

而众多的日志框架通常通过门面模式实现，在介绍日志框架使用之前，请容许我先给各位介绍一下门面模式，来深入理解日志框架为什么这么设计，在合适的场景下你也可以通过门面模式开发应用。

# 一、门面模式

门面模式也称外观模式【Facade Pattern】，提供⼀个统⼀的接⼝用来实现内部和外部的通信，使得子系统更加易用。比如我们到饭店吃饭，服务员相当于门面，服务员引导我们入座，点菜，上菜，如果想上卫生间也可以通过服务员询问，而且市面上所有的饭店都如此，即使到不同的饭店也是找服务员就可以了。

如果没有服务员，我们要自找座位，自己找到厨房给厨师说吃什么，每个饭店布局不同，厨房，卫生间的位置也都不一样。就大大增加了就餐难度。

![image.png](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407120238715.png)

## 1.1 门面模式的优点

- 减少了系统的相互依赖.实现了客⼾端与⼦系统的耦合关系,这使得⼦系统的变化不会影响到调⽤它的客⼾端
- 提⾼了灵活性,简化了客⼾端对⼦系统的使⽤难度，客⼾端⽆需关⼼⼦系统的具体实现⽅式，⽽只需要和⻔⾯对象交互即可
- 提⾼了安全性，可以灵活设定访问权限，不在⻔⾯对象中开通⽅法就⽆法访问

## 1.2 日志门面

日志门面只是提供日志服务的统一接口，具体的功能实现还需要具体的日志框架。如果没有门面模式，本系统的日志框架与引入的第三方库的日志框架不同，会有不同的配置文件注明日志的输出位置，级别等。

![未命名文件.png](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407120238607.png)

通过门面模式实现的日志框架，即使切换日志框架，或引入的第三方库使用的日志框架与本系统使用的日志不同对开发者来说还是很友好的。

> [!NOTE]
>
> 常见的 **日志门面** 有Jakarta Commons Logging【JCL】，SLF4J【Simple Logging Facade for Java】，jboss-logging
>
> 常用的 **日志框架 **有Logback、Log4J，Log4J2，JUL【java.util.logging】



# 二、SpringBoot 日志结构

SpringBoot 默认集成 jcl 日志系统，准确来讲是 Spring5 之后，就不需要再额外导入日志包了

![image-20240712024837360](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407120248479.png)

> [!TIP]
>
> SpringBoot 默认使用 logback，支持切换其他的日志框架，在日志适配器中有对应的判断，会检测 Log4j2 和 SLF4J，而 Log4j 已不再支持

![image-20240712024939547](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407120249606.png)

在 spring-boot-starter 依赖中就直接引入了日志

![image-20240712025031653](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407120250625.png)

> [!NOTE]
>
> SpringBoot 默认使用 SLF4J 门面，具体使用 logback 作为默认日志框架

![image-20240712025118978](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407120251897.png)

> [!NOTE]
>
> SpringBoot 中的日志并不是通过自动配置加载，而是通过 **监听器** 的形式加载，在项目启动时就要使用，所以我们呢会看到项目启动时的日志输出就是这个原因啦。而自动配置 xxxAutoConfiguration 时项目启动后加载的

![image-20240712025351575](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407120253611.png)

# 三、日志的基本使用

## 3.1 日志格式

项目启动时自动加载日志，有自己的默认格式

![image-20240712025525328](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407120255286.png)

- 时间和日期，精确到毫秒
- 日志级别：INFO、ERROR、WARN、DEBUG、TRACE
- 进程ID【PID】
- ---：分隔符
- 线程名：哪个线程输出的日志
- 类名：哪个类输出的日志
- 消息：日志输出的信息

默认格式配置在：spring-boot 包 additional-spring-configuration-metadata.json 文件中，如果要修改默认日志格式，在配置文件中修改

```yaml
logging:
  pattern:
    console: '%d{yyyy-MM-dd HH:mm:ss} %-5level [%thread] %logger{15} ====> %msg%n' # yml文件需要加''
```

- %d：格式化日期时间
- %-5level：level 是日志级别

- %5 若字符长度小于 5，则右边用空格填充，%-5 若字符长度小于 5，则左边用空格填充

- %thread：线程名
- %logger：生成日志的类
- %msg：日志信息
- %n：换行

当然也可以单独修改时间

![image-20240712025906266](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407120259430.png)

我们在配置文件中设置这个属性就可以了

```yaml
logging:
  pattern:
    dateformat: yyyy-MM-dd HH:mm:ss # 修改默认的时间格式
```

![image-20240712030159445](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407120302606.png)

## 3.2 日志级别

日志级别由低到高：ALL、TRACE、DEBUG、INFO、WARN、ERROR、FATAL、OFF。指定日志级别之后会打印对应级别和以后级别的日志，比如指定 INFO，则会打印 INFO、WRAN、ERROR、FATAL 级别

- ALL：打印所有日志
- TRACE：追踪框架详细流程日志，一般不使用
- DEBUG：开发调试细节日志
- INFO：关键、感兴趣信息日志
- WARN：警告但不是错误的信息日志，比如: 版本过时
- ERROR：业务错误日志，比如出现各种异常
- FATAL：致命错误日志，比如 jvm 系统崩溃
- OFF：关闭所有日志记录

SpringBoot 默认日志级别为 INFO，引入 lombok 之后，通过类上添加 @Slf4j 注解即可非常方便的输出日志

```java
@RestController
@RequestMapping("logs")
@Slf4j
public class ShowInfo {

    @GetMapping("test")
    public String test1() {
        log.trace("trace 日志");
        log.debug("debug 日志");
        log.info("info 日志");
        log.warn("warn 日志");
        log.error("error 日志");
        return "日志打印";
    }
}
```

![image-20240712030505449](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407120305638.png)

SpringBoot 支持调整全局日志级别，指定包和指定类的日志级别

```yaml
logging:
  level:
    jzxy.cbq.ShowInfo: debug # 调整指定包（类）的日志级别
    root: info # 调整所有类的日志级别为 info
```

![image-20240712030707271](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407120307837.png)

## 3.3 日志分组

如果我们有多个包的日志级别是一样的，配置多个 level 比较冗余，例如

```yaml
logging:
  level:
    jzxy.cbq.controller: info
    jzxy.cbq.service: info
    jzxy.cbq.config: info
```

SpringBoot支持创建分组，设置分组的日志级别

```java
logging:
  group:
    g_info: jzxy.cbq.controller,jzxy.cbq.service,jzxy.cbq.config
  level:
    # 指定分组级别
    g_info: info
```

springboot 默认有三个分组 root、web、sql，其中 root 是所有的包

- web：

  - org.springframework.core.codec

  - org.springframework.http

  - org.springframework.web

  - org.springframework.boot.actuate.endpoint.web

  - org.springframework.boot.web.servlet.ServletContextInitializerBeans

- sql：

  - org.springframework.jdbc.core

  - org.hibernate.SQL

  - orgjooq.tools.LoggerListener

## 3.4 日志输出位置

SpringBoot 默认将日志输出到控制台，在生产环境通常将日志输出到文件中，方便排查线上故障和分析日志，有两个配置

```yaml
logging:
  level:
    jzxy.cbq.ShowInfo: debug # 调整指定包（类）的日志级别
    root: info # 调整所有类的日志级别为 info
  file:
    # 1、只配置文件名会将文件保存到项目目录下
    # 2、可以同时指定目录 + 文件名
     name: test.log
    # name: C:\\test.log
    # 1、指定文件存储目录，存到 C 盘下,默认文件名为 spring.log，不能指定文件名
    # 2、与 name 属性同时存在则 path 不生效，所以该属性基本不用
    # path: C:\
```

![image-20240712031243846](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407120312751.png)

## 3.5 日志的归档和切割

> [!NOTE]
>
> - 归档：将日志按照指定规则存储并生成新的日志文件，继续接收当前日志
> - 切割：但日志文件过大，切割成多个日志文件，方便查询

将日志按照日期存储，防止日志文件随运行时间变得越来越大，导致打开速度极慢不便查看。单单归档还不够，用户增多，会导致一天的的日志也非常多，此时可以通过控制文件大小做切割

> [!TIP]
>
> 不要包含
>
> ```yaml
>   file:
>     # 1、只配置文件名会将文件保存到项目目录下
>     # 2、可以同时指定目录 + 文件名
>      name: test.log
>     # name: C:\\test.log
>     # 1、指定文件存储目录，存到 C 盘下,默认文件名为 spring.log，不能指定文件名
>     # 2、与 name 属性同时存在则 path 不生效，所以该属性基本不用
>     # path: C:\
> ```
>
> 否则会冲突

```yaml
logging:
  level:
    jzxy.cbq.ShowInfo: debug # 调整指定包（类）的日志级别
    root: info # 调整所有类的日志级别为 info
  logback:
    rolling policy:
      # 日志文件保存方式
      file-name-pattern: ${LOG_FILE}.%d{yyyy-MM-dd}.%i.gz
      # 单文件最大大小，超过就要切割
      max-file-size: 1MB
      # 启动时清除存档日志
      clean-history-on-start: false
      # 历史存档日志保存天数
      max-history: 7
```

![image-20240712032655498](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407120326787.png)

# 四、自定义日志

springboot 的配置文件只支持配置 logback，如果项目中使用其他日志框架比如：log4j2，步骤如下：

- 引入 log4j2 启动器，去除默认的日志依赖
- 在 resource 目录下配置 log4j 日志框架的 xml 文件【命名为 log4j2.xml 或 log4j2-spring.xml】

```xml
	<dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <!--     配出默认日志框架       -->
        <exclusions>
            <exclusion>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-logging</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
    <!--    引用 log4j2 框架    -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-log4j2</artifactId>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--Configuration后面的status，这个用于设置log4j2自身内部的信息输出，可以不设置，当设置成trace时，你会看到log4j2内部各种详细输出-->
<!--monitorInterval：Log4j能够自动检测修改配置 文件和重新配置本身，设置间隔秒数(最小是5秒钟)-->
<configuration monitorInterval="30" status="INFO">
    <!--日志级别以及优先级排序: OFF > FATAL > ERROR > WARN > INFO > DEBUG > TRACE > ALL -->
    <!--8个级别从低到高为：All < Trace < Debug < Info < Warn < Error < Fatal < OFF-->

    <!--Log4j 2 的 `PatternLayout` 支持的格式化配置项参数如下：
        `%m`：输出日志消息。
        `%p`：输出日志级别（或%-5level，-5表示左对齐并且固定输出5个字符，如果不足在右边补0）。
        `%r`：输出自应用启动后的毫秒数。
        `%c`：输出日志所属的类的全名。
        `%C`：输出日志所属的类的简单名。
        `%t`：输出当前线程名称。
        `%n`：换行。
        `%d{pattern}`：输出日志时间，其中 `{pattern}` 是日期格式化参数，用于指定日期格式。eg：%d{HH:mm:ss.SSS}表示输出到毫秒的时间
        `%F`：输出产生日志记录的文件名。如Log4j2Test.java
        `%L`：输出产生日志记录的行号。
        `%M`：输出产生日志记录的方法名。
        `%x`：输出线程的NDC（nested diagnostic context）。
        `%X{key}`：输出MDC（mapped diagnostic context）中指定键的值。
         %logger 输出日志记录的类的全名，因为Root Logger没有名称，所以没有输出。
    这些是一些常用的格式化配置项参数，你可以根据自己的需求组合这些参数来定义自己的日志输出格式。
    -->
    <!--变量配置-->
    <Properties>
        <property name="LOG_PATTERN" value="%d{yyyy-MM-dd}[%d{HH:mm:ss,SSS}] %p [%t] %highlight{%c{1.}.%M(%L)}: %m%n"/>
        <!--<property name="LOG_PATTERN" value="%d{yyyy-MM-dd}[%d{HH:mm:ss,SSS}] %p [Thread：%t] [Class：%F Method：%M() Row：%L]: %m%n" />-->
        <!-- 定义日志存储的路径 当前项目根目录-->
        <property name="FILE_PATH" value="./log"/>
        <!--<property name="FILE_NAME" value="myProject" />-->
    </Properties>

    <!--<Appenders>：定义所有日志输出器-->
    <!--此节点有三种常见的子节点：Console,RollingFile,File-->
    <appenders>

        <!--控制台日志输出器：target:SYSTEM_OUT或SYSTEM_ERR,一般只设置默认:SYSTEM_OUT-->
        <console name="Console" target="SYSTEM_OUT">
            <!--日志输出格式：默认为：%m%n,即只输出日志和换行-->
            <PatternLayout pattern="${LOG_PATTERN}"/>
            <!--阈值过滤器：只输出level及其以上级别的信息（onMatch），其他的直接拒绝（onMismatch）-->
            <ThresholdFilter level="debug" onMatch="ACCEPT" onMismatch="DENY"/>
        </console>

        <!--        &lt;!&ndash;文件会打印出所有信息，这个log每次运行程序会自动清空，由append属性决定，适合临时测试用&ndash;&gt;-->
        <!--        <File name="Filelog" fileName="${FILE_PATH}/test.log" append="false">-->
        <!--            <PatternLayout pattern="${LOG_PATTERN}"/>-->
        <!--        </File>-->

        <!--        &lt;!&ndash; 这个会打印出所有的debug及以上级别的信息，每次大小超过size，则这size大小的日志会自动存入按年份-月份建立的文件夹下面并进行压缩，作为存档&ndash;&gt;-->
        <!--        <RollingFile name="RollingFileDebug"-->
        <!--                     fileName="${FILE_PATH}/debug.log"-->
        <!--                     filePattern="${FILE_PATH}/debug/DEBUG-%d{yyyy-MM-dd}_%i.log.gz">-->
        <!--            &lt;!&ndash;阈值过滤器，只输出level及其以上级别的信息（onMatch），其他的直接拒绝（onMismatch）&ndash;&gt;-->
        <!--            <ThresholdFilter level="debug" onMatch="ACCEPT" onMismatch="DENY"/>-->
        <!--            <PatternLayout pattern="${LOG_PATTERN}"/>-->
        <!--            &lt;!&ndash;指定滚动日志的策略，即指定新建日志文件的时机&ndash;&gt;-->
        <!--            <Policies>-->
        <!--                &lt;!&ndash;interval属性用来指定多久滚动一次，时间单位取决于<PatternLayout pattern>，modulate属性调整时间，true：0点为基准滚动，false：服务器启动时间开始滚动&ndash;&gt;-->
        <!--                <TimeBasedTriggeringPolicy interval="1" modulate="true"/>-->
        <!--                <SizeBasedTriggeringPolicy size="100MB"/>-->
        <!--            </Policies>-->
        <!--            &lt;!&ndash; DefaultRolloverStrategy属性如不设置，则默认为最多同一文件夹下7个文件开始覆盖&ndash;&gt;-->
        <!--            &lt;!&ndash;            <DefaultRolloverStrategy max="15">&ndash;&gt;-->
        <!--            <DefaultRolloverStrategy max="10">-->
        <!--                &lt;!&ndash;删除5天之前的日志&ndash;&gt;-->
        <!--                <Delete basePath="${FILE_PATH}" maxDepth="2">-->
        <!--                    <IfFileName glob="*/*.log*"/>-->
        <!--                    <IfLastModified age="1d"/>-->
        <!--                </Delete>-->
        <!--            </DefaultRolloverStrategy>-->
        <!--        </RollingFile>-->


        <!--滚动文件日志输出器-->
        <!--打印出所有的info及以上级别的信息，每次大小超过size，则这size大小的日志会自动存入按年份-月份建立的文件夹下面并进行压缩，作为存档-->
        <RollingFile name="RollingFileInfo"
                     fileName="${FILE_PATH}/info.log"
                     filePattern="${FILE_PATH}/info/INFO-%d{yyyy-MM-dd}_%i.log.gz">
            <!--阈值过滤器，只输出level及其以上级别的信息（onMatch），其他的直接拒绝（onMismatch）-->
            <ThresholdFilter level="info" onMatch="ACCEPT" onMismatch="DENY"/>
            <PatternLayout pattern="${LOG_PATTERN}"/>
            <Policies>
                <!--interval属性用来指定多久滚动一次，时间单位取决于<PatternLayout pattern>，modulate属性调整时间，true：0点为基准滚动，false：服务器启动时间开始滚动-->
                <TimeBasedTriggeringPolicy interval="1" modulate="true"/>
                <SizeBasedTriggeringPolicy size="100MB"/>
            </Policies>
            <!-- DefaultRolloverStrategy属性如不设置，则默认为最多同一文件夹下7个文件开始覆盖-->
            <!--            <DefaultRolloverStrategy max="15">-->
            <DefaultRolloverStrategy max="60">
                <!--删除60天之前的日志-->
                <Delete basePath="${FILE_PATH}" maxDepth="2">
                    <IfFileName glob="*/*.log*"/>
                    <IfLastModified age="60d"/>
                </Delete>
            </DefaultRolloverStrategy>
        </RollingFile>


        <!--滚动文件日志输出器-->
        <!--打印出所有的error及以上级别的信息，每次大小超过size，则这size大小的日志会自动存入按年份-月份建立的文件夹下面并进行压缩，作为存档-->
        <RollingFile name="RollingFileError"
                     fileName="${FILE_PATH}/error.log"
                     filePattern="${FILE_PATH}/error/ERROR-%d{yyyy-MM-dd}_%i.log.gz">
            <!--阈值过滤器：只输出level及其以上级别的信息（onMatch），其他的直接拒绝（onMismatch）-->
            <ThresholdFilter level="error" onMatch="ACCEPT" onMismatch="DENY"/>
            <PatternLayout pattern="${LOG_PATTERN}"/>
            <Policies>
                <!--interval属性用来指定多久滚动一次，时间单位取决于<PatternLayout pattern>，modulate属性调整时间，true：0点为基准滚动，false：服务器启动时间开始滚动-->
                <TimeBasedTriggeringPolicy interval="1" modulate="true"/>
                <SizeBasedTriggeringPolicy size="100MB"/>
            </Policies>
            <!-- DefaultRolloverStrategy属性如不设置，则默认为最多同一文件夹下7个文件开始覆盖-->
            <!--<DefaultRolloverStrategy max="15">-->
            <DefaultRolloverStrategy max="60">
                <!--删除60天之前的日志-->
                <Delete basePath="${FILE_PATH}" maxDepth="2">
                    <IfFileName glob="*/*.log*"/>
                    <IfLastModified age="60d"/>
                </Delete>
            </DefaultRolloverStrategy>
        </RollingFile>

        <!--异步日志处理器：阻塞队列最大容量为20000，超出队列容量时等待日志输出，包含位置信息-->
        <Async name="Async" bufferSize="20000" blocking="true" includeLocation="true">
            <AppenderRef ref="Console"/>
            <!--<AppenderRef ref="RollingFileDebug"/>-->
            <AppenderRef ref="RollingFileInfo"/>
            <AppenderRef ref="RollingFileError"/>
        </Async>
    </appenders>

    <!--<loggers>：定义日志记录器，只有定义了logger并引入的appender，日志输出器才会生效-->
    <loggers>
        <!--根日志记录器，设置级别为info-->
        <root level="info">
            <AppenderRef ref="Async"/>
        </root>

        <!--单独指定特定包下的class指定不同的日志级别-->
        <!--日志记录器：过滤掉spring和mybatis的一些无用的DEBUG信息，级别设置为Info-->
        <!--通过设置additivity="false"，日志消息将只被传递给当前日志记录器，而不会传递给父级记录器，避免日志消息被重复输出-->
        <logger name="org.mybatis" level="info" additivity="false">
            <AppenderRef ref="Async"/>
        </logger>
        <Logger name="org.springframework" level="info" additivity="false">
            <AppenderRef ref="Async"/>
        </Logger>
        <logger name="org.mybatis.spring.SqlSessionUtils" level="OFF" additivity="false">
            <AppenderRef ref="Async"/>
        </logger>
        <logger name="org.mybatis.spring.transaction.SpringManagedTransaction" level="OFF" additivity="false">
            <AppenderRef ref="Async"/>
        </logger>
        <logger name="org.apache.kafka.clients" level="OFF" additivity="false">
            <AppenderRef ref="Async"/>
        </logger>
        <logger name="org.apache.ibatis.logging.jdbc.BaseJdbcLogger" level="OFF" additivity="false">
            <AppenderRef ref="Async"/>
        </logger>
    </loggers>

</configuration>
```

![image-20240712033627837](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407120336807.png)

# 五、最佳实践

1. 导入任何第三方框架，先排除它的日志包，因为 Spring Boot 底层控制好了日志
2. 修改 application.yml 配置文件，就可以调整日志的所有行为。也可以编写日志框架自己的配置文件放在 resource 目录下，比如logback-spring.xml , log4j2-spring.xml
3. 如需对接专业日志系统，也只需要把日志记录输出到 kafka 之类的中间件，只需要配置日志框架就可以了，与 Spring Boot 无关。
4. 业务中使用 slf4j-api 记录日志。不要再 System.out.println 输出了

> [!TIP]
>
> 输出 DEBUG 信息前最好判断下 DEBUG 模式是否开启

```java
if (log.isDebugEnabled()){
    log.debug("debug 日志");
}
```

