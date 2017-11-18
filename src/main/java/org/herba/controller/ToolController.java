package org.herba.controller;

import org.herba.model.dto.UploadResult;
import org.herba.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 工具控制器
 * Created by gravel on 2017/11/17.
 */


@CrossOrigin
@RestController
public class ToolController {
    public final String LINKAPI = "http://localhost:8081/getImage/";
    private final ResourceLoader resourceLoader;
    public final String imageFilePath = "src/main/resources/uploadFiles/";

    @Autowired
    public ToolController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    //上传文件
    @RequestMapping(value = "/upload/image", method = RequestMethod.POST)
    public UploadResult uploadFile(HttpServletRequest request) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "后台处理图片上传");
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    String fileName = file.getOriginalFilename();
                    System.out.println(fileName + imageFilePath + file.getBytes());
                    FileUtils.uploadFile(file.getBytes(), imageFilePath, fileName);
                } catch (Exception e) {
                    return new UploadResult(0, e.getMessage(), "");
                }
            }
        }
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "后台处理图片上传成功");
        return new UploadResult(1, "上传成功", LINKAPI + files.get(0).getOriginalFilename());
    }

    //获取图片
    @RequestMapping(value = "/getImage/{filename:.+}")
    public ResponseEntity<?> getFile(@PathVariable String filename) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "后台获取名为" + filename + "的图片");
        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(imageFilePath, filename).toString()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}