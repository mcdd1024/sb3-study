package jzxy.cbq;

import jzxy.cbq.entity.CBQ;
import jzxy.cbq.entity.MySQLDataSource;
import jzxy.cbq.entity.Redis;
import jzxy.cbq.enums.LocalEnv;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

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

        Environment environment = context.getBean(Environment.class);

        String java_home = environment.getProperty(String.valueOf(LocalEnv.JAVA_HOME));
        String mvn_home = environment.getProperty(String.valueOf(LocalEnv.MVN_HOME));
        String gradle_home = environment.getProperty(String.valueOf(LocalEnv.GRADLE_HOME));
        System.out.println("java_home = " + java_home);
        System.out.println("maven_home = " + mvn_home);
        System.out.println("gradle_home = " + gradle_home);


        log.info("Demo04ConfigFileApplication run successful");
    }

}
