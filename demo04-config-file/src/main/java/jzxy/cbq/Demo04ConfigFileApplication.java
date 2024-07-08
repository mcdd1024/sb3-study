package jzxy.cbq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author: cbq1024
 * @description: Demo04ConfigFileApplication
 * @since 2024/7/8 下午9:07
 */
@SpringBootApplication
@Slf4j
public class Demo04ConfigFileApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Demo04ConfigFileApplication.class, args);


        log.info("Demo04ConfigFileApplication run successful");
    }
}
