package jzxy.cbq.proxy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author: cbq1024
 * @description: CGLIBProxyInterceptor
 * @since 2024/7/11 下午2:43
 */
@Slf4j
@RequiredArgsConstructor
public class CGLIBProxyInterceptor implements MethodInterceptor {
    //被代理对象
    private final CGLIBProxyService target;
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        log.info("============= CGLIB 代理方法 =============");
        //通过 cglib 的代理⽅法调⽤
        return proxy.invoke(target, args);
    }
}
