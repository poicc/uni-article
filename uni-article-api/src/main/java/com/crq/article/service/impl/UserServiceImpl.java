package com.crq.article.service.impl;

import com.crq.article.mapper.UserMapper;
import com.crq.article.model.User;
import com.crq.article.model.dto.LoginDto;
import com.crq.article.service.UserService;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;

import javax.annotation.Resource;

/**
 * @description:
 * @author: crq
 * @create: 2022-03-22 16:47
 **/
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public boolean login(LoginDto loginDto) {
        User user = getUser(loginDto.getPhone());
        if(user != null) {
            return DigestUtils.md5Hex(loginDto.getPassword()).equals(user.getPassword());
        }
        return false;
    }

    @Override
    public User getUser(String phone) {
        return userMapper.findUserByPhone(phone);
    }
}
