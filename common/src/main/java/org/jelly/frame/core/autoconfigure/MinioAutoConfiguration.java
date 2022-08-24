package org.jelly.frame.core.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * {@link EnableAutoConfiguration Auto-Configuration} for Minio.
 *
 * @author zhangxb
 */
@Configuration
@EnableConfigurationProperties(MinioProperties.class)
public class MinioAutoConfiguration implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(MinioAutoConfiguration.class);

    private final MinioProperties properties;

    public MinioAutoConfiguration(MinioProperties properties) {
        this.properties = properties;
    }

    @Bean
    public String getBucketName() {
        return properties.getBucketName();
    }

    @Bean
    public MinioProperties getProperties() {
        return properties;
    }

    @Override
    public void afterPropertiesSet() {
        checkConfigItemEmpty();
    }

    /**
     * 检查配置文件必填项完整性
     */
    private void checkConfigItemEmpty() {
        if (StringUtils.hasText(properties.getEndpoint())
                && StringUtils.hasText(properties.getAccessKey())
                && StringUtils.hasText(properties.getSecretKey())) {
            logger.info("Minio 配置校验通过  properties = {}", properties);
        } else {
            logger.error("配置文件缺失，请检查配置完整性 ! [endpoint]:{},[accessKey]:{},[secretKey]:{},",
                    !StringUtils.hasText(properties.getEndpoint()),
                    !StringUtils.hasText(properties.getAccessKey()),
                    !StringUtils.hasText(properties.getSecretKey()));
        }
    }
}
