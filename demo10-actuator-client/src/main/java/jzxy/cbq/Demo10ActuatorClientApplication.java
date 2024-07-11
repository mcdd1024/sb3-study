package jzxy.cbq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: cbq1024
 * @description: Demo10ActuatorClientApplication
 * @since 2024/7/12 上午4:07
 */
@SpringBootApplication
@Slf4j
public class Demo10ActuatorClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(Demo10ActuatorClientApplication.class, args);

        log.info("Demo10ActuatorClientApplication run successful ");
    }
}
