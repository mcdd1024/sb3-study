# Web 开发

> [!NOTE]
>
> - MVC 思想及相关组件和执行流程
> - 开发 Web 应用 （Restful 接口开发）
> - 统一结果封装、统一异常处理
> - 参数校验、文件操作 （OSS）
> - 拦截器与过滤器

# 一、Spring MVC

使用 SpringBoot 或者说使用 Spring 生态实现 Web 开发，提供网络请求服务的后端服务采用的技术是 Spring MVC，其中SpringBoot 将 SpringMVC 集成入spring-boot-web-starter 中，引入此启动器即可实现 web 应用开发

现如今终端产品众多，有 PC 端、手机、平板或者其他诸如 VR 设备、电影院或医院等场所的硬件设备，为适应如此多的设备数据同步实现标准化，统一化服务，SpringMVC 通过 HTTP 或 HTTPS 协议的方式来处理用户的请求并给予响应。常见的交互数据格式有JSON，同时也有文件格式【PDF、Excel、Word】或者 XML 类型的数据等。我们将在 SpringBoot 环境下同时学习 SpringMVC 和基于 SpringBoot 的 Web 服务端开发。

## 1.1 Spring MVC 架构设计

Spring MVC 是 Spring Framework 提供的 Web 组件，全称是 **Spring Web MVC**，是目前主流的实现 **MVC设计模式** 的框架，提供前端路由映射、视图解析等功能，MVC 的设计思想不单单应用于 Java 开发，也广泛应用于其他系统开发，甚至前端开发也参考了MVC 思想，首先了解 MVC 分别代表什么

- **视图（View）**：指工程中的 html，jsp 等页面，作用是和用户进行交互，展示数据，在前后端分离的场景下前端视图现通常独立在后端服务之外

- **控制器（Control）**：指 Servlet 用来接收用户请求和返回数据，SpringMVC 简化对开发人员屏蔽了直接操作 Servlet 的细节，采用 Controller 实现这一操作

- **模型（Model）**：通常指 JavaBean，JavaBean 分为两类

  - **实体类 Bean**：专门用来存储业务数据，与数据库映射的类，比如 User 类对应的 tb_user 表

  - **业务处理 Bean**：指 Service 层或 Dao 层，专门用来处理业务逻辑和数据访问的模块

整个请求流程大致如下：

![未命名文件.png](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407111514168.png)

1. 处理请求先到达控制器 Controller 
2. 控制器分发请求，根据请求的内容去访问不同的 Model，Model 细分为服务层【Service】和数据访问层【Dao】 
3. 当控制器取到由模型层返回的数据后，就将数据渲染到视图中，这样就能够展现给用户

## 1.2 相关组件



### 1.2.1 DispatcherServlet 前端控制器

用户请求到达前端控制器，它就相当于 mvc 模式中的 c，dispatcherServlet 是整个流程控制的中心，由它调用其它组件处理用户的请求，dispatcherServlet 的存在降低了组件之间的耦合性。

### 1.2.2 HandlerMapping 处理器映射器

HandlerMapping 负责根据用户请求找到 Handler 即处理器，SpringMVC 提供了不同的映射器实现不同的映射方式，例如：配置文件方式，实现接口方式，注解方式等。

### 1.2.3 Handler 处理器

它就是我们开发中要编写的具体业务控制器。由 DispatcherServlet 把用户请求转发到 Handler。由 Handler 对具体的用户请求进行处理。

### 1.2.4 HandlAdapter 处理器适配器

通过 HandlerAdapter 对处理器进行执行，这是适配器模式的应用，通过扩展适配器可以对更多类型的处理器进行执行。适配器对应的处理器以及这些处理器的作用：

1. AnnotationMethodHandlerAdapter 主要是适配注解类处理器，注解类处理器就是我们经常使用的 @Controller 的这类处理器
2. HttpRequestHandlerAdapter 主要是适配静态资源处理器，静态资源处理器就是实现了 HttpRequestHandler 接口的处理器，这类处理器的作用是处理通过 SpringMVC 来访问的静态资源的请求
3. SimpleControllerHandlerAdapter 是 Controller 处理适配器，适配实现了 Controller 接口或 Controller 接口子类的处理器，比如我们经常自己写的 Controller 来继承 MultiActionController.
4. SimpleServletHandlerAdapter 是 Servlet 处理适配器, 适配实现了 Servlet 接口或 Servlet 的子类的处理器，我们不仅可以在 web.xml 里面配置 Servlet，其实也可以用 SpringMVC 来配置 Servlet，不过这个适配器很少用到，而且 SpringMVC 默认的适配器没有他，默认的是前面的三种。

### 1.2.5 ModelAndView

ModelAndView 是处理器返回的结果对象，它包含了视图名和需要在视图中渲染的模型数据。

### 1.2.6 ViewResolver

ViewResolver 用于将视图名解析为对应的视图对象（View）。在 SpringMVC 中，常用的 ViewResolver 实现包括 InternalResourceViewResolver（用于解析 JSP 视图）、FreeMarkerViewResolver（用于解析 FreeMarker 模板）等。

### 1.2.7 View

