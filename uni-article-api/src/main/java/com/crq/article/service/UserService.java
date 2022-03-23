package com.crq.article.service;

import com.crq.article.model.User;
import com.crq.article.model.dto.LoginDto;

/**
 * @description:
 * @author: crq
 * @create: 2022-03-22 16:46
 **/
public interface UserService {
    /**
     * 登录
     * @param loginDto loginDto
     * @return 登录结果
     */
    boolean loginByPassword(LoginDto loginDto);

    /**
     * 根据实际查找用户
     * @param phone phone
     * @return user
     */
    User getUser(String phone);

    /**
     * 验证码登录
     *
     * @param loginDto 登录dto对象
     * @return 登录结果
     */
    boolean loginByCode(LoginDto loginDto);
}
