package jzxy.cbq.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: cbq1024
 * @description: IdempotentInterceptor
 * @since 2024/7/11 下午6:46
 */
@Slf4j
public class IdempotentInterceptor implements HandlerInterceptor {
    Map<String, String> map = new HashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");
        String requestURI = request.getRequestURI();
        log.info("token ====> {}", token);
        log.info("requestURI ====> {}", requestURI);
        if (map.get(token + requestURI) == null) {
            map.put(token + requestURI, token);
            return true;
        }
        log.info("接口已访问过");
        return false;
    }
}
