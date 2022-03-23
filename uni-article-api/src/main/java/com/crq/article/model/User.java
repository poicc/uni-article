package com.crq.article.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

/**
 * @description:
 * @author: crq
 * @create: 2022-03-22 16:16
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Integer id;
    private String wxOpenId;
    private String phone;
    private String password;
    private String nickname;
    private String avatar;
    private Integer gender;
    private String bg;
    private LocalDate birthday;
    private String address;
    private Date createTime;
}
