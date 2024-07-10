package jzxy.cbq.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author: cbq1024
 * @description: Dog
 * @since 2024/7/10 下午11:43
 */
@Scope(value = "singleton")
@Component
public class Dog {
}
