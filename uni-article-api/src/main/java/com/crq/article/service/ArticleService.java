package com.crq.article.service;

import com.crq.article.model.Article;
import com.crq.article.model.vo.ArticleVo;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*
 * @author crq
 */
public interface ArticleService {
    /**
     * 批量新增
     *
     * @param articles articles
     */
    void insertArticles(List<Article> articles);

    /**
     * 查询文章数据并分页
     *
     * @param pageNum pageNum
     * @param pageSize pageSize
     * @return page
     */
    PageInfo<ArticleVo> selectByPage(int pageNum, int pageSize);

    /**
     * 根据文章id查找文章详情
     *
     * @param id id
     * @return  ArticleVo
     */
    ArticleVo getDetail(@Param(value = "id") int id);

    /**
     * 发布文章
     *
     * @param article article
     * @return article
     */
    Article postArticle(Article article);

}
