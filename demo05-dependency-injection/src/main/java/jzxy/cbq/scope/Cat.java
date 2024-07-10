package jzxy.cbq.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author: cbq1024
 * @description: Cat
 * @since 2024/7/10 下午11:40
 */
@Scope(value = "prototype")
@Component
public class Cat {
}
