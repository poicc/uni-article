package com.crq.article.model.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @description:
 * @author: crq
 * @create: 2022-03-29 15:40
 **/
@Data
@Builder
public class AddArticleDto {

    private String category;

    private Integer userId;

    private String title;

    private String cover;
    private String content;

    private String url;
    private String pageView;
    private String summary;
    private String ids;
}
