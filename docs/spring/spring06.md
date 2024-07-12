# Spring Boot 服务监控

SpringBoot Admin 用于对 SpringBoot 应用的管理和监控，可以用来监控服务是否健康、是否在线、以及一些 jvm 数据，以及服务异常时的回调实现短信通知或者邮件通知等等。

Spring Boot Admin 分为服务端 (spring-boot-admin-server) 和客户端 (spring-boot-admin-client)，服务端和客户端之间采用 http 通讯方式实现数据交互。

整个实现流程就是创建一个服务端负责监控管理各个模块，需要被管理的模块为客户端，注册到服务端即可。

- 官方文档 1： http://docs.spring-boot-admin.com/current/getting-started.html
- 官方文档 2： https://consolelog.gitee.io/docs-spring-boot-admin-docs-chinese/
- 官方文档 3： https://codecentric.github.io/spring-boot-admin/2.5.1/#getting-started
- 官方示例： https://github.com/codecentric/spring-boot-admin/tree/master/spring-boot-admin-samples

# 一、服务端开发

创建项目，引入以下依赖，注意版本对应

| SpringBoot | spring-boot-admin-starter-server |
| ---------- | -------------------------------- |
| 2.X        | 2.X                              |
| 3.X        | 3.X                              |

1. 引入依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>de.codecentric</groupId>
    <artifactId>spring-boot-admin-starter-server</artifactId>
    <version>3.3.2</version>
</dependency>
```

2. 主类上添加 **@EnableAdminServer** 注解

```java
@EnableAdminServer
@SpringBootApplication
@Slf4j
public class Demo10ActuatorServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(Demo10ActuatorServerApplication.class, args);

        log.info("Demo10ActuatorServerApplication run successful ");
    }
}
```

![image-20240712133944079](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407121339131.png)

3. 配置文件暂时倒不用有什么特殊配置，只需要自定义一个端口号就行

```yaml
server:
  port: 9111
```

![image-20240712134458670](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407121344749.png)

# 二、客户端开发

1. 引入依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>de.codecentric</groupId>
    <artifactId>spring-boot-admin-starter-client</artifactId>
    <version>3.3.2</version>
</dependency>
```

2. 配置

```yaml
spring:
  boot:
    admin:
      client:
        url: http://localhost:8090 # 客户端注册地址，与服务端 ip:port 一致
management:
  endpoints:
    web:
      exposure:
        include: '*'
  endpoint:
    health:
      show-details: always
    logfile: # 在线日志
      external-file: D:\\spring.log
  info:
    env:
      enabled: true
```

3. 启动

![image-20240712134629055](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407121346271.png)

启动之后在浏览器输入 **http://服务端ip:服务端端口/applications** 即可查看服务列表，例如此处的 `http://localhost:8090/applications`

> [!CAUTION]
>
> 注意：如果 url 显示为 xxx.mshome.net 说明 host 文件配置有问题，需要添加相关映射
>
> ![image-20240712140434636](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407121404246.png)
>
> ![image-20240712142241545](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407121422628.png)
>
> ![image-20240712142530102](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407121425839.png)
>
> 正确情况应如下所示

![image-20240712144206490](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407121442416.png)

![image-20240712144216344](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407121442387.png)

![image-20240712144142132](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407121441408.png)
