package com.crq.article.mapper;

import com.crq.article.model.ArticleTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author crq
 * @Entity com.crq.article.model.ArticleTag
 */
@Mapper
public interface ArticleTagMapper {
    /**
     * 批量插入
     * @param article article
     * @return 受影响行数
     */
    int batchInsert(@Param("articles") List<ArticleTag> article);


//    /**
//     * 新增关联
//     * @param articleId articleId
//     * @param tagId tagId
//     */
//    @Insert("INSERT INTO t_article_tag (article_id,tag_id) " + "VALUES (#{articleId},#{tagId}) ")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
//    void insert(Integer articleId,Integer tagId);


}




