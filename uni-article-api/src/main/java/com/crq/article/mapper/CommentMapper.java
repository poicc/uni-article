package com.crq.article.mapper;

import com.crq.article.model.Comment;
import com.crq.article.model.vo.CommentVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author crq
 * @Entity com.crq.article.model.Comment
 */
@Mapper
public interface CommentMapper {
    /**
     * 根据文章id查询评论
     *
     * @param articleId articleId
     * @return list
     */
    @Select("SELECT a.*,b.nickname,b.avatar FROM t_comment a LEFT JOIN t_user b ON a.user_id = b.id WHERE a.article_id=#{articleId} ORDER BY a.id DESC ")
    List<CommentVo> selectByArticleId(@Param("articleId") int articleId);

    /**
     * 添加评论
     *
     * @param comment comment
     */
    @Insert("INSERT INTO t_comment (article_id,user_id,content,create_time)" +
            "VALUES (#{comment.articleId},#{comment.userId},#{comment.content},#{comment.createTime})")
    void addComment(@Param("comment") Comment comment);

}




