package org.herba.controller;

import org.herba.model.dto.UploadResult;
import org.herba.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 工具控制器
 * Created by gravel on 2017/11/17.
 */


@CrossOrigin
@RestController
public class ToolController {
    @Value("${imageShowApi}")
    public String imageShowApi;
    @Value("${fileDownloadApi}")
    public String fileDownloadApi;
    @Value("${imageFilePath}")
    public String imageFilePath;
    @Value("${filePath}")
    public String filePath;
    private final ResourceLoader resourceLoader;


    @Autowired
    public ToolController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * uploadFile 上传文件
     *
     * @param type image:图片,file:文件
     */
    @RequestMapping(value = "/upload/{type}", method = RequestMethod.POST)
    public UploadResult uploadFile(HttpServletRequest request, @PathVariable String type) {
        List<String> ListApi = new ArrayList<String>();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "后台处理上传");
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    String fileName = file.getOriginalFilename();
                    if (type.equals("image")) {
                        FileUtils.uploadFile(file.getBytes(), imageFilePath, fileName);
                        ListApi.add(imageShowApi + fileName);
                    } else {
                        FileUtils.uploadFile(file.getBytes(), filePath, fileName);
                        ListApi.add(fileDownloadApi + fileName);
                    }
                } catch (Exception e) {
                    return new UploadResult(0, e.getMessage(), new ArrayList<>());
                }
            }
        }
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "后台处理上传成功");

        return new UploadResult(1, "上传成功", ListApi);
    }

    //获取图片
    @RequestMapping(value = "/getImage/{filename:.+}")
    public ResponseEntity<?> geImage(@PathVariable String filename) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "后台获取名为" + filename + "的图片");
        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(imageFilePath, filename).toString()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    //下载文件
    @RequestMapping(value = "/getFile/{filename:.+}")
    public String getFile(@PathVariable String filename, HttpServletResponse response){
        File file = new File(filePath + "/" + filename);
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + filename);
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("----------file download" + filename);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}