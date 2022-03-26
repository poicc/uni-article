package com.crq.article.service;

import com.crq.article.model.User;
import com.crq.article.model.dto.LoginDto;
import com.crq.article.model.dto.WxLoginDto;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 修改用户信息
     *
     * @param user 用户对象
     * @return User
     */
    User updateUser(User user);


    /**
     * 微信登录
     *
     * @param wxLoginDto 入参
     * @return boolean
     */
    User wxLogin(WxLoginDto wxLoginDto);

    /**
     * 根据微信openId查询用户
     *
     * @param wxOpenId 微信openId
     * @return 用户
     */
    User findByOpenId(String wxOpenId);

    /**
     * 上传文件到OSS
     *
     * @param file 文件对象
     * @return 上传后的url
     */
    String uploadFile(MultipartFile file);
}
