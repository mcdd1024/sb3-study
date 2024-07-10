package jzxy.cbq.controller;

import jzxy.cbq.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author: cbq1024
 * @description: StudentController
 * @since 2024/7/10 下午4:43
 */
@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    @GetMapping
    List<Student> list() {
        return Arrays.asList(
                new Student(1, "cbq", 22, "cbq@gmail.com"),
                new Student(2, "cq", 18, "cb@qq.com"));
    }
}
