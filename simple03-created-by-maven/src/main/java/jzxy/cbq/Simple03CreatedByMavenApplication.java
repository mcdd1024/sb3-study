package jzxy.cbq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: cbq1024
 * @description: Simple03CreatedByMavenApplication
 * @since 2024/7/10 下午4:20
 */
@SpringBootApplication
@Slf4j
public class Simple03CreatedByMavenApplication {
    public static void main(String[] args) {
        SpringApplication.run(Simple03CreatedByMavenApplication.class, args);

        log.info("Simple03CreatedByMavenApplication run successful ");
    }
}
