package jzxy.cbq.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author: cbq1024
 * @description: MySQLDataSource
 * @since 2024/7/9 下午2:17
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "db")
@PropertySource("classpath:application-db.properties")
public class MySQLDataSource {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
}
