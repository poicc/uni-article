package com.crq.article.controller;

import com.crq.article.common.ResponseResult;
import com.crq.article.model.Comment;
import com.crq.article.model.vo.CommentVo;
import com.crq.article.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: crq
 * @create: 2022-03-31 11:09
 **/
@RestController
@RequestMapping(value = "/api/v1/comments")
@Slf4j
public class CommentController {
    @Resource
    private CommentService commentService;

    @GetMapping("{id}")
    public ResponseResult selectByArticleId(@PathVariable int id) {
        List<CommentVo> commentVoList = commentService.selectByArticleId(id);
        return ResponseResult.success(commentVoList);
    }

    @PostMapping("add")
    public ResponseResult addComment(@RequestBody Comment comment) {
        List<CommentVo> commentVoList = commentService.addComment(comment);
        return ResponseResult.success(commentVoList);
    }
}