View 即视图，它用于渲染模型数据，最终生成响应内容。在 SpringMVC 中，常用的 View 实现包括 JstlView（用于渲染 JSP 视图）、FreeMarkerView（用于渲染 FreeMarker 模板）等。

## 1.3 执行流程

SpringMVC 的核心是 DispatcherServlet，如果是传统的 SSM 项目，我们需要进行大量的配置，包括对 DispatcherServlet 进行配置，而在 SpringBoot 环境下我们使用了 Spring MVC 就会自动的配置 DispactherServlet 和一些重要组件，相关配置可到 spring-webmvc 依赖的 DispatcherServlet.properties 配置文件中查看，下边介绍一下

![2024-05-29_15-54-42.png](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407111516990.png)

1. 发起请求到前端控制器【DispatcherServlet】
2. 前端控制器请求 HandlerMapping 查找 Handler
3. 处理器映射器 HandlerMapping 向前端控制器返回 Handler，HandlerMapping 会把请求映射为 HandlerExecutionChain 对象【包含一个 Handler 处理器对象，多个 HandlerInterceptor 拦截器对象】
4. 前端控制器调用处理器适配器去执行 Handler
5. 处理器适配器 HandlerAdapter 将会根据适配的结果去执行 Handler
6. Handler 执行完成给适配器返回 ModelAndView
7. 处理器适配器向前端控制器返回 ModelAndView【ModelAndView 是 springmvc 框架的一个底层对象，包括 Model 和 view】
8. 前端控制器请求视图解析器去进行视图解析【根据逻辑视图名解析成真正的视图】，通过这种策略很容易更换其他视图技术，只需要更改视图解析器即可
9. 视图解析器向前端控制器返回 View
10. 前端控制器进行视图渲染【将在 ModelAndView 中的数据填充到 request 域】
11. 前端控制器向用户响应结果

# 二、SpringBoot 开发 web 应用

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

此处所介绍的 web 应用开发仅限于基于 SpringBoot 的后台服务开发，并不涉及前端内容，后端基本的服务开发只需掌握以下几点内容：

- 正确开发 web 服务接口，提供 HTTP/HTTPS 服务
- 正确接收基于 JSON 格式的请求数据
- 正确返回基于 JSON 格式的接口响应数据
- 全局异常捕获

其他拦截器、文件相关操作等后边单独介绍，此处只做简单的接口开发

> [!TIP]
>
> 基于目前 **前后端分离架构** 场景下，后端不负责页面跳转，只需接收用户参数通过服务处理之后返回处理结果和数据，前端根据处理结果来判断之后的逻辑，是跳转页面，还是弹窗提示等都交由前端处理。达到解耦的目的。

## 2.1 开发 HTTP 接口

学习接口开发前，让我们先来了解下什么是 RestFul 风格 API，HTTP 的创始人发现我们在乱用 HTTP 请求，我们喜欢在 url 上添加动词，或者全都用 POST 请求，简直在乱用，提出了一些规则：

- 首先网络上的数据都称为资源，一个用户信息，一个图片，一个视频，一个音频
- 对资源做添加，修改，删除，查询，做不同的操作应该用不同的请求方式

- 添加：POST 
- 修改：PUT
- 删除：DELETE 
- 查询：GET

> [!NOTE]
>
> 对 url 的约束，url 中不应搞包含动词，只包含名词，比如对动物园操作，一个合理的 url 应该是：
>
> - 添加：zoo
> - 删除：zoo/1
> - 修改：zoo
> - 查询：zoo/郑州动物园
>
> 添加和修改的地址一样，会根据请求方式区分

初步了解 RestFul 风格的 API 后，让我们实现一个基本的 CRUD 案例，此案例通过 @Controller、@ResponseBody和 @RequestMapping("test") 两个注解实现：

- 类上注明 Controller 注解表明 Spring 将该类注册为 Bean，并且提供接口服务，否则请求将会 404

> [!NOTE]
>
> 类上的 Controller 注解表示是一个普通的接口服务，不会返回 JSON 类型是数据，如果你希望跳转页面可以使用 Controller 注解，所以请求处理之后会做跳转，但是匹配不到对应的路径就会 404。注意这个 404 是请求处理之后的 404，并不是接口的 404

- 类上使用 ResponseBody 注解表示返回的类型为 JSON 格式，否则会返回视图
- 类上使用 RequestMapping 注解表明是一个统一前缀
- 方法上的 RequestMapping 注解表示该方法提供接口服务，表明接口地址

> [!TIP]
>
> 一般的 web 服务协议都是 HTTP 协议，如果希望是 HTTPS 协议需要使用域名配置 SSL 证书

下方案例接口都是 HTTP 协议，下方先实现简单的 web 接口开发。会用到的注解有：

- @Controller：使用在类上，创建 Bean 用来处理 HTTP 请求
- @ResponseBody：使用在类和方法上，接口返回的数据格式化为 JSON
- @RestController：使用在类上，是 @Controller 和 @ResponseBody 的组合注解，所有的数据返回都是 JSON 格式

