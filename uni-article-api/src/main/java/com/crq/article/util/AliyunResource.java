package com.crq.article.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: crq
 * @create: 2022-03-23 14:54
 **/
@Component
@Data
@PropertySource("classpath:aliyun.properties")
@ConfigurationProperties(prefix = "aliyun")
public class AliyunResource {
    private String accessKeyId;
    private String accessKeySecret;
    private String signName;
    private String templateCode;

}
