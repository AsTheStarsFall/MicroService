package com.tianhy.study.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FeignUploadController {

    @PostMapping(value = "/uploadFile/server", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String fileUploadServer(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();

        //生成的新的文件名称
        String newFileName = UUID.randomUUID() + originalFilename;

        //设置存储的路径: /uploadFile/文件名称
        String path = File.separator + "uploadFile" + File.separator + newFileName;

        //当前项目的工作目录
        String currPath = System.getProperty("user.dir");
        //文件的路径：当前工作目录/存储路径
        File destFile = new File(currPath + path);
        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdir();
        }

        try {
            file.transferTo(destFile);
        } catch (IOException e) {
            e.printStackTrace();
            return "false";
        }
        return originalFilename;
    }

}
