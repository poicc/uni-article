package com.crq.article.service;

import com.crq.article.model.Article;
import com.crq.article.model.dto.AddArticleDto;
import com.crq.article.model.vo.ArticleVo;
import com.github.pagehelper.PageInfo;

/**
*
 * @author crq
 */
public interface ArticleService {
    /**
     * 分页查询
     * @param page 页码
     * @param size 大小
     * @return list
     */
    PageInfo<Article> selectAllByPage(Integer page, Integer size);

    /**
     * 根据Id查询文章详情
     * @param articleId articleId
     * @return article
     */
    ArticleVo getArticleById(Integer articleId);

    /**
     * 添加
     * @param addArticleDto addArticleDto
     * @return int
     */
    Article addArticle(AddArticleDto addArticleDto);

}
