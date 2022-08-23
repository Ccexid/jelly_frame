package org.jelly.frame.core.autoconfigure;

import com.github.pagehelper.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 分页插件配置
 *
 * @author zhangxb
 */
@Configuration
public class PageHelperConfigure {

    @Bean
    PageInterceptor pageInterceptor() {
        return new PageInterceptor();
    }
}
