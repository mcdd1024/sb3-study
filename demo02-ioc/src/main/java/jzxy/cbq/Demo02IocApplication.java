package jzxy.cbq;

import jzxy.cbq.entity.Book;
import jzxy.cbq.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author: cbq1024
 * @description: Demo02IocApplication
 * @since 2024/7/8 下午5:40
 */
@Slf4j
@ComponentScan("jzxy.cbq")
public class Demo02IocApplication {
    public static void main(String[] args) {
        getContextByClassPathXML();
        getContextByFileSystemXML();
        getContextByAnnotationConfig();
    }

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

    /**
     * 通过 AnnotationConfig 方式获取 context
     */
    private static void getContextByAnnotationConfig() {
        log.info("============ getContextByAnnotationConfig ===============\n");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("jzxy.cbq"); // 也可以写成 new AnnotationConfigApplicationContext(Demo02IocApplication.class); 前提是需要 Demo02IocApplication.class 上标有 @ComponentScan("jzxy.cbq")
        Student student = context.getBean(Student.class);
        Book book = context.getBean(Book.class);
        System.out.println("student = " + student + "\n");
        System.out.println("book = " + book + "\n");
        log.info("============ getContextByAnnotationConfig ===============\n");
    }
}
