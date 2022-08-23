package org.jelly.frame.core.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for Minio.
 *
 * @author zhangxb
 */
@ConfigurationProperties(prefix = MinioProperties.MINIO_PREFIX)
@Data
public class MinioProperties {
    public static final String MINIO_PREFIX = "minio";

    /**
     * endPoint是一个URL，域名，IPv4或者IPv6地址。以下是合法的endpoints:
     */
    private String endpoint;
    /**
     * TCP/IP端口号。可选，默认值是，如果是http,则默认80端口，如果是https,则默认是443端口。
     */
    private Integer port;
    /**
     * accessKey类似于用户ID，用于唯一标识你的账户。
     */
    private String accessKey;
    /**
     * secretKey是你账户的密码。
     */
    private String secretKey;
    /**
     * 如果是true，则用的是https而不是http,默认值是true。
     */
    private boolean secure;
    /**
     * 存储桶名称
     */
    private String bucketName;
}
