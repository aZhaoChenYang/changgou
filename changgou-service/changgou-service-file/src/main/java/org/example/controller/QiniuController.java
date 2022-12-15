package org.example.controller;

import org.example.service.QiniuImageService;
import org.example.util.QiniuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/qiniu")
public class QiniuController {
    @Autowired
    QiniuImageService qiniuImageService;

    @PostMapping
    private String upLoadImage(@RequestParam("file") MultipartFile file) throws IOException {

        // 获取文件的名称
        String fileName = file.getOriginalFilename();

        // 使用工具类根据上传文件生成唯一图片名称
        assert fileName != null;
        String imgName = QiniuUtil.getRandomImgName(fileName);

        if (!file.isEmpty()) {

            FileInputStream inputStream = (FileInputStream) file.getInputStream();

            String path = qiniuImageService.uploadQiniuImage(inputStream, imgName);
            System.out.print("七牛云返回的图片链接:" + path);
            return path;
        }
        return "上传失败";
    }

}

