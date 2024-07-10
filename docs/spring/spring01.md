# 开篇

> [!CAUTION] 前置准备：
> 可参考 [环境安装](../path-install.md) 进行准备 （Windows）
> - IDEA
> - JAVA、Maven、MySQL
> - Git (版本控制)

# 一、SpringBoot 介绍

传统的 Spring 开发需要使用大量 XML 配置才能使 Spring 框架运行起来，配置复杂而且还需要使用 XML 语法。在 Spring4.x 发布之后，Spring 己经可以完全脱离 XML，只使用注解就可以配置和运行项目。也就是只需要 Java 类，而 XML 文件变为可选。

再者是 Martin Fowler 于 2012 提出 **微服务** 概念，通过 REST Ful 风格请求将这些服务连接到一起。Pivotal 团队于 2014 年推出SpringBoot，一方面简化基于 Spring 的应用开发，另一方面也是为了迎合微服务架构热潮下的开发思维。随着 2017 年 9 月 Spring5的发布，2018 年 SpringBoot2.X 版本问世。2022 年 11 月 Spring6 与 SpringBoot3 也先后发布。

> [!TIP]
>
> 微服务深似海，切勿强行下海！

本 SpringBoot 教程基于 SpringBoot3 基础讲解，主要内容包含：

![image-20240710145338224](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101453274.png)

## 1.1 SpringBoot 的特点

SpringBoot 采用 **约定优于配置** 的原则简化 Spring 应用的搭建和开发过程，从而成为业界内最流行的微服务开发框架，不但具备Spring 的所有特征，而且解放程序员劳动力，增强系统性能。SpringBoot 应用有以下特点：

- 创建独立的 Spring 应用    
- 内置 Tomcat、Jetty、Undertow 应用服务器，无需单独部署 war 包，默认使用 Tomcat
- 提供丰富的 starter 依赖项，简化项目构建，如果需要其他依赖引入对应的 starter

- 需要开发 web 应用，引入 web 的 starter，需要集成 redis，引入 redis 的 starter

- 实现 **自动配置**，简化第三方技术集成，引入对应依赖【starter】之后，SpringBoot 会自动通过默认方式整合该技术，不需要额外的配置，如果你需要根据自己的要求整合，则可以通过配置文件或者 JavaConfig 自定义配置
- 提供生产级别功能，如外部配置、健康检查、不会生成额外代码，不需要 XML 配置

