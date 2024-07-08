package jzxy.cbq.config;

import jzxy.cbq.entity.MySQLDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: cbq1024
 * @description: DataSourceConfiguration
 * @since 2024/7/8 下午9:03
 */
@Configuration
public class DataSourceConfiguration {

    @Bean
    public MySQLDataSource initDataSource() {
        MySQLDataSource dataSource = new MySQLDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }
}
