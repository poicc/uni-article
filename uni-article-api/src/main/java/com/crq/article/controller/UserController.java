package com.crq.article.controller;

import com.crq.article.common.ResponseResult;
import com.crq.article.common.ResultCode;
import com.crq.article.model.dto.LoginDto;
import com.crq.article.service.RedisService;
import com.crq.article.service.UserService;
import com.crq.article.util.SmsUtil;
import com.crq.article.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @description:
 * @author: crq
 * @create: 2022-03-22 16:56
 **/
@RestController
@RequestMapping(value = "/api/v1/users")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private SmsUtil smsUtil;

    @Resource
    private RedisService redisService;

    @PostMapping(value = "/login/pass")
    public ResponseResult loginByPassword(@RequestBody LoginDto loginDto) {
        log.info("loginDto:" + loginDto);
        boolean flag = userService.loginByPassword(loginDto);
        if (flag) {
            return ResponseResult.success(userService.getUser(loginDto.getPhone()));
        } else {
            return ResponseResult.failure(ResultCode.USER_SIGN_IN_FAIL);
        }
    }

    @PostMapping(value = "/sms")
    public ResponseResult sendSms(@RequestParam String phone) {
        //随机验证码
        String code = StringUtil.getVerifyCode();
        //给入参手机号发送短信
        boolean flag = smsUtil.sendSms(phone, code);
        log.info(String.valueOf(flag));
        redisService.set(phone, code, 1L);
        log.info(code);
        return ResponseResult.success(code);
        //if (flag) {
        //    redisService.set(phone, code, 1L);
        //    // 结果返回给客户端
        //    return ResponseResult.success(code);
        //} else {
        //    //redisService.set(phone, code, 1L);
        //    return ResponseResult.failure(ResultCode.SMS_ERROR);
        //}
    }

    @PostMapping(value = "/login/sms")
    public ResponseResult loginByCode(@RequestBody LoginDto loginDto) {
        log.info("loginDto:" + loginDto);
        boolean flag = userService.loginByCode(loginDto);
        if (flag) {
            return ResponseResult.success(userService.getUser(loginDto.getPhone()));
        } else {
            return ResponseResult.failure(ResultCode.USER_SIGN_IN_FAIL);
        }
    }
}
