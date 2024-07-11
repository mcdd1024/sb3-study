package jzxy.cbq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: cbq1024
 * @description: ShowInfo
 * @since 2024/7/12 上午3:03
 */
@RestController
@RequestMapping("logs")
@Slf4j
public class ShowInfo {

    @GetMapping("test")
    public String test1() {
        log.trace("trace 日志");
        if (log.isDebugEnabled()){
            log.debug("debug 日志");
        }
        log.info("info 日志");
        log.warn("warn 日志");
        log.error("error 日志");
        return "日志打印";
    }
}
