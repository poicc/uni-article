package com.crq.article.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description:
 * @author: crq
 * @create: 2022-03-23 14:52
 **/
@Component
@Slf4j
public class SmsUtil {
    /**
     * 注入阿里云配置类实例
     */
    @Resource
    private AliyunResource aliyunResource;

    /**
     * 发短信
     *
     * @param phone 手机号
     * @param code  短信JSON串
     */
    public boolean sendSms(String phone, String code) {
        log.info(String.valueOf(aliyunResource));
        log.info(code);
        Config config = new Config()
                .setAccessKeyId(aliyunResource.getAccessKeyId())
                .setAccessKeySecret(aliyunResource.getAccessKeySecret());
        config.endpoint = "dysmsapi.aliyuncs.com";
        try {
            Client client = new Client(config);
            SendSmsRequest sendSmsRequest = new SendSmsRequest()
                    .setPhoneNumbers(phone)
                    .setSignName(aliyunResource.getSignName())
                    .setTemplateCode(aliyunResource.getTemplateCode())
                    // 这里把原来写死的code换成变量，用工具类生成一个随机6位字符串
                    .setTemplateParam("{\"code\":\"" + code + "\"}");
            SendSmsResponse resp = client.sendSms(sendSmsRequest);
            log.info(resp.getBody().getCode());
            log.info(resp.getBody().getMessage());
            //获得返回结果JSON串
            String res = String.valueOf(resp.getBody());
            //转成JSON对象
            JSONObject jsonObj = JSON.parseObject(res);
            log.info(String.valueOf(jsonObj));
            //返回发送成功与否的标记
            if ("OK".equals(jsonObj.get("Code"))) {
                return true;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }
}