> [!NOTE]
>
> 如果你希望接口返回 JSON 类型的响应数据，则可以在类或者方法上添加 @ResponseBody 注解，区别在于：
>
> - 添加在类上，则类中所有的接口都返回 JSON 类型数据
> - 添加在方法上，则仅仅添加的方法返回 JSON 类型数据
>
> 一般接口中不会有的返回 json 有的不返回 json，如果出现有不同返回需求的，都是会进行拆分解耦，否则接口结构就太乱了。一般返回 json 数据的要占 99%，会发现同时使用 ResponseBody 和 Controller 注解会显得比较冗余，通常情况下我们会使用Spring MVC 提供了的组合注解 **@RestController**，使用在类上，表示类中所有的接口都返回 JSON 类型数据

- @RequestMapping：使用在类或者方法上，表示 HTTP 的映射路径，可以指定请求方式
- @PostMapping：使用在方法上，表示 HTTP 请求映射路径，仅支持 POST 请求
- @GetMapping：使用在方法上，表示 HTTP 请求映射路径，仅支持 GET 请求
- @PutMapping：使用在方法上，表示 HTTP 请求映射路径，仅支持 PUT 请求
- @DeleteMapping：使用在方法上，表示 HTTP 请求映射路径，仅支持 DELETE 请求

```java
@RequestMapping("test")
@RestController
@Slf4j
public class TestController {

    /**
     * 添加数据
     */
    @RequestMapping(value = "save")
    public String save() {
        log.info("添加数据");
        return "添加数据成功";
    }

    /**
     * 删除数据
     */
    @RequestMapping(value = "remove", method = RequestMethod.DELETE)
    public String remove() {
        log.info("删除数据");
        return "删除数据成功";
    }

    /**
     * 修改数据
     */
    @RequestMapping(value = "update")
    public String update() {
        log.info("修改数据");
        return "修改数据成功";
    }

    /**
     * 查询数据
     */
    @RequestMapping(value = "search")
    public String search() {
        log.info("查询数据");
        return "查询数据成功";
    }

}
```

![image-20240711153900800](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407111539020.png)

> [!NOTE]
>
> 如果方法上的 RequestMapping 注解没有使用 method 参数，则可以接收任意类型的请求，如果像删除方法那样指定请求方式，则仅接收 DELETE 方式的请求。

方法上一般不会直接使用 @RequestMapping 注解，根据 RESTFul 风格的接口规范来说：

- 不同功能的接口使用不同的请求方式
- url 上不要包含动词，你是查，还是改都根据请求方式来判断，url 上只表明资源名词，操作学生还是订单

所以方法上的注解还需修改为以下形式：

```java
@RequestMapping("test")
@RestController
@Slf4j
public class TestController {

    /**
     * 添加数据
     */
    @PostMapping
    public String save() {
        log.info("添加数据");
        return "添加数据成功";
    }

    /**
     * 删除数据
     */
    @DeleteMapping
    public String remove() {
        log.info("删除数据");

        return "删除数据成功";
    }

    /**
     * 修改数据
     */
    @PutMapping
    public String update() {
        log.info("修改数据");
        return "修改数据成功";
    }

    /**
     * 查询数据
     */
    @GetMapping
    public String search() {
        log.info("查询数据");
        return "查询数据成功";
    }

}
```

![image-20240711153813617](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407111538486.png)

## 2.2 JSON 数据格式

JSON【JavaScript Object Notation】是一种轻量级的数据格式，用来前后端传输数据是非常方便的，首先很简单，第二容易解析，以键值形式存在

```json
{
  id: 1,
  name: 张三,
  age: 30
}
// JSON 数组
[
  {
    id: 1,
    name: 张三,
    age: 30
  },
  {
    id: 1,
    name: 张三,
    age: 30
  },
]
```

相较于之前使用 xml 传输数据要简单，轻便的多

```xml
<person id = "1">
	<name>张三</name>
  <age>40</age>
</person>
```

> [!NOTE]
>
> 基于 JSON 轻量以及易解析的特点，目前在 web 开发领域，大部分使用 JSON 交互数据

Web 开发中接口接收参数主要分为：

- 单个参数接收
- 数组，集合接收
- JSON 数据接收

