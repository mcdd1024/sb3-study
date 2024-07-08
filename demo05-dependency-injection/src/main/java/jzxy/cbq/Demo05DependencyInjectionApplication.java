package jzxy.cbq;

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

        log.info("Demo05DependencyInjectionApplication run successful ");
    }
}
