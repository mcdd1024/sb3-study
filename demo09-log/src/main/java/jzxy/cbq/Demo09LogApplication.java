package jzxy.cbq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: cbq1024
 * @description: Demo09LogApplication
 * @since 2024/7/12 上午2:41
 */
@SpringBootApplication
@Slf4j
public class Demo09LogApplication {

    public static void main(String[] args) {
        SpringApplication.run(Demo09LogApplication.class, args);

        log.info("Demo09LogApplication run successful ");
    }

}
