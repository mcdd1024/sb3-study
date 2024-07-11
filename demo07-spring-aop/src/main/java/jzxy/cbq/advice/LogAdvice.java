package jzxy.cbq.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author: cbq1024
 * @description: LogAdvice
 * @since 2024/7/11 上午9:03
 */
@Aspect
@Component
@Slf4j
public class LogAdvice {
    // 定义切点
    @Pointcut("execution(* jzxy.cbq.service.AccountService.*(..))")
    public void afterLog() {
    }
    // 使用切点到增强上
    @After("afterLog()")
    public void insertLog() {
        log.info("=============== 日志记录 =================");
    }
}