```java
@RequestMapping("params")
@RestController
@Slf4j
public class ParamController {

    /**
     * 通过 HttpServletRequest 接收数据，
     * 可以接收 form 表单和地址上数据，接收不到 body 中的 json 数据
     * <p>
     * curl -X "GET" "http://localhost:8080/params/method1" \
     * --data-urlencode 'name=cbq-method01&age=22' \
     */
    @PostMapping("method1")
    public String method1(HttpServletRequest request) {
        // 参数名要与页面提交的参数名一致
        String name = request.getParameter("name");
        String age = request.getParameter("age");

        return "我叫 " + name + "，今年 " + age;
    }

    /**
     * 通过形参接收数据
     * 可以接收 form 表单和地址上数据，接收不到 body 中的 json 数据
     * <p>
     * curl -X "GET" "http://localhost:8080/params/method2?name=cbq-request-param&age=22" \
     * @RequestParam 注解可以设置是否必填和默认值
     */
    @PostMapping("method2")
    public String method2(@RequestParam(value = "name") String name,
                          @RequestParam(value = "age", defaultValue = "10") Integer age) {
        // 参数名要与页面提交的参数名一致
        log.info("name ===> {}", name);
        log.info("age ===> {}", age);
        return "我叫 " + name + "，今年 " + age;
    }

    /**
     * 接收对象数据
     * <p>
     * curl -X "GET" "http://localhost:8080/params/method3?name=name-instance&age=22" \
     */
    @PostMapping("method3")
    public String method3(Account account) {
        // 参数名要与页面提交的参数名一致
        log.info("account-instance ====> {}", account);
        return "我叫 " + account.getName() + "，今年 " + account.getAge();
    }

    /**
     * 接收数组
     * <p>
     * curl -X "GET" "http://localhost:8080/params/method4?balls=ball1,ball2,ball3" \
     */
    @GetMapping("method4")
    public String[] method4(@RequestParam("balls") String[] balls) {
        // 参数名要与页面提交的参数名一致
        log.info("balls ====> {}", Arrays.toString(balls));
        return balls;
    }

    /**
     * 接收 List
     * <p>
     * curl -X "GET" "http://localhost:8080/params/method5?list=item1,item2,item3" \
     */
    @GetMapping("method5")
    public List<String> method5(@RequestParam("list") List<String> list) {
        // 参数名要与页面提交的参数名一致
        log.info("list ====> {}", list);
        return list;
    }

    /**
     * 接收 JSON 数据，json 数据放到 body 中，需要使用 POST、PUT、DELETE 请求，GET 请求不能在 body 中存放数据
     * <p>
     * curl -X "GET" "http://localhost:8080/params/method6" \
     * -H 'Content-Type: application/json' \
     * -d '{
     *   "id": 1,
     *   "name": "张三",
     *   "age": 20,
     *   "jobs": [
     *     "job1",
     *     "job2",
     *     "job3"
     *   ],
     *   "address": {
     *     "name": "家庭住址",
     *     "zipCode": "454700"
     *   },
     *   "time": "2024-06-05 12:12:12"
     * }' \
     */
    @PostMapping("method6")
    public Account method6(@RequestBody Account account) {
        log.info("account-json ====> {}", account);
        return account;
    }

    /**
     * 接收 JSON 数据，json 数据放到 body 中，需要使用 POST、PUT、DELETE 请求，GET 请求不能在 body 中存放数据
     * <p>
     * curl -X "POST" "http://localhost:8080/params/method7" \
     * -H 'Content-Type: application/json' \
     * -d '{"id": 1}' \
     */
    @PostMapping("method7")
    public Map<String, String> method7(@RequestBody Map<String, String> map) {
        log.info("map ====> {}", map);
        return map;
    }

    /**
     * 接收路径参数，常用在 GET 请求根据 id 或者什么关键字获取数据的场景
     * <p>
     * curl -X "GET" "http://localhost:8080/params/method8/1" \
     */
    @GetMapping("method8/{id}")
    public Integer method8(@PathVariable("id") Integer id) {
        log.info("id ====> {}", id);
        return id;
    }

}
```

## 2.3 统一结果返回

前后端分离架构下后端的数据返回通常需要由三部分组成：

- 响应码：表明该次请求的处理结果，通常 200 为成功，500 为服务端异常
- 结果集：如果是查询请求，则需要将结果返回到前端展示，如果不是查询，该字段可以不返回
- 返回信息：返回信息中一般存放前端提示的内容，当然前端也可以根据响应码自己做提示

统一结果返回可以通过自定义结果类，接口统一返回该类就可以，统一响应类通常有三个属性表示响应码，结果集和返回信息，另外还包含：

- 常用的成功和失败的静态方法，用来快速返回数据
- 通过集成 Map 实现，可通过 put 方法添加额外的响应数据

```java
@SuppressWarnings("unused")
@EqualsAndHashCode(callSuper = true)
@Data
public class CommonResult extends HashMap<String, Object> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    public static final String CODE_TAG = "code";

    /**
     * 返回内容
     */
    public static final String MSG_TAG = "msg";

    /**
     * 数据对象
     */
    public static final String DATA_TAG = "data";

    /**
     * 初始化一个新创建的 CommonResult 对象，使其表示一个空消息
     */
    public CommonResult() {
    }

    /**
     * 初始化一个新创建的 CommonResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     */
    public CommonResult(int code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个新创建的 CommonResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    public CommonResult(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (!ObjectUtils.isEmpty(data)) {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static CommonResult success() {
        return CommonResult.success("操作成功");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static CommonResult success(Object data) {
        return CommonResult.success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static CommonResult success(String msg) {
        return CommonResult.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static CommonResult success(String msg, Object data) {
        return new CommonResult(HttpStatus.SUCCESS, msg, data);
    }

    /**
     * 返回警告消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static CommonResult warn(String msg) {
        return CommonResult.warn(msg, null);
    }

    /**
     * 返回警告消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static CommonResult warn(String msg, Object data) {
        return new CommonResult(HttpStatus.WARN, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return 错误消息
     */
    public static CommonResult error() {
        return CommonResult.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 错误消息
     */
    public static CommonResult error(String msg) {
        return CommonResult.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 错误消息
     */
    public static CommonResult error(String msg, Object data) {
        return new CommonResult(HttpStatus.ERROR, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 错误消息
     */
    public static CommonResult error(int code, String msg) {
        return new CommonResult(code, msg, null);
    }

    /**
     * 是否为成功消息
     *
     * @return 结果
     */
    public boolean isSuccess() {
        return Objects.equals(HttpStatus.SUCCESS, this.get(CODE_TAG));
    }

    /**
     * 是否为警告消息
     *
     * @return 结果
     */
    public boolean isWarn() {
        return Objects.equals(HttpStatus.WARN, this.get(CODE_TAG));
    }

    /**
     * 是否为错误消息
     *
     * @return 结果
     */
    public boolean isError() {
        return Objects.equals(HttpStatus.ERROR, this.get(CODE_TAG));
    }

    public static CommonResult getResult(Boolean row) {
        if (row) {
            return CommonResult.success();
        } else {
            return CommonResult.error();
        }
    }

    public static CommonResult getResult(Integer row) {
        if (row > 0) {
            return CommonResult.success();
        } else {
            return CommonResult.error();
        }
    }

    /**
     * 方便链式调用
     *
     * @param key   键
     * @param value 值
     * @return 数据对象
     */
    @Override
    public CommonResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
```

