package jzxy.cbq.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: cbq1024
 * @description: CGLIBProxyServiceImpl
 * @since 2024/7/11 下午2:42
 */
@Service
@Slf4j
public class CGLIBProxyServiceImpl implements CGLIBProxyService {
    @Override
    public void cglIbMethod() {
        log.info("=============== CGLIB 代理实现类方法 ===============");

    }
}
