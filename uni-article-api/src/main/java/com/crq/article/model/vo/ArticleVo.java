package com.crq.article.model.vo;

import com.crq.article.model.Article;
import lombok.Builder;
import lombok.Data;

/**
 * @description:
 * @author: crq
 * @create: 2022-03-29 15:46
 **/
@Data
@Builder
public class ArticleVo {
    private Article article;
    private String userName;
    private String userAvatar;
}
