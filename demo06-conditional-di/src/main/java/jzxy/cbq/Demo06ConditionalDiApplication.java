package jzxy.cbq;

import jzxy.cbq.service.DataCollectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author: cbq1024
 * @description: Demo06ConditionalDiApplication
 * @since 2024/7/10 下午11:08
 */
@SpringBootApplication
@Slf4j
public class Demo06ConditionalDiApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Demo06ConditionalDiApplication.class, args);

        DataCollectionService service = context.getBean(DataCollectionService.class);

        System.out.println("service = " + service);
        service.collect();

        log.info("Demo06ConditionalDiApplication run successful ");
    }
}
