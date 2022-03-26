package com.crq.article.mapper;

import com.crq.article.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Slf4j
class UserMapperTest {
    @Resource
    private UserMapper userMapper;

    @Test
    void insert() throws Exception {
        User user = User.builder()
                .phone("18851699003")
                .password("E10ADC3949BA59ABBE56E057F20F883E")
                .nickname("陈蓉琪")
                .avatar("https://cdn.jsdelivr.net/gh/1802343228/image@main/avatar.3sylmzwhoqi0.png")
                .gender(1)
                .birthday(LocalDate.now())
                .address("江苏南京")
                .createTime(new Date())
                .build();
        userMapper.insert(user);
    }

    @Test
    void findUserByPhone() throws  Exception {
        User user =  userMapper.findUserByPhone("18851699003");
        assertNotNull(user);
        log.info(String.valueOf(user));
    }

    @Test
    void updateUser() throws Exception {
        User user = userMapper.findUserByPhone("18851699003");
        user.setNickname("陶然然");
        userMapper.updateUser(user);
    }

}