提供项目中使用到的状态码，可以任意自定义，不过还是建议采用规范：

- 2 开头：一般表示处理成功
- 3 开头：重定向，资源跳转相关
- 4 开头：一般表示是否有权限，是否认证，资源是否存在
- 5 开头：一般表示服务端的错误，比如异常，服务长时间未响应等
- 6 开头：一般表示系统警告

```java
public class HttpStatus {
     /**
     * 操作成功
     */
    public static final int SUCCESS = 200;

    /**
     * 对象创建成功
     */
    public static final int CREATED = 201;

    /**
     * 请求已经被接受
     */
    public static final int ACCEPTED = 202;

    /**
     * 操作已经执行成功，但是没有返回数据
     */
    public static final int NO_CONTENT = 204;

    /**
     * 资源已被移除
     */
    public static final int MOVED_PERM = 301;

    /**
     * 重定向
     */
    public static final int SEE_OTHER = 303;

    /**
     * 资源没有被修改
     */
    public static final int NOT_MODIFIED = 304;

    /**
     * 参数列表错误（缺少，格式不匹配）
     */
    public static final int BAD_REQUEST = 400;

    /**
     * 未授权
     */
    public static final int UNAUTHORIZED = 401;

    /**
     * 访问受限，授权过期
     */
    public static final int FORBIDDEN = 403;

    /**
     * 资源，服务未找到
     */
    public static final int NOT_FOUND = 404;

    /**
     * 不允许的http方法
     */
    public static final int BAD_METHOD = 405;

    /**
     * 资源冲突，或者资源被锁
     */
    public static final int CONFLICT = 409;

    /**
     * 不支持的数据，媒体类型
     */
    public static final int UNSUPPORTED_TYPE = 415;

    /**
     * 系统内部错误
     */
    public static final int ERROR = 500;

    /**
     * 接口未实现
     */
    public static final int NOT_IMPLEMENTED = 501;

    /**
     * 系统警告消息
     */
    public static final int WARN = 601;
}
```

以上就是开发一个接口所需的接收参数和返回数据的内容，掌握这些基本够用

## 2.4 统一异常处理

系统内发生异常默认 JVM 会直接抛出，会导致前端显示一堆报错，一来对用户不友好，二来暴露了服务技术不安全，我们可以通过全局统一异常处理，按照我们的响应码规则返回给前端。可以通过两个注解实现：

- @RestControllerAdvice：使用在类上表示是一个全局异常处理类
- @ExceptionHandler：指明要处理的异常类型，一般会使用 Exception 兜底，与 try catch 类似

```java
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public CommonResult handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址 '{}',发生未知异常.", requestURI, e);
        return CommonResult.error(e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public CommonResult handleException(Exception e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址 '{}',发生系统异常. ", requestURI, e);
        return CommonResult.error(e.getMessage());
    }

}
```

当发生异常时就会通过对应的异常处理器处理

```java
@SuppressWarnings("NumericOverflow")
@RequestMapping("exceptions")
@RestController
@Slf4j
public class ExceptionController {

    @GetMapping("math")
    public String mathException() {
        // 运行时异常
        int i = 0 / 0;

        return "0 / 0";
    }
}
```

![image-20240711164308577](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407111643556.png)

当然在系统开发中通常会自定义异常，常见的自定义异常就是业务异常，即项目业务内出现异常，统一抛出自定的业务异常

```java
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public final class ServiceException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误提示
     */
    private String message;

    /**
     * 错误明细，内部调试错误
     * <p>
     */
    private String detailMessage;

}
```

此时在统一异常处理器中添加自定义异常处理的方法

```java
    @ExceptionHandler(ServiceException.class)
    public CommonResult handleServiceException(Exception e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址 '{}',发生业务异常. ", requestURI, e);
        return CommonResult.error(e.getMessage());
    }
```

在抛出对应异常时就会自动处理

```java
    @GetMapping("service")
    public String serviceException() {
        throw new ServiceException(HttpStatus.ERROR,"业务异常","发生业务异常");
    }
```

![image-20240711164347982](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407111643195.png)

## 2.5 参数校验

在后端服务接收数据时为了保障接口安全和过滤无效请求通常会对参数进行校验，符合接口参数规则再处理，否则直接拦截返回不做处理，SpringBoot 中处理参数校验分三步走：

