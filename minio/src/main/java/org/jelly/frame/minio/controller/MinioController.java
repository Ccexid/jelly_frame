package org.jelly.frame.minio.controller;

import org.jelly.frame.core.api.ResultsHelper;
import org.jelly.frame.minio.service.IMinioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zhangxb
 */
@RestController
public class MinioController {
    private final IMinioService iMinioService;

    @Autowired
    public MinioController(IMinioService iMinioService) {
        this.iMinioService = iMinioService;
    }

    @PostMapping(value = "/upload", produces = "application/json;charset=utf-8")
    public ResultsHelper<Object> upload(@RequestParam(value = "file") MultipartFile file) throws Exception {
        iMinioService.upload(file);
        return ResultsHelper.builder().success("文件上传成功").build();
    }
    @GetMapping(value = "/download/{fileName}", produces = "application/json;charset=utf-8")
    public void download(@PathVariable("fileName") String fileName, HttpServletResponse response) throws Exception {
        iMinioService.download(fileName,response);
    }
}
