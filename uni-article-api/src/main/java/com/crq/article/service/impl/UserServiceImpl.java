package com.crq.article.service.impl;

import com.crq.article.mapper.UserMapper;
import com.crq.article.model.User;
import com.crq.article.model.dto.LoginDto;
import com.crq.article.service.RedisService;
import com.crq.article.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @description:
 * @author: crq
 * @create: 2022-03-22 16:47
 **/
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisService redisService;

    @Override
    public boolean loginByPassword(LoginDto loginDto) {
        User user = getUser((loginDto.getPhone()));
        if (user != null) {
            // 对客户端传递的密码进行加密后，和数据库中的密文比对
            return DigestUtils.md5Hex(loginDto.getPassword()).equals(user.getPassword());
        }
        return false;
    }

    @Override
    public User getUser(String phone) {
        return userMapper.findUserByPhone(phone);
    }

    @Override
    public boolean loginByCode(LoginDto loginDto) {
        //检查redis 中是否存在这个手机号
        boolean flag = redisService.existsKey(loginDto.getPhone());
        if (flag) {
            // 取出redis中之前存储的验证码
            String saveCode = redisService.getValue(loginDto.getPhone(), String.class);
            // 和前端传的验证码比对
            if (saveCode.equalsIgnoreCase(loginDto.getCode())) {
                // 查找数据库该手机号用户是否存在
                User user = getUser(loginDto.getPhone());
                // 不存在就注册为新用户
                if (user == null) {
                    //不存在该手机号,就构建新用户记录，补充必备字段写入数据库，一键注册并登录（密码留空，用户可后期修改）
                    User saveUser = User.builder()
                            .phone(loginDto.getPhone())
                            .nickname("新用户")
                            .avatar("/static/img/nologin.jpeg")
                            .bg("https://cdn.jsdelivr.net/gh/poicc/image@main/bgh.at8sse7wbww.webp")
                            .createTime(new Date())
                            .build();
                    userMapper.insert(saveUser);
                }
                //存在就登录成功
                return true;
            }
        }
        return false;
    }
}