- 引入参数校验依赖
- 定义参数校验规则
- 启用参数校验

1. 引入依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

主要其实是引入 **hibernate-validator** 这个依赖

![image-20240711164711444](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407111647407.png)

2. 定义参数校验规则

此处以实体类接收数据为例，在 Student 类中定义规则

```java
@Data
public class Student {
    @NotNull(message = "id 不能为空")
    private Integer id;
    @Length(min = 2, max = 8, message = "姓名长度为 2-8")
    private String name;
    @Min(value = 0, message = "年龄最小为 0")
    @Max(value = 200, message = "年龄最大为 200")
    private Integer age;
    @NotEmpty(message = "姓名不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;
    @Pattern(regexp = "(^\\\\d{15}$)|(^\\\\d{17}([0-9]|X)$)",message = "身份证格式错误")
    private String idCard;
}
```

3. 启用参数校验

```java
@RequestMapping("students")
@RestController
@Slf4j
public class StudentController {

    @PostMapping
    public CommonResult add(@RequestBody @Validated Student student) {
        return CommonResult.success("添加成功 student is -->", student);
    }

    @GetMapping("/{id}")
    public CommonResult findById(@PathVariable("id") @Min(value = 1,message = "id 最小为 0") Integer id) {
        return CommonResult.success("查找成功 id is -->", id);
    }
}
```

![image-20240711171955312](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407111719616.png)

## 2.6 文件上传

SpringMVC 提供的文件上传通过 **MultipartHttpServletRequest** 实现，以下是该接口的体系结构

![image.png](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407111812942.png)

> [!NOTE]
>
> 文件上传分为单文件上传和批量文件上传，值得注意的是文件上传不能使用 GET 请求，因为文件通常比较大，数据放到 request body中，通常会 **使用POST请求** 实现文件上传，使用 **MultipartFile** 接受文件数据

```java
@RequestMapping("local-files")
@RestController
@Slf4j
public class LocalFileController {

    @PostMapping("single-file")
    public CommonResult singleFile(@RequestParam("file") MultipartFile file) {
        showInfo(file);
        return CommonResult.success();
    }

    /**
     * 多文件上传
     */
    @PostMapping("method2")
    public CommonResult method2(@RequestParam("fileList") MultipartFile[] fileList) {
        for (MultipartFile file : fileList) {
            showInfo(file);
            log.info("============================");
        }

        return CommonResult.success();
    }

    private static void showInfo(MultipartFile file) {
        log.info("文件大小 ==========> {}", file.getSize());
        log.info("文件文件名 ==========> {}", file.getName());
        log.info("文件文本类型 ==========> {}", file.getContentType());
        log.info("文件原始名 ==========> {}", file.getOriginalFilename());
    }
}
```

文件上传时默认请求最大数据量为10M

![image-20240711181917119](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407111819126.png)

可以通过修改配置文件设置上传文件大小和请求数据大小

- 单文件大小：一个文件的最大大小
- 请求文件大小：整个请求的最大大小

比如以下案例一个文件最大是 200MB，请求最多是 400MB，也就是一个文件不要超过 200M，上传多个文件一共不能超过 400M，也就是 200MB 的文件能上传 2 个，100MB 的文件能上传 4 个

```yaml
spring:
  servlet:
    multipart:
      max-file-size: 200MB # 设置单文件大小
      max-request-size: 400MB # 设置请求文件大小
```

![image-20240711181959253](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407111820365.png)

### 2.6.1 上传至本地

文件上传到本地有以下几个步骤：

- 配置文件中指定文件上传目录，指定文件访问路径
- 开发文件上传工具类，没有目录创建目录，重置文件名称，上传文件
- 返回文件名字和文件访问路径

