package com.crq.article.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: crq
 * @create: 2022-03-25 23:23
 **/
@Data
@Component
@PropertySource("classpath:file.properties")
@ConfigurationProperties(prefix = "file")
public class FileResource {
    private String host;
    private String endPoint;
    private String bucketName;
    private String objectName;
    private String ossHost;
}
