package jzxy.cbq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: cbq1024
 * @description: Demo01TemplateApplication
 * @since 2024/7/8 上午10:46
 */
@SpringBootApplication
@Slf4j
public class Demo01TemplateApplication {
    public static void main(String[] args) {
        SpringApplication.run(Demo01TemplateApplication.class, args);

        log.info("Demo01TemplateApplication run successful ");
    }
}
