package jzxy.cbq.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: cbq1024
 * @description: JdkProxyServiceImpl
 * @since 2024/7/11 下午2:31
 */
@Service
@Slf4j
public class JdkProxyServiceImpl implements JdkProxyService {
    @Override
    public void jdkProxyMethod() {
        log.info("=============== JDK 动态代理接口方法 ===============");

    }
}
