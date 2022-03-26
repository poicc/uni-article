package com.crq.article.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: crq
 * @create: 2022-03-25 23:26
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WxLoginDto {
    private String wxOpenId;
    private String nickname;
    private Integer gender;
    private String avatar;
}


