package jzxy.cbq.entity;

import lombok.Data;

/**
 * @author: cbq1024
 * @description: MySQLDataSource
 * @since 2024/7/8 下午9:03
 */
@Data
public class MySQLDataSource {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
}
