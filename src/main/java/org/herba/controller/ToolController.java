package org.herba.controller;

import org.herba.util.FileUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 工具控制器
 * Created by gravel on 2017/11/17.
 */
@RestController
@CrossOrigin
public class ToolController {
    //处理文件上传
    @RequestMapping(value="/uploadFile", method = RequestMethod.POST)
    public @ResponseBody
    String uploadFile(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        String filePath = "src/main/resources/uploadFiles/";
        for (MultipartFile file: files) {
            if (!file.isEmpty()) {
                try {
                    String fileName = file.getOriginalFilename();
                    FileUtils.uploadFile(file.getBytes(), filePath, fileName);
                } catch (Exception e) {
                    return "{'code':'0','msg':'上传失败，"+e.getMessage()+"' }";
                }
            }
        }
        return "{'code':'1','msg':'上传成功！' }";
    }

    }