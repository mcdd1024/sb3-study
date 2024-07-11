package jzxy.cbq.entity;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author: cbq1024
 * @description: Student
 * @since 2024/7/11 下午4:48
 */
@Data
public class Student {
    @NotNull(message = "id 不能为空")
    private Integer id;
    @Length(min = 2, max = 8, message = "姓名长度为 2-8")
    private String name;
    @Min(value = 0, message = "年龄最小为 0")
    @Max(value = 200, message = "年龄最大为 200")
    private Integer age;
    @NotEmpty(message = "姓名不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;
    @Pattern(regexp = "(^\\\\d{15}$)|(^\\\\d{17}([0-9]|X)$)",message = "身份证格式错误")
    private String idCard;
}
