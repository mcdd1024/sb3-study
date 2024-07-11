package jzxy.cbq;

import jzxy.cbq.entity.Account;
import jzxy.cbq.proxy.*;
import jzxy.cbq.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Proxy;

/**
 * @author: cbq1024
 * @description: Demo07SpringAopApplication
 * @since 2024/7/11 上午8:44
 */
@SpringBootApplication
@Slf4j
public class Demo07SpringAopApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Demo07SpringAopApplication.class, args);

        demo01(context);
        staticProxy(context);
        jdkProxy(context);
        cglibProxy(context);

        log.info("Demo07SpringAopApplication run successful ");
    }

    private static void cglibProxy(ApplicationContext context) {
        CGLIBProxyService service = context.getBean(CGLIBProxyService.class);
        CGLIBProxyService proxy = (CGLIBProxyService)Enhancer.create(service.getClass(), new CGLIBProxyInterceptor(service));
        proxy.cglIbMethod();
    }

    private static void jdkProxy(ApplicationContext context) {
        JdkProxyService service = context.getBean(JdkProxyService.class);
        JdkProxyServiceProxy proxy = context.getBean(JdkProxyServiceProxy.class);
        ClassLoader classLoader = service.getClass().getClassLoader();
        Class<?>[] interfaces = service.getClass().getInterfaces();
        JdkProxyService jdkProxyService = (JdkProxyService)Proxy.newProxyInstance(classLoader, interfaces, proxy);
        jdkProxyService.jdkProxyMethod();
    }

    private static void staticProxy(ApplicationContext context) {
        StaticProxyServiceProxy proxy = context.getBean(StaticProxyServiceProxy.class);
        proxy.staticProxyMethod();
    }

    private static void demo01(ApplicationContext context) {
        AccountService service = context.getBean(AccountService.class);
        Account account = new Account();

        service.add(account);
        service.delete(1);
        service.update(account);
        service.list();
    }
}
