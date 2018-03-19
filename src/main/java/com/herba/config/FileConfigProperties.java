package com.herba.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "herba.file")
//spring boot1.5以上版本@ConfigurationProperties取消location后需要手动制定配置文件来源
@PropertySource("classpath:application.yml")
public class FileConfigProperties {
    private String imageFilePath;
    private String filePath;
    private String imageShowApi;
    private String fileDownloadApi;

}
