package jzxy.cbq.config;

import jzxy.cbq.interceptors.DemoInterceptor;
import jzxy.cbq.interceptors.IdempotentInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: cbq1024
 * @description: WebConfiguration
 * @since 2024/7/11 下午6:41
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DemoInterceptor())
                .addPathPatterns("/**") // 设置匹配路径
                .excludePathPatterns("/params/method1"); // 设置忽略路径
        registry.addInterceptor(new IdempotentInterceptor()).addPathPatterns("/**"); // 注册幂等拦截器

    }
}
