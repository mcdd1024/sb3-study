package jzxy.cbq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: cbq1024
 * @description: HelloController
 * @since 2024/7/10 下午4:41
 */
@RestController
@RequestMapping("api/v1/hellos")
public class HelloController {

    @GetMapping("sayHello")
    public String sayHello() {
        return "Hello Spring Boot";

    }
}
