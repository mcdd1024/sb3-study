package jzxy.cbq.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author: cbq1024
 * @description: TestController
 * @since 2024/7/11 下午3:28
 */
@RequestMapping("test")
@RestController
@Slf4j
public class TestController {

    /**
     * 添加数据
     */
    @PostMapping
    public String save() {
        log.info("添加数据");
        return "添加数据成功";
    }

    /**
     * 删除数据
     */
    @DeleteMapping
    public String remove() {
        log.info("删除数据");

        return "删除数据成功";
    }

    /**
     * 修改数据
     */
    @PutMapping
    public String update() {
        log.info("修改数据");
        return "修改数据成功";
    }

    /**
     * 查询数据
     */
    @GetMapping
    public String search() {
        log.info("查询数据");
        return "查询数据成功";
    }

}
