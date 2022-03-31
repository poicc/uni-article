package com.crq.article.service;

import com.crq.article.model.Comment;
import com.crq.article.model.vo.CommentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: crq
 * @create: 2022-03-31 11:01
 **/
public interface CommentService {
    /**
     * 根据文章id查询评论
     * @param articleId
     * @return
     */
    List<CommentVo> selectByArticleId(int articleId);

    /**
     * 新增评论
     * @param comment
     * @return
     */
    List<CommentVo> addComment(@Param("comment") Comment comment);
}
