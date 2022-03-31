package com.crq.article.mapper;

import com.crq.article.model.ArticleTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author crq
 * @Entity com.crq.article.model.ArticleTag
 */
@Mapper
public interface ArticleTagMapper {
    /**
     * 批量插入
     *
     * @param articleTagList
     */
    void batchInsert(@Param(value = "articleTagList") List<ArticleTag> articleTagList);

    /**
     * 查询指定文章的所有标签
     *
     * @param articleId
     * @return
     */
    @Select("SELECT * FROM t_article_tag WHERE article_id = #{articleId}")
    List<ArticleTag> selectByArticleId(@Param(value = "articleId") String articleId);


}




