package jzxy.cbq.service;

import jzxy.cbq.entity.DataSource;
import lombok.Data;

/**
 * @author: cbq1024
 * @description: DataCollectionService
 * @since 2024/7/10 下午11:13
 */
@Data
public class DataCollectionService {

    private DataSource dataSource;

    public void collect() {
        String url = dataSource.getUrl();
        String driverClassName = dataSource.getDriverClassName();
        System.out.println("连接数据库 ======> ");
        System.out.println("url ====>" + url);
        System.out.println("driverClassName ===>》" + driverClassName);
    }
}
