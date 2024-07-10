package jzxy.cbq.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author: cbq1024
 * @description: CBQ
 * @since 2024/7/9 下午2:12
 */
@Configuration // 首先需要通过 @Configuration 注册为 Bean 交由 Spring 管理然后才能读取配置文件中的值
@ConfigurationProperties(prefix = "cbq")
@Data
public class CBQ {
    private String name;
    private Integer age;
    @Value("${idol}")
    private String idol;
    private List<String> hobby;

}
