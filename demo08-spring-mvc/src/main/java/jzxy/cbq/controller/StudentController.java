package jzxy.cbq.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jzxy.cbq.entity.CommonResult;
import jzxy.cbq.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author: cbq1024
 * @description: StudentController
 * @since 2024/7/11 下午4:54
 */
@RequestMapping("students")
@RestController
@Slf4j
public class StudentController {

    @PostMapping
    public CommonResult add(@RequestBody @Validated Student student) {
        return CommonResult.success("添加成功 student is -->", student);
    }

    @GetMapping("/{id}")
    public CommonResult findById(@PathVariable("id") @Min(value = 1,message = "id 最小为 0") Integer id) {
        return CommonResult.success("查找成功 id is -->", id);
    }
}
