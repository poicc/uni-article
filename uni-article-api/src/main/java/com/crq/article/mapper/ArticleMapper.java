package com.crq.article.mapper;

import com.crq.article.model.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author crq
 * @Entity com.crq.article.domain.Article
*/
@Mapper
public interface ArticleMapper {
    /**
     * 批量插入
     * @param article article
     * @return 受影响行数
     */
    int batchInsert(@Param("articles")List<Article> article);

    /**
     * 查所有
     * @return list
     */
    List<Article> selectAll();


    /**
     * 批量修改
     * @param articles articles
     * @return int
     */
    int batchUpdate(@Param("articles") List<Article> articles);



    /**
     * 查询课程及选修该课程的所有学生
     * @param articleId articleId
     * @return article
     */
    Article getArticle(int articleId);

    /**
     * 添加article
     * @param article article
     * @return int
     */
    int addArticle(Article article);


}
