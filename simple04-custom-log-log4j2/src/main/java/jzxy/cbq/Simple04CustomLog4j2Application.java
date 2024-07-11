package jzxy.cbq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: cbq1024
 * @description: Simple04CustomLog4j2Application
 * @since 2024/7/12 上午3:31
 */
@SpringBootApplication
@Slf4j
public class Simple04CustomLog4j2Application {
    public static void main(String[] args) {
        SpringApplication.run(Simple04CustomLog4j2Application.class, args);

        log.info("Simple04CustomLog4j2Application run successful ");
    }
}
