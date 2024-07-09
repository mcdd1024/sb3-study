package jzxy.cbq.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author: cbq1024
 * @description: Redis
 * @since 2024/7/9 下午2:24
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "redis")
@PropertySource("classpath:application-redis.yaml")
public class Redis {
    private String host;
    private Integer port;
}
