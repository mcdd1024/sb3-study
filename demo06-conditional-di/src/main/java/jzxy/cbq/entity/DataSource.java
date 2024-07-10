package jzxy.cbq.entity;

import lombok.Data;

/**
 * @author: cbq1024
 * @description: DataSource
 * @since 2024/7/10 下午11:12
 */
@Data
public class DataSource {

    private String driverClassName;
    private String url;
    private String username;
    private String password;

}
