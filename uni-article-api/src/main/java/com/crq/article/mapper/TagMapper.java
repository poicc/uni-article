package com.crq.article.mapper;

import com.crq.article.model.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author crq
 * @Entity com.crq.article.model.Tag
 */
@Mapper
public interface TagMapper {
    /**
     * 批量插入tag
     *
     * @param tagList tagList
     * @return int
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int batchInsert(@Param(value = "tagList") List<Tag> tagList);

    /**
     * 查询所有标签
     *
     * @return list
     */
    @Select("SELECT  * FROM t_tag")
    List<Tag> selectAll();

    /**
     * 根据文章id查询所有标签
     *
     * @param articleId articleId
     * @return list
     */
    @Select("SELECT  a.id,a.tag_name FROM t_tag a LEFT JOIN t_article_tag b ON a.id = b.tag_id LEFT JOIN" +
            " ON b.article_id = c.id WHERE article_id =#{articleId}")
    List<Tag> selectByArticleId(@Param("articleId") int articleId);


}




