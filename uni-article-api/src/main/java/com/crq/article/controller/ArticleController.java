package com.crq.article.controller;

import com.crq.article.common.ResponseResult;
import com.crq.article.model.Article;
import com.crq.article.model.vo.ArticleVo;
import com.crq.article.service.ArticleService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @description:
 * @author: crq
 * @create: 2022-03-29 14:41
 **/
@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @PostMapping("page")
    public ResponseResult getArticleByPage(@RequestParam(name = "pageNum", defaultValue = "1", required = false) int pageNum,
                                          @RequestParam(name = "pageSize", defaultValue = "6", required = false) int pageSize) {
        PageInfo<ArticleVo> articleVoPageInfo = articleService.selectByPage(pageNum, pageSize);
        return ResponseResult.success(articleVoPageInfo);
    }

    @GetMapping("{id}")
    public ResponseResult getArticleDetail(@PathVariable int id) {
        ArticleVo detail = articleService.getDetail(id);
        return ResponseResult.success(detail);
    }

    @PostMapping("post")
    public ResponseResult postArticle(@RequestBody Article article) {
        return ResponseResult.success(articleService.postArticle(article));
    }


}