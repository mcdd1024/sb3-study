package jzxy.cbq;

import jzxy.cbq.entity.CBQ;
import jzxy.cbq.entity.MySQLDataSource;
import jzxy.cbq.entity.Redis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author: cbq1024
 * @description: Demo04ConfigFileApplication
 * @since 2024/7/8 下午9:07
 */
@SpringBootApplication
@Slf4j
public class Demo04ConfigFileApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Demo04ConfigFileApplication.class, args);

        CBQ cbq = context.getBean(CBQ.class);
        MySQLDataSource dataSource = context.getBean(MySQLDataSource.class);
        Redis redis = context.getBean(Redis.class);
        System.out.println("cbq = " + cbq);
        System.out.println("dataSource = " + dataSource);
        System.out.println("redis = " + redis);

        log.info("Demo04ConfigFileApplication run successful");
    }
}
