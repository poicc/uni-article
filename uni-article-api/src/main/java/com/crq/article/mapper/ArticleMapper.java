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


}