> [!NOTE]
>
> 更多信息可到[【SpringBoot官网】](https://spring.io/projects/spring-boot)查看

## 1.2 SpringBoot 版本

下图为官网目前版本列表

![image-20240710145616117](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101456663.png)

不同版本标签意义如下：

| 版本         | 意义                         |
| ------------ | ---------------------------- |
| **CURRENT**  | 代表了当前版本，最新发布版本 |
| **SNAPSHOT** | 快照版本，可用但非稳定版本   |
| **PRE**      | 预览版本                     |
| **GA**       | 通用正式发布版本             |

# 二、项目创建

创建 SpringBoot 项目的方式有很多种

- 通过 Spring Initializr 创建
- 通过阿里云镜像创建
- 创建普通 maven/gradle 项目，再手动配置依赖

> [!TIP] 提示
>
> 该部分我们只提供大概方式，具体创建流程请参见：
>
> - [Spring Initalizr](#_2-4-1-spring-initalizr)
> - [ALiyun](#_2-4-2-aliyun)
> - [Maven](#_2-4-3-maven)

## 2.1 通过 Spring Initalizr 创建

我们可以访问 [Spring Initalizr](https://start.spring.io/) 通过网页的方式创建项目

![image-20240710150119666](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101501699.png)

> [!CAUTION] 注意
>
> SpringBoot3 最低支持 JDK 17

![image-20240710150156003](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101501386.png)

> [!TIP]
>
> 我们也可以通过 IDEA 直接创建，可以看到默认情况下 IDEA 也是通过访问 https://start.spring.io/ 进行项目创建的

![image-20240710150246443](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101502666.png)

## 2.2 通过 ALiyun 创建

我们可以直接访问 https://start.aliyun.com 通过类似 [Spring Initalizr 创建](#2.1) 的方式进行创建

![image-20240710150430078](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101504075.png)

或者我们也可以将 IDEA 的 URL 修改为 https://start.aliyun.com 通过 IDEA 创建

![image-20240710150516480](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101505148.png)

![image-20240710150534221](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101505335.png)

![image-20240710150540635](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101505231.png)

## 2.3 通过 Maven 构建

当然我们也可以通过 Maven 手动构建

![image-20240710150705931](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101507976.png)

## 2.4 项目结构

这部分我们通过记录不同方式创建出来的项目来介绍 SpringBoot 常见的项目结构

### 2.4.1 Spring Initalizr

1. 项目基本信息

![image-20240710151949914](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101519894.png)

2. 选择 SpringBoot 版本、添加依赖

![image-20240710152040861](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101520165.png)

可以看到不同类别的依赖会划分在不同的选项卡，方便我们根据依赖的功能，选择大致范围，当然我们也可以直接进行搜索添加，此处出于演示目的我们仅添加 Web 栏目中的 Spring Web 依赖项

![image-20240710152228931](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101522087.png)

3. 点击创建

![image-20240710152346299](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101523225.png)

> [!TIP] 抱歉
>
> 🤣 注意到上述我们的项目名单词拼写存在错误 (initalizr 应为 initializr)，下面我将演示如何修改

![image-20240710152859032](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101529018.png)

![image-20240710152911846](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101529760.png)

![image-20240710152944957](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101529529.png)

![image-20240710153213299](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101532382.png)

可以看到此时已经修改过来了

> [!CAUTION]
>
> 如果还是没有修改过来，我们需要手动将 `pom.xml` 、`Simple01CreatedBySpringInitializrApplication` 和`Simple01CreatedBySpringInitializrApplicationTests` 进行修改然后重新加载 Maven 项目即可

![image-20240710153457515](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101534351.png)

可以看到默认情况下通过 Spring Initializr 创建的项目中包含以下文件：

- `.mvn`  maven 包装器，用于统一 maven 版本
- `src` 源代码目录
  - `main`  主目录
    - `java` 
      - `jzxy.cbq.Simple01CreatedBySpringInitializrApplication` 类  
    - `resources` 资源文件
      - `static` 静态资源目录，用于存放前端资源
      - `templates` 模板文件夹（便于模板引擎，此处可以不关心）
      - `application.properties` SpringBoot 项目默认配置文件
  - `test` 测试目录
    - `jzxy.cbq.Simple01CreatedBySpringInitializrApplicationTests` 类
- `mvnw` 和 `mvnw.cmd` maven 脚本搭配 maven 包装器使用，分别为 linux 下 和 windows 下提供支持
- `pom.xml` maven 项目的 pom 文件，定义项目信息以及依赖、插件等

让我们先来着重介绍下 `pom.xml` 文件中的内容

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <!--maven 父工程-->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.3.1</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <!--项目基本信息-->
    <groupId>jzxy.cbq</groupId>
    <artifactId>simple01-created-by-spring-initializr</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>simple01-created-by-spring-initializr</name>
    <description>simple01-created-by-spring-initializr</description>
    <!--提供更为细致的信息-->
    <url/>
    <licenses>
        <license/>
    </licenses>
    <developers>
        <developer/>
    </developers>
    <scm>
        <connection/>
        <developerConnection/>
        <tag/>
        <url/>
    </scm>
    <!--项目属性-->
    <properties>
        <java.version>17</java.version>
    </properties>
    <!--依赖-->
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    <!--插件配置-->
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>

```

其中

```xml
 <!--提供更为细致的信息-->
    <url/>
    <licenses>
        <license/>
    </licenses>
    <developers>
        <developer/>
    </developers>
    <scm>
        <connection/>
        <developerConnection/>
        <tag/>
        <url/>
    </scm>
```

的作用请参见

![image-20240710154245002](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101542270.png)

> [!NOTE] 翻译
>
> 英文版：
>
> Due to Maven's design, elements are inherited from the parent POM to the project POM.
> While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the
> parent.
> To prevent this, the project POM contains empty overrides for these elements.
> If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.
>
> 中文翻译：
>
> 由于 Maven 的设计，元素从父 POM 继承到项目 POM。虽然大部分继承都很好，但它也继承了不需要的元素，例如来自父母。
> 为了防止这种情况，项目 POM 包含这些元素的空覆盖。如果您手动切换到不同的父级并且实际上想要继承，则需要删除这些覆盖。

### 2.4.2 ALiyun

> [!NOTE] 小记
>
> 区别于使用 Spring Initalizr 通过 ALiyun 创建的项目会包含部分示例代码，因为这两种方式都是通过 git 克隆一个模板项目进行项目创建的

1. 修改 IDEA 中 URl 为 https://start.aliyun.com/

![image-20240710154516865](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101545923.png)

2. 填写项目基本信息

![image-20240710154609582](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101546495.png)

2. 版本和依赖选择

![image-20240710154627572](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101546747.png)

3. 创建

![image-20240710155253335](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101552238.png)

> [!TIP]
>
> 可以看到相较于通过 Spring Initializr 方式创建，通过 ALiyun 创建的项目多了很多示例代码，我们将简要介绍相关代码（区别于通过 Spring Initializr 方式创建 出来的代码）其中有关 DDD、Controller 和 point 等的概念可以暂时不用了解，此处仅仅是指明区别。

- `demos.web` DDD 领域架构下的 web 领域
  - `BasicController` 基础 Controller 用于提供通用 point
  - `PathVariableController` 环境参数 Controller 用于提供基于 url 传参的 point
  - `User`
- `static`
  - `index.html` 项目主页，默认情况下 SpringBoot 会访问 static 下的 index.html 页面

> [!CAUTION]
>
> 注意 通过 ALiyun 创建的项目中的 pom.xml 与通过 Spring Initializr 方式创建 的有很大区别，因此我们将对其进行详细讲解

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>jzxy.cbq</groupId>
    <artifactId>simple02-created-by-aliyun</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>simple02-created-by-aliyun</name>
    <description>simple02-created-by-aliyun</description>
    <properties>
        <java.version>17</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <spring-boot.version>3.0.2</spring-boot.version>
    </properties>
    <!--通用依赖-->
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    <!--依赖管理-->
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>${spring-boot.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
    <!--插件-->
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>17</source>
                    <target>17</target>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>${spring-boot.version}</version>
                <configuration>
                    <mainClass>jzxy.cbq.Simple02CreatedByAliyunApplication</mainClass>
                    <skip>true</skip>
                </configuration>
                <executions>
                    <execution>
                        <id>repackage</id>
                        <goals>
                            <goal>repackage</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

</project>

```

其中：

- 通用依赖即 `dependencies` 标签下的依赖为所有子项目所共有，不需要在子项目中单独指出 （maven 会自动引入该部分依赖到子项目）
- 依赖管理即 `dependencyManagement` 标签下的依赖主要通过父项目管理，子项目使用需要单独指定 ，但相关版本可以不指定 （maven 不会将该部分依赖自动引入到子项目，只有在子项目的 pom 文件中指出 mavne 才会引入到子项目）这样做有利于版本的统一管理即父项目通过在  `<properties>` 标签下定义相关依赖的版本并通过 `<version>${xxx.version}</version> `的方式指定即可。

> [!NOTE] 例如
>
> - 在 `properties` 中定义 `<spring-boot.version>3.0.2</spring-boot.version>`
> - 在 `dependencyManagement` 中引用 `<version>${spring-boot.version}</version>`

### 2.4.3 Maven

1. 创建 maven 项目

![image-20240710161543106](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101615259.png)

![image-20240710161707871](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101617680.png)

2. 引入相关依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.3.1</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <groupId>jzxy.cbq</groupId>
    <artifactId>simple03-created-by-maven</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>

```

3. 创建目录结构

   ![image-20240710161844208](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101618663.png)

   ![image-20240710162043301](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101620595.png)

   ```java
   @SpringBootApplication
   public class Simple03CreatedByMavenApplication {
       public static void main(String[] args) {
           SpringApplication.run(Simple03CreatedByMavenApplication.class, args);
       }
   }
   ```

   ![image-20240710162007662](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101620794.png)

   ```properties
   spring.application.name=Simple03CreatedByMavenApplication
   ```

4. 编写测试类

![image-20240710162232765](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101622304.png)

![image-20240710162244118](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101622036.png)

```java
package jzxy.cbq;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Simple03CreatedByMavenApplicationTest {
    @Autowired
    ApplicationContext context;

    @Test
    void contextLoads() {
        assertNotNull(context, "context is null");
    }

    @Test
    void beanList() {
        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
    }

}

```

![image-20240710162336586](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101623980.png)

## 2.5 基本使用

### 2.5.1 项目启动

> [!TIP]
>
> 该部分我们基于上述的 [Maven](#_2-4-3-maven) 构建好的项目进行学习

为了方便开发我们引入 lombok

![image-20240710162545154](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101625482.png)

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <scope>annotationProcessor</scope>
</dependency>
```

在启动类 `Simple03CreatedByMavenApplication` 中添加相关日志信息

```java
@SpringBootApplication
@Slf4j
public class Simple03CreatedByMavenApplication {
    public static void main(String[] args) {
        SpringApplication.run(Simple03CreatedByMavenApplication.class, args);
        
        log.info("Simple03CreatedByMavenApplication run successful ");
    }
}
```

启动

![image-20240710162740516](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101627699.png)

![image-20240710162809922](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101628228.png)

### 2.5.3 修改默认 banner

> [!NOTE]
>
> 众所周知 Hello World 对于大部分 coder 都是一个美好的开始，同样的我们可以在此处修改 SpringBoot 启动的默认 banner 来输出 Hello Spring Boot 

SpringBoot 的默认 banner 为

![image-20240710163058778](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101631063.png)

SpringBoot 启动时会自动读取 resources 下的 banner.txt 文件，如果存在则使用不存在就是用默认的，因此我们可以通过为 resources 文件夹下添加一个 banner.txt 文件达到上述目的，其中 banner.txt 文件的内容我们可以通过 https://www.bootschool.net/ascii 进行生成

![image-20240710163344809](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101633969.png)

![image-20240710163237752](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101632794.png)

```txt
    //    / /                             //   ) )                                             //   ) )
   //___ / /  ___     // //  ___         ((         ___      __     ( )   __      ___         //___/ /   ___      ___    __  ___
  / ___   / //___) ) // // //   ) )        \\     //   ) ) //  ) ) / / //   ) ) //   ) )     / __  (   //   ) ) //   ) )  / /
 //    / / //       // // //   / /           ) ) //___/ / //      / / //   / / ((___/ /     //    ) ) //   / / //   / /  / /
//    / / ((____   // // ((___/ /     ((___ / / //       //      / / //   / /   //__       //____/ / ((___/ / ((___/ /  / /

${AnsiColor.BRIGHT_GREEN}
Spring Boot Version: ${spring-boot.version}
```

![image-20240710163701350](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101637245.png)

### 2.5.4 访问项目接口

在上述项目中我们已经引入了 `spring-boot-starter-web` 依赖，接下来让我们简单体验下如何使用：

返回基本信息：

![image-20240710164101042](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101641940.png)

```java
@RestController
@RequestMapping("api/v1/hellos")
public class HelloController {

    @GetMapping("sayHello")
    public String sayHello() {
        return "Hello Spring Boot";

    }
}
```

访问 `http://localhost:8080/api/v1/hellos/sayHello`

![image-20240710164314770](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101643787.png)

或通过 IDEA 的 Http Client

![image-20240710164211792](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101642326.png)

![image-20240710164247429](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101642511.png)

返回实体信息：

1. 创建 entity

![image-20240710164132073](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101641545.png)

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private int id;
    private String name;
    private int age;
    private String email;
}
```

2. 创建 controller

![image-20240710164337040](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101643160.png)

3. 编写 point

```java
@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    @GetMapping
    List<Student> list() {
        return Arrays.asList(
                new Student(1, "cbq", 22, "cbq@gmail.com"),
                new Student(2, "cq", 18, "cb@qq.com"));
    }
}
```

![image-20240710164739727](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101647204.png)

# 三、项目打包

## 3.1 maven-compiler-plugin

maven-compiler-plugin 是 Maven 项目构建工具中的一个重要插件，主要用于 **编译源码**。在 Maven 项目中，如果没有特别指定，通常会使用 maven-compiler-plugin 的默认配置进行编译。然而，对于特定的项目需求，可能需要调整编译器的配置，这时就可以通过配置 maven-compiler-plugin 来实现。

**maven-compiler-plugin 插件的主要作用包括：**

1. **指定JDK版本：**Maven 默认使用的 JDK 版本可能并不符合项目的实际需求，这可能导致编译错误或运行时问题。通过使用 maven-compiler-plugin 插件，可以明确指定项目源代码所使用的 JDK 版本，以及编译后的类库拟运行的 JVM 版本，从而确保项目在不同环境中的一致性和稳定性。**比如此案例中使用的是 JDK17**
2. **设置编码方式：**源代码的编码方式对于编译和运行过程至关重要。maven-compiler-plugin 允许设置源代码和目标代码的编码方式，以防止因编码不一致而导致的编译错误或乱码问题。**比如此案例中设置的是 UTF-8 编码方式。**
3. **优化编译过程：**maven-compiler-plugin 提供了丰富的配置选项，可以对编译过程进行细粒度的控制。例如，可以设置是否使用增量编译、是否生成调试信息等，以提高编译效率和代码质量。

### 3.1.1 注意点

在配置 maven-compiler-plugin 时，需要注意以下几点：

1. 插件版本：确保使用的 maven-compiler-plugin 版本与 Maven 版本和 JDK 版本兼容。不同版本的插件可能具有不同的功能和配置选项。
2. JDK 版本：根据项目的实际需求选择合适的 JDK 版本。如果项目中使用了较新的 Java 特性，需要确保 JDK 版本支持这些特性。
3. 编码方式：确保源代码文件的编码方式与 maven-compiler-plugin 中设置的编码方式一致，以避免编码问题导致的编译错误。

### 3.1.2 配置项

```xml
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-compiler-plugin</artifactId>
  <!-- 版本，要和 Jdk 版本匹配 -->
  <version>3.8.1</version>
  <configuration>
      <!-- 源码 JDK 版本 -->
      <source>17</source>
      <!-- 目标 JDK 版本 -->
      <target>17</target>
      <!-- 编码 -->
      <encoding>UTF-8</encoding>
      <!-- 示输出编译的详细细节，方便了解编译的具体情况 -->
      <verbose>true</verbose>
      <!-- 
      fork 和 executable 一般搭配使用，如果省略 executable 并设置 true，
      maven 编译器插件将默认选择 JAVA_HOME/bin/javac 二进制文件，
      如果设置了 false，maven 编译器插件将通过 ToolProvider 接口选择编译器。
      这意味着不会启动新进程，Maven 正在运行的 JavaVM 也会进行编译。
      executable 表示 javac 的绝对路径，默认会寻找环境变量 JAVA_HOME 的位置，当前也可以自己设置一个路径。
      -->
      <fork>true</fork>
      <!-- 设置外部 JDK 命令 -->
      <executable>${JAVA_HOME}/bin/javac</executable>
      <!-- 最小内存 -->
      <meminitial>128m</meminitial>
      <!-- 最大内存 -->
      <maxmem>512m</maxmem>
      <!-- 设置编译时 jdk 的版本信息 -->
      <compilerVersion>17</compilerVersion>
  </configuration>
</plugin>
```

## 3.2 spring-boot-maven-plugin

该插件是项目打包插件，可以配置打包时的参数，不同的参数会打出不同的 jar/war 包

```java
<!--使用的插件列表-->
<plugins>
  <!--plugin元素包含描述插件所需要的信息。 -->
  <plugin>
  	<!--插件在仓库里的 group ID -->
  	<groupId />
  	<!--插件在仓库里的 artifact ID -->
  	<artifactId />
  	<!--被使用的插件的版本（或版本范围） -->
  	<version />
    <configuration>
    	<!-- 最终名称 xxx.jar-->
		<finalName>xxx</finalName>
        <mainClass>com.stt.springboot.CreateDemoApplication</mainClass>
        <!--跳过依赖的 jar，不要配置它（打出来的 jar 包不包含依赖项特别小但是无法使用）-->
        <!--<skip>false</skip>-->
        <!-- 排除依赖，如 lombok 仅在编译时需要，打包时忽略的就可以配置进来 -->
        <excludes>
            <exclude>
                <groupId>xxx.xxx</groupId>
                <artifactId>xxx</artifactId>
            </exclude>
        </excludes>
    </configuration>
  	<!--在构建生命周期中执行一组目标的配置。每个目标可能有不同的配置。 -->
  	<executions>
  		<!--execution 元素包含了插件执行需要的信息 -->
  		<execution>
  			<!--执行目标的标识符，用于标识构建过程中的目标，或者匹配继承过程中需要合并的执行目标 -->
  			<id />
  			<!--绑定了目标的构建生命周期阶段，如果省略，目标会被绑定到源数据里配置的默认阶段 -->
  			<phase />
  			<!--配置的执行目标 -->
  			<goals />
  			<!--配置是否被传播到子 POM -->
  			<inherited />
  			<!--作为 DOM 对象的配置 -->
  			<configuration />
  		</execution>
  	</executions>
  	<!--项目引入插件所需要的额外依赖 -->
  	<dependencies>
  		<!--参见 dependencies/dependency 元素 -->
  		<dependency>
  			......
  		</dependency>
  	</dependencies>
  	<!--任何配置是否被传播到子项目 -->
  	<inherited />
  	<!--作为 DOM 对象的配置 -->
  	<configuration />
  </plugin>
</plugins>
```

**goals的可选值**

- build-image: 将程序使用 buildpack 打包进容器镜像中。
- build-info：生成项目的构建信息文件 build-info.properties
- help：显示帮助信息。调用 mvn spring-boot:help -Ddetail=true -Dgoal= 以显示参数详细信息。
- repackage：可生成可执行的 jar 包或 war 包。插件的核心 goal。
- run：运行 Spring Boot 应用
- start：在集成测试阶段，控制生命周期
- stop：在集成测试阶段，控制生命周期

### 3.2.1 打包命令

会在项目的 target 目录中生成相关 jar

```shell
mvn clean package -Dmaven.test.skip=true
```

![image-20240710165402276](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101654380.png)

![image-20240710165426587](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101654458.png)

![image-20240710165454462](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101654646.png)

可以通过 `java -jar xxx.jar` 方式启动

![image-20240710165616536](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101656927.png)

# 四、SpringBoot 依赖管理机制

> [!TIP]
>
> 此处仅对 SpringBoot 的依赖管理机制和自动配置机制进行简要概述，不进行深入

## 4.1 依赖版本

当项目中引入指定版本的 SpringBoot 依赖之后，在引入其他 starter 时既不需要指定版本，因为在 SpringBoot 依赖中已经指定好了版本，根据 maven 的依赖传递原则，子工程自然可以访问到父工程的所有依赖

> [!TIP]
>
> 依赖具体定义位于：`spring-boot-starter-parent` --> `spring-boot-dependencies` （又称版本仲裁中心）

![image-20240710165922090](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101659157.png)

## 4.2 导入启动器

> [!NOTE] SpringBoot 启动器
>
> https://docs.spring.io/spring-boot/reference/using/build-systems.html#using.build-systems.starters
>
> 英文：
>
> Starters are a set of convenient dependency descriptors that you can include in your application. You get a one-stop shop for all the Spring and related technologies that you need without having to hunt through sample code and copy-paste loads of dependency descriptors. For example, if you want to get started using Spring and JPA for database access, include the dependency in your project.`spring-boot-starter-data-jpa`
>
> The starters contain a lot of the dependencies that you need to get a project up and running quickly and with a consistent, supported set of managed transitive dependencies.
>
> 中文：
>
> 启动器是一组方便的依赖关系描述符，您可以将其包含在应用程序中。您可以一站式获得所需的所有 Spring 及相关技术，而无需翻阅示例代码和复制粘贴大量依赖关系描述符。例如，如果您想开始使用 Spring 和 JPA 进行数据库访问，请在项目中包含该依赖关系。 `spring-boot-starter-data-jpa`
>
> 启动器包含了大量的依赖项，这些依赖项可以让项目快速启动和运行，并提供一套一致的、受支持的受管传递依赖项。

导入对应的启动器时同时会导入相关联的依赖，因为启动器中包含了这些依赖，比如导入**spring-boot-starter-web** 启动器，该启动器中包含了以下依赖，Maven 也会将依赖传递到子项目中。

![image-20240710170024885](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101700976.png)

> [!NOTE] SpringBoot starter 命名规范
>
> https://docs.spring.io/spring-boot/reference/using/build-systems.html#using.build-systems.starters
>
> 英文：
>
> All **official** starters follow a similar naming pattern; , where is a particular type of application. This naming structure is intended to help when you need to find a starter. The Maven integration in many IDEs lets you search dependencies by name. For example, with the appropriate Eclipse or Spring Tools plugin installed, you can press in the POM editor and type “spring-boot-starter” for a complete list.`spring-boot-starter-*``*``ctrl-space`
>
> As explained in the [Creating Your Own Starter](https://docs.spring.io/spring-boot/reference/features/developing-auto-configuration.html#features.developing-auto-configuration.custom-starter) section, third party starters should not start with , as it is reserved for official Spring Boot artifacts. Rather, a third-party starter typically starts with the name of the project. For example, a third-party starter project called would typically be named .`spring-boot``thirdpartyproject``thirdpartyproject-spring-boot-starter`
>
> 中文：
>
> 所有官方启动程序都遵循类似的命名模式 `spring-boot-starter`是一种特定类型的应用程序。这种命名结构的目的是在需要查找启动程序时提供帮助。许多集成开发环境中的 Maven 集成可让您按名称搜索依赖项。例如，在安装了相应的 Eclipse 或 Spring Tools 插件后，您可以按下 POM 编辑器并键入 "spring-boot-starter"，以获得完整的列表。
>
> 正如 [创建自己的启动程序](https://docs.spring.io/spring-boot/reference/features/developing-auto-configuration.html#features.developing-auto-configuration.custom-starter) 部分所述，第三方启动程序不应以 `spring-boot-starter` 开头，因为它是 Spring Boot 官方工件的专用开头。相反，第三方启动器通常以项目名称开头。例如，第三方启动器项目的名称通常为 .  `thirdpartyproject-spring-boot-starter`
>
> 例如：
>
> - 官方 ：`spring-boot-starter-data-jpa`
> - 非官方：`mybatis-plus-spring-boot3-starter`

# 五、SpringBoot 自动配置机制

> [!NOTE] 什么是自动配置
>
> spring-boot-starter-web 认为我们要开发 web 应用了，就需要一些固定的依赖项，比如 tomcat、json、日志。都有一套默认的配置，默认的配置都是通过 JavaConfig 的方式实现的，并不是 xml 文件。

SpringBoot 导入对应的启动器之后还会执行自动配置来简化开发，比如导入 spring-boot-starter-web 启动器

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

web 启动器还导入了 spring-boot-starter 启动器，它是所有启动器的根

![image-20240710170148790](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101701004.png)

在 spring-boot-starter 启动器中有一个自动配置依赖 spring-boot-autoconfigure

![image-20240710170210583](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101702123.png)

在引入的依赖库中可以看到在 META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports 文件中配置了诸多配置类，这些配置类的源码在 org.springframework.boot.autoconfigure 包中，SpringBoot 会根据引入的启动器加载对应的配置类，这些都是默认写好的，所以 SpringBoot 有一个口号是 **约定大于配置**

![image-20240710170426189](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101704385.png)

从上述中的 `DispatcherServletAutoConfiguration` 我们可以观察到 SpringBoot 使用了很多类似于 `ConditionalOnXXX` 的注解控制相关配置的注入，例如 `@ConditionalOnWebApplication` 表示当前类为 Web 应用时注入该对象。

![image-20240710170500746](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407101705458.png)
