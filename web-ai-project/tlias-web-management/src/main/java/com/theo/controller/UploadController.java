package com.theo.controller;

import com.aliyun.oss.OSSClient;
import com.theo.pojo.Result;
import com.theo.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

/**
 * 本地磁盘文件上传方案
 */
    /*@PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传: {}", file.getOriginalFilename());
        //获取文件原始名称
        String originalFilename = file.getOriginalFilename();
        //生成新的文件名称
        String newFileName = UUID.randomUUID().toString() + "_" + originalFilename;
        //保存文件到指定位置
        file.transferTo(new File("D:/uploads/" + newFileName));

        return Result.success();
    }*/

    /**
     * 云存储文件上传方案
     */
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传: {}", file.getOriginalFilename());
        //将文件交给oss操作类进行上传
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("文件上传oss，url：{}", url);
        return Result.success(url);
    }
}
