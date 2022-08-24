package org.jelly.frame.minio.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

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

    /**
     * 文件下载
     * @param fileName 文件名称
     * @param response 返回
     * @throws Exception
     */
    void download(String fileName, HttpServletResponse response) throws Exception;
}
