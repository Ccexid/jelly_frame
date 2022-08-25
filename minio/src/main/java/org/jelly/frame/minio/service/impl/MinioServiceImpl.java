package org.jelly.frame.minio.service.impl;

import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.crypto.SecureUtil;
import io.minio.*;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.jelly.frame.core.autoconfigure.MinioAutoConfiguration;
import org.jelly.frame.core.handler.JellyCheckedException;
import org.jelly.frame.core.handler.JellyRuntimeException;
import org.jelly.frame.minio.service.IMinioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Minio 服务接口
 *
 * @author zhangxb
 */
@Service
@Slf4j
public class MinioServiceImpl implements IMinioService {

    private final MinioAutoConfiguration minioAutoConfiguration;

    @Autowired
    public MinioServiceImpl(MinioAutoConfiguration minioAutoConfiguration) {
        this.minioAutoConfiguration = minioAutoConfiguration;
    }


    /**
     * {@link MinioClient} build
     *
     * @param bucketName 桶 名称
     * @return {@link MinioClient}
     * @throws Exception ErrorResponseException – thrown to indicate S3 service returned an error response.
     *                   IllegalArgumentException – throws to indicate invalid argument passed.
     *                   IllegalArgumentException – throws to indicate invalid argument passed.
     *                   InsufficientDataException – thrown to indicate not enough data available in InputStream.
     *                   InternalException – thrown to indicate internal library error.
     *                   InvalidBucketNameException – thrown to indicate invalid bucket name passed.
     *                   InvalidKeyException – thrown to indicate missing of HMAC SHA-256 library.
     *                   InvalidResponseException – thrown to indicate S3 service returned invalid or no error response.
     *                   IOException – thrown to indicate I/O error on S3 operation.
     *                   NoSuchAlgorithmException – thrown to indicate missing of MD5 or SHA-256 digest library.
     *                   XmlParserException – thrown to indicate XML parsing error.
     */
    private MinioClient buildClient(String bucketName) throws Exception {
        MinioClient minioClient;
        try {
            if (StringUtils.isEmpty(bucketName)) {
                throw new JellyCheckedException("bucketName must be fill in");
            }
            minioClient = MinioClient.builder()
                    .endpoint(minioAutoConfiguration.getProperties().getEndpoint())
                    .credentials(minioAutoConfiguration.getProperties().getAccessKey(),
                            minioAutoConfiguration.getProperties().getSecretKey())
                    .build();
            //判断 桶 是否存在
            boolean bucketExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            //不存在 需要创建 桶
            if (!bucketExist) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }

        } catch (ErrorResponseException | IllegalArgumentException | InsufficientDataException |
                InternalException | InvalidBucketNameException | InvalidKeyException |
                InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException |
                XmlParserException | JellyCheckedException | SecurityException e) {
            throw new JellyRuntimeException(e);
        }

        return minioClient;
    }

    @Override
    public void upload(MultipartFile file) throws Exception {
        try {
            if (file.isEmpty()) {
                throw new JellyCheckedException("获取文件为空，请检查文件路径或文件文件完整性!");
            }
            MinioClient client = buildClient(minioAutoConfiguration.getBucketName());

            // 对文件重新编码加密
            //重新命名文件
            String fileName = FileNameUtil.mainName(file.getOriginalFilename());
            String fileExt = FileNameUtil.extName(file.getOriginalFilename());
            String name = SecureUtil.md5(fileName) + "." + fileExt;

            ObjectWriteResponse response = client.putObject(PutObjectArgs
                    .builder()
                    .bucket(minioAutoConfiguration.getBucketName())
                    .contentType(file.getContentType())
                    .object(name)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .build());

            Assert.notNull(response, "文件上传失败");

            //TODO 数据记录需要保存到 库中

        } catch (IllegalAccessException | SecurityException | InvalidBucketNameException | IllegalArgumentException | NoSuchAlgorithmException |
                InsufficientDataException | IOException | InvalidKeyException | ServerException |
                XmlParserException | ErrorResponseException | InternalException | InvalidResponseException e) {
            log.error("-IMinioService- [upload](83) RuntimeException ", e);
            throw new JellyRuntimeException(e);
        }
    }

    @Override
    public void download(String fileName, HttpServletResponse response) throws Exception {
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        ServletOutputStream os = null;
        try {
            if (StringUtils.isEmpty(fileName)) {
                throw new JellyCheckedException("获取文件为空，请检查文件名正确性!");
            }
            MinioClient client = buildClient(minioAutoConfiguration.getBucketName());
            //执行前先查询是否存在 文件
            ObjectStat stat = client.statObject(StatObjectArgs.builder()
                    .bucket(minioAutoConfiguration.getBucketName())
                    .object(fileName).build());
            Assert.notNull(stat, "获取文件失败");

            inputStream = client.getObject(GetObjectArgs.builder()
                    .bucket(stat.bucketName())
                    .object(stat.name()).build());

            outputStream = new ByteArrayOutputStream();
            IOUtils.copy(inputStream, outputStream);
            byte[] bytes = outputStream.toByteArray();
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.setContentType("application/octet-stream");
            os = response.getOutputStream();
            os.write(bytes);
            os.flush();

        } catch (IllegalAccessException | SecurityException | InvalidBucketNameException | IllegalArgumentException | NoSuchAlgorithmException |
                InsufficientDataException | IOException | InvalidKeyException | ServerException |
                XmlParserException | ErrorResponseException | InternalException | InvalidResponseException e) {
            log.error("-IMinioService- [download](10) RuntimeException ", e);
            throw new JellyRuntimeException(e);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            if (os != null) {
                os.close();
            }
        }
    }
}
