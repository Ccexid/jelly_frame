package org.jelly.frame;

import lombok.extern.slf4j.Slf4j;
import org.jelly.frame.core.utils.JellySpringBeanUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author zhangxb
 */
@SpringBootApplication
@Slf4j
public class JellyApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(JellyApplication.class, args);
        JellySpringBeanUtil.setContext(context);
    }
}
