package jzxy.cbq.config;

import jzxy.cbq.entity.DataSource;
import jzxy.cbq.service.DataCollectionService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: cbq1024
 * @description: DataSourceConfiguration
 * @since 2024/7/10 下午11:14
 */
@Configuration
public class DataSourceConfiguration {

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    @Bean
    // 当 DataSource 这个 bean 存在时加载
    @ConditionalOnBean(DataSource.class)
    public DataCollectionService dateCollectionService() {
        DataCollectionService dateCollectionService = new DataCollectionService();
        System.out.println("dataSource() ====> " + dataSource());
        dateCollectionService.setDataSource(dataSource());
        return dateCollectionService;
    }

}
