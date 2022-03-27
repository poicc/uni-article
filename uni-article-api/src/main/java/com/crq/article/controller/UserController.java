package com.crq.article.controller;

import com.crq.article.common.ResponseResult;
import com.crq.article.common.ResultCode;
import com.crq.article.model.User;
import com.crq.article.model.dto.BindPhoneDto;
import com.crq.article.model.dto.LoginDto;
import com.crq.article.model.dto.WxLoginDto;
import com.crq.article.service.RedisService;
import com.crq.article.service.UserService;
import com.crq.article.util.FileResource;
import com.crq.article.util.SmsUtil;
import com.crq.article.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Resource
    private FileResource fileResource;

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

    @PostMapping(value = "/update")
    public User update(@RequestBody User user) {
        log.info("user:" + user);
        return userService.updateUser(user);
    }

    @PostMapping(value = "/login/wx")
    public ResponseResult wxLogin(@RequestBody WxLoginDto wxLoginDto) {
        log.info("wxLoginDto:" + wxLoginDto);
        User user = userService.wxLogin(wxLoginDto);
        if (user == null) {
            log.info("新用户");
            return ResponseResult.success(userService.findByOpenId(wxLoginDto.getWxOpenId()));
        }
        return ResponseResult.success(userService.findByOpenId(wxLoginDto.getWxOpenId()));
    }

    @PostMapping(value = "/upload")
    public ResponseResult uploadFile(MultipartFile file) {
        // 声明图片的地址路径，返回到前端
        String path = null;
        // 判断文件不能为空
        if (file != null) {
            // 获得文件上传的名称
            String fileName = file.getOriginalFilename();
            log.info(fileName);
            //调用上传服务，得到上传后的新文件名
            path = userService.uploadFile(file);
        }
        if (StringUtils.isNotBlank(path)) {
            //拼接上服务器地址前缀，得到最终返回给前端的url
            path = fileResource.getOssHost() + path;
            log.info(path);
        }
        return ResponseResult.success(path);
    }

    @PostMapping(value = "/bind")
    public ResponseResult bindPhone(@RequestBody BindPhoneDto bindPhoneDto) {
        log.info(String.valueOf(bindPhoneDto));
        User user = userService.bindPhone(bindPhoneDto);
        return ResponseResult.success(user);
    }
}
