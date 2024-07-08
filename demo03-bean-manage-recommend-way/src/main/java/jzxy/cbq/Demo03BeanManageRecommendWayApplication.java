package jzxy.cbq;

import jzxy.cbq.entity.MySQLDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author: cbq1024
 * @description: Demo03BeanManageRecommendWayApplication
 * @since 2024/7/8 下午8:58
 */
@SpringBootApplication
@Slf4j
public class Demo03BeanManageRecommendWayApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Demo03BeanManageRecommendWayApplication.class, args);

        MySQLDataSource dataSource = context.getBean(MySQLDataSource.class);
        System.out.println("dataSource = " + dataSource);

        log.info("Demo03BeanManageRecommendWayApplication run successful ");
    }
}
