package jzxy.cbq.proxy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: cbq1024
 * @description: JdkProxyServiceProxy
 * @since 2024/7/11 下午2:32
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class JdkProxyServiceProxy implements InvocationHandler {

    private final JdkProxyService target;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        log.info("========== JDK 动态代理 ==========");
        // 调用被代理对象方法
        result = method.invoke(target, args);
        return result;
    }
}
