package org.jelly.frame.core.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

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


    public String getBucketName() {
        return properties.getBucketName();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        
    }
}
