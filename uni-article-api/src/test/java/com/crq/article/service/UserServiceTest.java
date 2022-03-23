package com.crq.article.service;

import com.crq.article.model.User;
import com.crq.article.model.dto.LoginDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Slf4j
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @Resource
    private UserService userService;

    @Test
    void login() {
        LoginDto loginDto = LoginDto
                .builder()
                .phone("18851699003")
                .password("123456")
                .build();
        boolean flag = userService.loginByPassword(loginDto);
        assertTrue(flag);
    }

    @Test
    void getUser() {
        User user = userService.getUser("18851699003");
        assertNotNull(user);
        log.info(String.valueOf(user));
    }

    @Test
    void test() {
        System.out.println(DigestUtils.md5Hex("123456"));
    }
}