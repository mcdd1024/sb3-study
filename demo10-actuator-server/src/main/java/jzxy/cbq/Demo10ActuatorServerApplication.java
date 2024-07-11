package jzxy.cbq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: cbq1024
 * @description: Demo10ActuatorServerApplication
 * @since 2024/7/12 上午4:08
 */
@SpringBootApplication
@Slf4j
public class Demo10ActuatorServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(Demo10ActuatorServerApplication.class, args);

        log.info("Demo10ActuatorServerApplication run successful ");
    }
}
