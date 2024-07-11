package jzxy.cbq.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: cbq1024
 * @description: StaticProxyServiceImpl
 * @since 2024/7/11 上午9:19
 */
@Service
@Slf4j
public class StaticProxyServiceImpl implements StaticProxyService {
    @Override
    public void staticProxyMethod() {
        log.info("=============== 静态代理对象原对象方法 ===============");
    }
}
