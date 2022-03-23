package com.crq.article.util;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Slf4j
class SmsUtilTest {
    @Resource
    private SmsUtil sms;

    @Test
    void sendSms() {
        sms.sendSms("18851699003", StringUtil.getVerifyCode());
    }
}