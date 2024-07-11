package jzxy.cbq.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author: cbq1024
 * @description: PermissionsAdvice
 * @since 2024/7/11 上午8:56
 */
@Aspect
@Component
@Slf4j
public class PermissionsAdvice {

    @Before("execution(* jzxy.cbq.service.AccountService.add(..)) || " +
            "execution(* jzxy.cbq.service.AccountService.update(..)) || " +
            "execution(* jzxy.cbq.service.AccountService.delete(..))")
    public void checkPermission() {
        log.info("=============== 权限校验 =================");
    }
}
