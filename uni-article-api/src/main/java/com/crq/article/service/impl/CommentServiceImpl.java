package com.crq.article.service.impl;

import com.crq.article.mapper.CommentMapper;
import com.crq.article.model.Comment;
import com.crq.article.model.vo.CommentVo;
import com.crq.article.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @description:
 * @author: crq
 * @create: 2022-03-31 11:02
 **/
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Override
    public List<CommentVo> selectByArticleId(int articleId) {

        return commentMapper.selectByArticleId(articleId);
    }

    @Override
    public List<CommentVo> addComment(Comment comment) {
        comment.setCreateTime(LocalDateTime.now());
        commentMapper.addComment(comment);
        return commentMapper.selectByArticleId(comment.getArticleId());
    }
}
