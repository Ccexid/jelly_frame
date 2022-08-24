package org.jelly.frame.minio.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Minio 服务接口
 *
 * @author zhangxb
 */
public interface IMinioService {
    /**
     * 文件上传
     * @param file {@link MultipartFile}
     * @throws Exception 异常
     */
    void upload(MultipartFile file) throws Exception;
}
