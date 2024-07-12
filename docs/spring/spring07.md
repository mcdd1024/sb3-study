# 集成 MybatisPlus

使用 Java 开发 Web 应用与数据库交互是绕不开的话题，与数据库交互的技术包含最基础的 JDBC，和在其上封装的框架如：Hibernate、MyBatis、JPA 等，目前市场上使用较多的是 MyBatis，而 MyBatis-Plus 是在其基础上的进一步增强，来简化 MyBatis 使用提高开发效率，还增强了一部分功能，详细信息可以移步[【MyBatis-Plus官网】](https://baomidou.com/)。本文档讲解内容如下：

> [!NOTE]
>
> - MyBatis-Plus 快速使用
> - 条件构造器
> - 逻辑删除，主键生成策略，乐观锁插件
> - 多表查询
> - 多数据源

# 一、MybatisPlus 简介

## 1.1 概述

[MyBatis-Plus](https://github.com/baomidou/mybatis-plus) 是一个 [MyBatis](https://www.mybatis.org/mybatis-3/) 的增强工具，在 MyBatis 的基础上只做增强不做改变，为简化开发、提高效率而生。

![image.png](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407121457171.png)

## 1.2 特性

- **无侵入**：只做增强不做改变，引入它不会对现有工程产生影响，MyBatis可照常使用
- **损耗小**：启动即会自动注入基本 CURD，性能基本无损耗，直接面向对象操作
- **强大的 CRUD 操作**：内置通用 Mapper、通用 Service，仅仅通过少量配置即可实现单表大部分 CRUD 操作，更有强大的条件构造器，满足各类使用需求
- **支持 Lambda 形式调用**：通过 Lambda 表达式，方便的编写各类查询条件，无需再担心字段写错
- **支持主键自动生成**：支持多达 4 种主键策略（内含分布式唯一 ID 生成器 - Sequence），可自由配置，完美解决主键问题
- **支持 ActiveRecord 模式**：支持 ActiveRecord 形式调用，实体类只需继承 Model 类即可进行强大的 CRUD 操作
- **支持自定义全局通用操作**：支持全局通用方法注入（ Write once, use anywhere ）
- **内置代码生成器**：采用代码或者 Maven 插件可快速生成 Mapper 、 Model 、 Service 、 Controller 层代码，支持模板引擎，更有超多自定义配置等您来使用
- **内置分页插件**：基于 MyBatis 物理分页，开发者无需关心具体操作，配置好插件之后，写分页等同于普通 List 查询
- **分页插件支持多种数据库**：支持 MySQL、MariaDB、Oracle、DB2、H2、HSQL、SQLite、Postgre、SQLServer 等多种数据库
- **内置性能分析插件**：可输出 SQL 语句以及其执行时间，建议开发测试时启用该功能，能快速揪出慢查询
- **内置全局拦截插件**：提供全表 delete 、 update 操作智能分析阻断，也可自定义拦截规则，预防误操作

![image.png](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407121457793.png)

# 二、环境准备

在介绍 mybatis-plus 逻辑删除和自动填充时首先搭建项目和准备一张数据表，此案例通过 springboot+mysql+mybatis-plus 实现

## 2.1 数据库环境准备

1. 创建数据表

```sql
CREATE TABLE `tb_student` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '学生 ID',
  `class_id` int(11) NOT NULL COMMENT '班级编号',
  `dorm_id` int(11) DEFAULT NULL COMMENT '宿舍编号',
  `major_id` int(11) DEFAULT NULL COMMENT '专业 ID',
  `stu_no` varchar(50) NOT NULL COMMENT '学号',
  `stu_name` varchar(50) NOT NULL COMMENT '学生姓名',
  `stu_sex` char(1) NOT NULL DEFAULT '0' COMMENT '用户性别（0 男 1 女 2 未知）',
  `stu_mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `status` char(1) NOT NULL COMMENT '学生状态（0 正常 1请假 2 休学 3 退学 4 毕业）',
  `is_delete` BINARY(1) DEFAULT '0' COMMENT '删除标志（0 未删除 1 已删除）',
  `create_id` bigint(11) not null COMMENT '创建者',
	`update_id` bigint(63) not null COMMENT '修改者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='学生信息记录';
```

2. 连接 mysql

![image-20240712145943308](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407121459376.png)

![image-20240712150055526](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407121500716.png)

## 2.2 依赖导入并配置

![image-20240712151339200](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407121513358.png)

```xml
 <dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-spring-boot3-starter</artifactId>
    <version>3.5.7</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>
```

> [!NOTE]
>
> 我们可以通过 `${K:V}` 的形式在配置文件中使用环境变量，以保护敏感信息，例如 `${MYSQL_USERNAME:root}` 表示优先使用环境变量中 key 为 `MYSQL_USERNAME` 的变量，如果没有则使用 `root`

```yaml
# 数据库配置
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/mybatis-plus?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=GMT%2B8&allowMultiQueries=true
    username: ${MYSQL_USERNAME:root}
    password: ${MYSQL_PASSWORD:root}
# mybatis-plus 配置
mybatis-plus:
  configuration:
    # 配置输出 sql 到控制台
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl

```

![image-20240712150654621](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407121506603.png)

## 2.4 dev

1. 创建 studnet 实体类

```java
@Data
@TableName(value = "tb_student")
public class Student implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer classId;
    private Integer dormId;
    private Integer majorId;
    private String stuNo;
    private String stuName;
    private Integer stuSex;
    private String stuMobile;
    private Integer status;
    private Integer isDelete;
    private Long createId;
    private Long updateId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String remark;

}
```

2. mapper

```java
public interface StudentMapper extends BaseMapper<Student> {
}
```

3. service

```java
public interface StudentService extends IService<Student> {
}

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
```

4. 配置 MapperScan

```java
@MapperScan("jzxy.cbq.mapper")
@SpringBootApplication
@Slf4j
public class Demo11MyBatisPlusApplication {
    public static void main(String[] args) {
        SpringApplication.run(Demo11MyBatisPlusApplication.class, args);

        log.info("Demo11MyBatisPlusApplication run successful ");
    }
}
```

![image-20240712151432722](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407121514621.png)

# 三、特殊功能

## 3.1 逻辑删除

逻辑删除是为了方便数据恢复和保护数据潜在价值的一种手段，即当用户不需要某一个数据时就希望删除掉，此时有物理删除和逻辑删除两种方案。

- 物理删除：将数据真实的从数据库【磁盘】中删除掉
- 逻辑删除：数据并没有真正删除，还在数据库【磁盘】上个保存，只是在查询时过滤掉被删除的数据，对用户来说就是删除掉了

### 3.1.1 实现原理

物理删除通知数据库执行删除操作。即删除的 sql

```sql
-- 删除 id 为 1 的学生
DELETE FROM tb_student WHERE id = 1
```

逻辑删除的实现方式是在数据表中添加一个是否删除字段，注意该字段不要和数据的状态字段耦合，比如学生有很多状态：正常，请假，休学，退学。可以使用 status 字段表示，而该学生被删除，则可以使用 is_delete 字段表示，我们暂定 0 为未删除，1 为删除，那么在删除数据时其实就是修改该字段的值了

```sql
UPDATE tb_student set is_delete = 1 WHERE id = 2;
```

相应的在查询时，就需要带上is_delete字段作为判断条件

```sql
-- 查询所有学生
SELECT * FROM tb_student WHERE is_delete = 0;
```

在项目中实现原理相同，此时发现一个不方便的问题就是在查询时都要带上 is_delete = 0 的判断，删除的话修改该字段的值，mybatis-plus 提供了 `逻辑删除` 的机制

- 查询：自动追加 is_delete = 0 的条件，过滤掉已被删除的数据
- 修改：修改时也会追加 is_delete = 0 的条件，防止修改已被删除的数据
- 删除：将删除变为 update 语句，语句同上述逻辑删除 sql

### 3.1.2 代码实现

使用方法有两种方式，但都需要保障一个前提：

- 保障数据表中有一个字段标识该数据是否删除，类型无所谓【推荐使用 Integer、Boolean】类型，官方还推荐使用LocalDateTime，emmm~~~~

实现方式：

- 在实体类中的对应字段上添加 @TableLogic 注解 
- 或者 在 application.yml 中配置 mybatis-plus 的逻辑删除

此时数据表和实体类中都已经有一个名为 is_delete 的字段标识是否删除，当然该字段的名字你可以任意取名，下边就从第二步开始

#### A. 通过 @TableLogic 注解实现

在实体类中的 isDelete 字段上添加该注解，该注解有两个属性，意义如下

- value：表示未删除的值，0 代表如果值为 0 则是没有删除
- delval：表示已删除的值，1 代表该数据已删除

```java
@TableLogic(value = "0", delval = "1")
private Integer isDelete;
```

查询测试

```java
@RequestMapping("students")
@RestController
@Slf4j
@RequiredArgsConstructor
public class StudentController {
    private final StudentService service;
    
     /**
     * curl -X "POST" "http://localhost:8080/students" \
     * -H 'Content-Type: application/json' \
     * -d '{
     *   "id": 1,
     *   "classId": 1,
     *   "dormId": 1,
     *   "majorId": 1,
     *   "stuNo": "stuNo_ehwdk",
     *   "stuName": "stuName_szz7h",
     *   "stuSex": 1,
     *   "stuMobile": "stuMobile_xewyb",
     *   "status": 1,
     *   "isDelete": 0,
     *   "createId": 1,
     *   "updateId": 1,
     *   "remark": "remark_yl2le"
     * }' \
     */
    @PostMapping
    public Student save(@RequestBody Student student) {
        boolean saved = service.save(student);
        return saved ? student : null;
    }

    @GetMapping
    public List<Student> list() {
        return service.list();
    }
    
}
```

![image-20240712152800762](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407121528824.png)

删除测试

```java
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        boolean remove = service.removeById(id);
        return remove ? "删除成功" : "删除失败";
    }
