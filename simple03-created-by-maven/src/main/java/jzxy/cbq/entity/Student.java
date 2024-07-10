package jzxy.cbq.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: cbq1024
 * @description: Student
 * @since 2024/7/10 下午4:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private int id;
    private String name;
    private int age;
    private String email;
}