> [!TIP]
>
> 这里我们使用 [Hutool 工具](https://www.hutool.cn/docs/#/?id=%f0%9f%93%a6%e5%ae%89%e8%a3%85)
>
> ```xml
>         <dependency>
>             <groupId>cn.hutool</groupId>
>             <artifactId>hutool-all</artifactId>
>             <version>5.8.16</version>
>         </dependency>
> ```

```yaml
spring:
  servlet:
    multipart:
      max-file-size: 200MB # 设置单文件大小
      max-request-size: 400MB # 设置请求文件大小
  mvc:
    static-path-pattern: /file/** # 配置静态资源访问前缀
  web:
    resources:
      static-locations: file:D:/dev-tools/file-up # 设置静态资源映射路径
cbq:
  upload_path: D:/dev-tools/file-up # 自定义配置，文件上传路径

```

```java
@Slf4j
@Component
public class FileUploadUtils {
    // 使用静态属性，通过 set 方法注入值，类上需要加 @Component 注解才能完成值注入
    private static String uploadPath;

    @Value("${cbq.upload_path}")
    public void setUploadPath(String uploadPath) {
        FileUploadUtils.uploadPath = uploadPath;
    }

    /**
     * 文件上传
     */
    public static final String upload(MultipartFile file)
            throws IOException {

        String fileName = extractFilename(file);
        String absPath = getAbsoluteFile(fileName).getAbsolutePath();
        file.transferTo(Paths.get(absPath));
        return fileName;
    }

    /**
     * 编码文件名
     */
    public static final String extractFilename(MultipartFile file) {
        return StrUtil.format("{}.{}", UUID.randomUUID().toString(), getExtension(file));
    }

    public static final File getAbsoluteFile(String fileName) throws IOException {
        File desc = new File(uploadPath + File.separator + fileName);
        if (!desc.exists()) {
            if (!desc.getParentFile().exists()) {
                desc.getParentFile().mkdirs();
            }
        }
        return desc;
    }

    /**
     * 获取文件名的后缀
     */
    public static final String getExtension(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();

        return originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
    }
}
```

```java
    @PostMapping("/s_upload")
    public CommonResult uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(file);
            HashMap<String, String> map = new HashMap<>();
            // 返回值根据需求返回就可以了
            map.put("fileName", fileName);
            map.put("url", "http://localhost:8080/file/" + fileName);
            return CommonResult.success(map);
        } catch (Exception e) {
            return CommonResult.error(e.getMessage());
        }
    }
```

![image-20240711183326669](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407111833820.png)

![image-20240711183303295](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407111833345.png)

### 2.6.2 上传至阿里云 OSS

文件还可以上传到其他服务商提供的文件存储平台上，比如七牛云、阿里云、华为云、腾讯云等，好处在于这些平台提供了高可用的文件存储能力，支持断点续传，大文件分片上传等功能，弊端在于收费，至于平台的稳定性选择大厂家都要比自己的稳吧！

这里选用阿里云 OSS 服务存储文件，步骤基本如下：

- 需要有阿里云账号，可以通过手机号注册，实名认证一下就可以了，企业的话使用营业执照做企业认证可以有更多优惠
- 开启阿里云 OSS 服务，创建子账号操作服务
- 创建文件存储空间，阿里云中一个存储空间称为一个 bucket
- 导入依赖，开发功能

> [!TIP]
>
> 注册账号这块就省略了到官网自己注册就行，官网地址：https://www.aliyun.com/

1. 开启阿里云服务

TODO

2. 引入阿里云 OSS 依赖

```xml
<dependency>
    <groupId>com.aliyun.oss</groupId>
    <artifactId>aliyun-sdk-oss</artifactId>
    <version>3.15.1</version>
</dependency>
```

如果使用的是 Java 9 及以上的版本，则需要添加 JAXB 相关依赖。添加 JAXB 相关依赖示例代码如下：

```xml
<dependency>
    <groupId>javax.xml.bind</groupId>
    <artifactId>jaxb-api</artifactId>
    <version>2.3.1</version>
</dependency>
<dependency>
    <groupId>javax.activation</groupId>
    <artifactId>activation</artifactId>
    <version>1.1.1</version>
</dependency>
<!-- no more than 2.3.3-->
<dependency>
    <groupId>org.glassfish.jaxb</groupId>
    <artifactId>jaxb-runtime</artifactId>
    <version>2.3.3</version>
</dependency>
```

3. 上传文件

```java
@Slf4j
public class OSSUploadUtils {
    public static String uploadOSS(MultipartFile file) {
        // Endpoint 以华东 1（杭州）为例，其它 Region 请按实际情况填写。
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        String keyId = "换成自己的 key";
        String keySecret = "换成自己的 key";
        // 填写 Bucket 名称，例如 example bucket
        String bucketName = "stt-study";
        // 设置文件名
        String fileName = extractFilename(file);

        // 创建 OSSClient 实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, keyId, keySecret);

        try {
            // 创建 PutObjectRequest 对象
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, file.getInputStream());

            // 上传文件
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            log.info("result======>{}", result);
            return fileName;
        } catch (OSSException oe) {
            log.info("OSSException===》{}", oe.getMessage());
            throw new ServiceException("文件上传失败");
        } catch (ClientException ce) {
            log.info("ClientException===》{}", ce.getMessage());
            throw new ServiceException("文件上传失败");
        } catch (IOException e) {
            log.info("IOException===》{}", e.getMessage());
            throw new ServiceException("文件上传失败");
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
```



# 三、拦截器

当请求来到 DispatcherServlet 时， 它会根据 HandlerMapping 的机制找到处理器这样就会返回一个 HandlerExecutionChain 对象，这个对象包含处理器和拦截器。这里的拦截器会对处理器进行拦截，通过拦截器增强处理器的功能。

拦截器（Interceptor）是一种特殊的组件，它可以在请求处理的过程中对请求和响应进行拦截和处理。拦截器可以在请求到达目标处理器之前、处理器处理请求之后以及视图渲染之前执行特定的操作。拦截器的主要目的是在不修改原有代码的情况下，实现对请求和响应的统一处理。

拦截器可以用于实现以下功能：

1. 权限控制：拦截器可以在请求到达处理器之前进行权限验证，从而实现对不同用户的访问控制
2. 日志记录：拦截器可以在请求处理过程中记录请求和响应的详细信息，便于后期分析和调试
3. 接口幂等性校验：拦截器可以在请求到达处理器之前进行幂等性校验，防止重复提交
4. 数据校验：拦截器可以在请求到达处理器之前对请求数据进行校验，确保数据的合法性
5. 缓存处理：拦截器可以在请求处理之后对响应数据进行缓存，提高系统性能

## 3.1 拦截器与过滤器的区别

拦截器和过滤器都可以实现对请求和响应的拦截和处理，但它们之间存在以下区别：

1. 执行顺序：过滤器在拦截器之前执行，拦截器在处理器之前执行
2. 功能范围：过滤器可以对所有请求进行拦截，而拦截器只能对特定的请求进行拦截
3. 生命周期：过滤器由 Servlet 容器管理，拦截器由 Spring 容器管理
4. 使用场景：过滤器适用于对请求和响应的全局处理，拦截器适用于对特定请求的处理

## 3.2 拦截器实现

所有的拦截器都应实现 HandlerInterceptor 接口，该接口实现如下：

```java
public interface HandlerInterceptor {

	/**
    在请求到达处理器之前执行，可以用于权限验证、数据校验等操作。
    如果返回 true，则继续执行后续操作；如果返回 false，则中断请求处理
	 */
	default boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		return true;
	}

	// 在处理器处理请求之后执行，可以用于日志记录、缓存处理等操作
	default void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
	}

	// 在视图渲染之后执行，可以用于资源清理等操作
	default void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
	}

}
```

## 3.3 自定义拦截器

```java
@Slf4j
public class DemoInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle 执行");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle 执行");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion 执行");
    }
}
```

新建类实现 WebMvcConfigurer 接口，类上使用 @Configuration 注解，重写 addInterceptors 方法配置自定义的拦截器

```java
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DemoInterceptor())
                .addPathPatterns("/**") // 设置匹配路径
                .excludePathPatterns("/params/method1"); // 设置忽略路径
    }
}
```

![image-20240711184421091](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407111844645.png)

![image-20240711184444152](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407111844477.png)

## 3.4 多拦截器

再定义一个控制接口幂等的拦截器，意思就是不可重复点击，实现思路:

- 进入接口之前判断是否正在访问，可以将是否正在访问的状态记录到 Map 中，项目中建议使用 redis 记录
- 访问接口之后将这个状态移除，案例中我们就没有移除，因为此处使用的 Map 不是项目共享的

```java
@Slf4j
public class IdempotentInterceptor implements HandlerInterceptor {
    Map<String, String> map = new HashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");
        String requestURI = request.getRequestURI();
        log.info("token ====> {}", token);
        log.info("requestURI ====> {}", requestURI);
        if (map.get(token + requestURI) == null) {
            map.put(token + requestURI, token);
            return true;
        }
        log.info("接口已访问过");
        return false;
    }
}
```

```java
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DemoInterceptor())
                .addPathPatterns("/**") // 设置匹配路径
                .excludePathPatterns("/params/method1"); // 设置忽略路径
        registry.addInterceptor(new IdempotentInterceptor()).addPathPatterns("/**"); // 注册幂等拦截器 [!code ++]

    }
}
```

> [!TIP]
>
> 执行时拦截器执行顺序按照注册顺序

![image-20240711185017945](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407111850301.png)

## 3.5 拦截器的性能优化和常见问题

拦截器在请求处理过程中可能会影响系统性能，以下是一些性能优化策略：

1. 减少拦截器数量：尽量将相关功能集中到一个拦截器中，避免创建过多的拦截器
2. 精确配置拦截规则：通过 addPathPatterns 和 excludePathPatterns 方法精确配置拦截规则，避免不必要的拦截
3. 使用异步处理：在拦截器中使用异步处理，避免阻塞请求处理过程
4. 使用缓存：在拦截器中使用缓存，减少对数据库或其他资源的访问

拦截器是一种用于处理请求和响应的中间件，它可以在请求到达目标处理器之前或响应返回客户端之前执行一些操作。然而，在实际使用过程中，我们可能会遇到一些问题，如拦截器不生效、执行顺序错误或影响性能等。接下来，我们将逐一分析这些问题的原因及解决方法。

1. 拦截器不生效：拦截器不生效的可能原因有很多，其中最常见的包括拦截器未注册到 InterceptorRegistry、拦截规则配置错误等。为了解决这个问题，我们需要首先检查拦截器是否已经正确注册到 InterceptorRegistry 中，然后再检查拦截规则是否配置正确。如果发现问题，需要及时进行调整和修复
2. 拦截器执行顺序错误：拦截器执行顺序错误的主要原因是拦截器的注册顺序错误。在实际应用中，拦截器的执行顺序是根据它们在InterceptorRegistry 中的注册顺序来决定的。因此，为了解决这个问题，我们需要调整拦截器在 InterceptorRegistry 中的注册顺序，确保它们按照预期的顺序执行
3. 拦截器影响性能：拦截器影响性能的主要原因是拦截器中的处理逻辑过于复杂或资源消耗过大。为了解决这个问题，我们需要对拦截器的处理逻辑进行优化，尽量减少不必要的计算和资源消耗。同时，我们还可以考虑使用一些性能监控工具，如 JProfiler 等，来对拦截器的性能进行实时监控和分析，从而找到性能瓶颈并进行优化

拦截器在实际应用中可能会遇到一些问题，但只要我们能够深入了解其原理和机制，就可以找到合适的解决方案。