```

![image-20240712152923645](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407121529598.png)

![image-20240712152936437](https://2024-cbq-1311841992.cos.ap-beijing.myqcloud.com/picgo/202407121529625.png)

#### B. 通过 application.yml 配置文件实现

还有一种方式就是不使用注解，在 application.yml 文件中配置

```yaml
# Mybatis-plus 配置
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  global-config:
    db-config:
      logic-delete-field: isDelete # 全局逻辑删除的实体字段名 (since 3.3.0)
    	# 如果你的逻辑删除值和默认值相同则可以不配置以下两项
      logic-delete-value: 1 # 逻辑已删除值(默认为 1)
      logic-not-delete-value: 0 # 逻辑未删除值(默认为 0)
```

两种效果相同，不过推荐使用配置文件的方式，好处在于全局共用，即不需要每个实体类中都在 isDelete 属性上写 @TableLogic 注解，前提是你的项目中的逻辑删除规则是一样

### 3.1.3 注意

也不是说项目中都要使用逻辑删除，是需求而定，逻辑删除有一个致命缺陷在于如果数据量剧增，mysql 在单表数据达到 300万 以上时查询性能就会明显下降，此时可以将逻辑删除的数据统一迁移到另一个表中存储，正常的业务还是使用原表，如果需要用到原来被删除的数据，则可以到另一张表中查询
