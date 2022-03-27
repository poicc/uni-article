package com.crq.article.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: crq
 * @create: 2022-03-26 22:28
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BindPhoneDto {
    private String phone;
    private String code;
    private String wxOpenId;
}
