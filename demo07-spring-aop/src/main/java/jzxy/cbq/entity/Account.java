package jzxy.cbq.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: cbq1024
 * @description: Account
 * @since 2024/7/11 上午8:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private int id;
    private String name;
    private int age;
    private String email;
}
