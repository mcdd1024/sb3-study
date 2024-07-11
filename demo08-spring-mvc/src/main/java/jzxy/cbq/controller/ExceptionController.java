package jzxy.cbq.controller;

import jzxy.cbq.entity.HttpStatus;
import jzxy.cbq.exceptions.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: cbq1024
 * @description: ExceptionController
 * @since 2024/7/11 下午4:31
 */
@RequestMapping("exceptions")
@RestController
@Slf4j
public class ExceptionController {

    @GetMapping("math")
    public String mathException() {
        // 运行时异常
        int i = 0 / 0;

        return "0 / 0";
    }

    @GetMapping("service")
    public String serviceException() {
        throw new ServiceException(HttpStatus.ERROR,"业务异常","发生业务异常");
    }

}
