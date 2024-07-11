package jzxy.cbq.proxy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: cbq1024
 * @description: StaticProxyServiceProxy
 * @since 2024/7/11 上午9:20
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StaticProxyServiceProxy implements StaticProxyService {

    private final StaticProxyService staticProxyServiceImpl;

    @Override
    public void staticProxyMethod() {
        log.info("========== 静态代理对象方法 ==========");
        // 调用原对象方法
        staticProxyServiceImpl.staticProxyMethod();
    }
}
