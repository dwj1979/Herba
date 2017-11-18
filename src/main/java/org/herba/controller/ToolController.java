package org.herba.controller;

import org.herba.util.FileUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
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
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String uploadFile(HttpServletRequest request) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "后台处理图片上传");
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        String filePath = "src/main/resources/uploadFiles/";
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    String fileName = file.getOriginalFilename();
                    FileUtils.uploadFile(file.getBytes(), filePath, fileName);
                } catch (Exception e) {
                    return "{'code':'0','msg':'上传失败，" + e.getMessage() + "' }";
                }
            }
        }
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "后台处理图片上传成功");
        return "{'code':'1','msg':'上传成功！' }";
    }
}