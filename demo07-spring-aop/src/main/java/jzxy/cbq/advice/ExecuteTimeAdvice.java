package jzxy.cbq.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author: cbq1024
 * @description: ExecuteTimeAdvice
 * @since 2024/7/11 上午9:13
 */
@Aspect
@Component
@Slf4j
public class ExecuteTimeAdvice {
    /**
     * 获取执行时间，在执行之前
     */
    @Around("execution(* jzxy.cbq.service.AccountService.*(..))")
    public Object getTimeLong(ProceedingJoinPoint joinPoint) {
        Object result = null;
        long startTime = System.currentTimeMillis();
        // 前置业务代码
        log.info("执行前时间 =========> {}", startTime);
        try {
            // 执行目标方法
            result = joinPoint.proceed();
        } catch (Throwable e) {
            if (log.isDebugEnabled()) {
                log.debug(e.getMessage(), e);
            }
        }
        // 后置业务执行代码
        long endTime = System.currentTimeMillis();
        log.info("执行耗时 =========> {} 毫秒", endTime - startTime);
        return result;
    }
}
