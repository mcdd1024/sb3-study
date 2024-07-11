package jzxy.cbq.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import jzxy.cbq.entity.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: cbq1024
 * @description: GlobalExceptionHandler
 * @since 2024/7/11 下午4:27
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public CommonResult handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址 '{}',发生未知异常.", requestURI, e);
        return CommonResult.error(e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public CommonResult handleException(Exception e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址 '{}',发生系统异常. ", requestURI, e);
        return CommonResult.error(e.getMessage());
    }

    @ExceptionHandler(ServiceException.class)
    public CommonResult handleServiceException(Exception e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址 '{}',发生业务异常. ", requestURI, e);
        return CommonResult.error(e.getMessage());
    }
}
