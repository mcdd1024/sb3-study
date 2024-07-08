package jzxy.cbq.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: cbq1024
 * @description: Student
 * @since 2024/7/8 下午5:43
 */
@Data
@Component("cbq")
public class Student {
    @Value("name-annotation")
    private String name;
    @Value("22")
    private int age;
    @Value("email-annotation")
    private String email;
}
