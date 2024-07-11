package jzxy.cbq.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: cbq1024
 * @description: Account
 * @since 2024/7/11 下午3:43
 */
@Data
public class Account {
    private Integer id;
    private String name;
    private Integer age;
    private List<String> jobs;
    private Address address;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;
}
