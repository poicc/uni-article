package com.crq.article.controller;

import com.crq.article.common.ResponseResult;
import com.crq.article.model.dto.AddArticleDto;
import com.crq.article.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @description:
 * @author: crq
 * @create: 2022-03-29 14:41
 **/
@RestController
@RequestMapping(value = "/api/v1/article")
@Slf4j
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @GetMapping(value = "/article")
    public ResponseResult getAll(@RequestParam("page") Integer page,@RequestParam("size")Integer size) {
        return ResponseResult.success(articleService.selectAllByPage(page,size));
    }

    @GetMapping(value = "id")
    public ResponseResult getArticleById(@RequestParam("id") Integer id) {
        return ResponseResult.success(articleService.getArticleById(id));
    }

    @PostMapping(value = "add")
    public ResponseResult addArticle(@RequestBody AddArticleDto addArticleDto) {
        return ResponseResult.success(articleService.addArticle(addArticleDto));
    }

}
