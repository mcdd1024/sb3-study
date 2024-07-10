package jzxy.cbq;

import jzxy.cbq.scope.Cat;
import jzxy.cbq.scope.Dog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author: cbq1024
 * @description: Demo05DependencyInjectionApplication
 * @since 2024/7/8 下午9:16
 */
@SpringBootApplication
@Slf4j
public class Demo05DependencyInjectionApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Demo05DependencyInjectionApplication.class, args);

        Cat cat1 = context.getBean(Cat.class);
        Cat cat2 = context.getBean(Cat.class);

        System.out.println("cat1 = " + cat1);
        System.out.println("dog2 = " + cat2);

        Dog dog1 = context.getBean(Dog.class);
        Dog dog2 = context.getBean(Dog.class);

        System.out.println("dog1 = " + dog1);
        System.out.println("dog2 = " + dog2);


        log.info("Demo05DependencyInjectionApplication run successful ");
    }
}
