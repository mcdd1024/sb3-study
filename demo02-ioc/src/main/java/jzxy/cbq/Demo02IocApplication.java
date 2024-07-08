package jzxy.cbq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: cbq1024
 * @description: Demo02IocApplication
 * @since 2024/7/8 下午5:40
 */
@SpringBootApplication
@Slf4j
public class Demo02IocApplication {
    public static void main(String[] args) {
        SpringApplication.run(Demo02IocApplication.class, args);

        log.info("Demo02IocApplication run successful ");
    }
}
