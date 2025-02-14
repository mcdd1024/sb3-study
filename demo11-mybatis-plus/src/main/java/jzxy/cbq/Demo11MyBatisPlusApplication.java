package jzxy.cbq;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: cbq1024
 * @description: Demo11MyBatisPlusApplication
 * @since 2024/7/12 上午4:10
 */
@MapperScan("jzxy.cbq.mapper")
@SpringBootApplication
@Slf4j
public class Demo11MyBatisPlusApplication {
    public static void main(String[] args) {
        SpringApplication.run(Demo11MyBatisPlusApplication.class, args);

        log.info("Demo11MyBatisPlusApplication run successful ");
    }
}
