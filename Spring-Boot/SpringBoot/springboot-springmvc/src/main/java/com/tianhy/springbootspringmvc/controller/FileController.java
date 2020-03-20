package com.tianhy.springbootspringmvc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.*;
import java.io.*;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * {@link}
 *
 * @Desc: 文件上传
 * @Author: thy
 * @CreateTime: 2019/5/21
 **/
@RequestMapping("/file")
@Controller
public class FileController {

    @Value("${spring.servlet.multipart.location}")
    private String multipart_location;

    @GetMapping("/upload/page")
    public String uploadPage() {
        return "/file/upload";
    }

    @PostMapping("/upload/request")
    @ResponseBody
    public Map<String, Object> uploadRequest(HttpServletRequest request) {
        boolean flag = false;
        MultipartHttpServletRequest mreq = null;
        if (request instanceof MultipartHttpServletRequest) {
            mreq = (MultipartHttpServletRequest) request;
        } else {
            return resultMap(flag, "上传失败");
        }
        //获取文件信息
        MultipartFile mf = mreq.getFile("file");
        //获取源文件名称
        String originalFilename = mf.getOriginalFilename();
        File file = new File(originalFilename);
        try {
            //保存文件
            mf.transferTo(file);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(flag, "上传失败");
        }
        return resultMap(flag, "上传成功");
    }

    // 使用Spring MVC的MultipartFile类作为参数
    @PostMapping("/upload/multipart")
    @ResponseBody
    public Map<String, Object> uploadMultipartFile(MultipartFile file) {
        //获取源文件名称
        String originalFilename = file.getOriginalFilename();
        //生成的新的文件名称
        String newFileName = UUID.randomUUID() + originalFilename;

        //设置存储的路径: /uploadFile/文件名称
        String path = File.separator + "uploadFile" + File.separator + newFileName;

        //当前项目的工作目录
        String currPath = System.getProperty("user.dir");
        //文件的路径：当前工作目录/存储路径
        File destFile = new File(path);
        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdir();
        }
        try {
            file.transferTo(destFile);
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(false, "上传失败");
        }
        return resultMap(true, "上传成功");
    }

    @PostMapping("/upload/part")
    @ResponseBody
    public Map<String, Object> uploadRequest(Part file) {
        String fileName = file.getSubmittedFileName();
        try {
            file.write(fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap(false, "上传失败");
        }
        return resultMap(true, "上传成功");
    }


    public Map<String, Object> resultMap(boolean success, String msg) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        result.put("msg", msg);
        return result;
    }


    //OutputStream的形式
    @GetMapping("/download")
    public String downloadFile(HttpServletRequest request, HttpServletResponse resp) {
        String contentType = request.getContentType();
        //获取要下载的文件
        String realPath = multipart_location + File.separator + "uploadFile";
        File fileDir = new File(realPath);
        //文件夹下所有文件
        File[] files = fileDir.listFiles();
        //获取第一个
        System.out.println(files[0]);
        //文件名称
        String fileName = files[0].getName();

        if (fileName != null) {
            File file = new File(realPath, fileName);
            if (file.exists()) {
                // 配置文件下载
                resp.setHeader("content-type", "application/octet-stream");
                resp.setContentType("application/octet-stream");
                // 下载文件能正常显示中文
                try {
                    resp.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                // 实现文件下载
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                OutputStream os = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    os = resp.getOutputStream();
//                    int i = bis.read(buffer);
//                    while (i != -1) {
//                        os.write(buffer, 0, i);
//                        i = bis.read(buffer);
//                    }
                    int len;
                    while ((len = bis.read(buffer)) > 0) {
                        os.write(buffer, 0, len);
                    }
                    System.out.println("download success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }

    //ResponseEntity的形式
    @GetMapping(value = "/download1", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<InputStreamResource> download(Long id) {

//        String realPath = multipart_location + File.separator + "uploadFile";
//        File file = new File(realPath);
//        File[] files = file.listFiles();
//        String fileName = files[2].getName();
//        String filePath = realPath + File.separator + fileName;
        String filePath = multipart_location + File.separator + "uploadFile" + File.separator + id + "_file.txt";
        try {
            FileSystemResource fileSystemResource = new FileSystemResource(filePath);
//                //文件名转码
//                fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            //设置响应头
            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            //弹出下载框
            headers.add("Content-Disposition", "attachment;filename=" + fileSystemResource.getFilename());
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            ResponseEntity responseEntity = new ResponseEntity(new InputStreamResource(fileSystemResource.getInputStream()), headers, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
