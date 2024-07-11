package jzxy.cbq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: cbq1024
 * @description: Demo12RedisApplication
 * @since 2024/7/12 上午4:10
 */
@SpringBootApplication
@Slf4j
public class Demo12RedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(Demo12RedisApplication.class, args);

        log.info("Demo12RedisApplication run successful ");
    }
}
