package com.crq.article.service.impl;

import com.crq.article.mapper.ArticleMapper;
import com.crq.article.mapper.ArticleTagMapper;
import com.crq.article.model.Article;
import com.crq.article.model.ArticleTag;
import com.crq.article.model.vo.ArticleVo;
import com.crq.article.service.ArticleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author crq
 */
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ArticleTagMapper articleTagMapper;

    @Override
    public void insertArticles(List<Article> articles) {
        articleMapper.batchInsert(articles);
        assert articles != null;
        articles.forEach(article -> {
            if (article.getTagList() != null) {
                articleTagMapper.batchInsert(article.getTagList());
            }
        });
    }

    @Override
    public PageInfo<ArticleVo> selectByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<ArticleVo> articleVoPage = articleMapper.selectAll();
        return new PageInfo<>(articleVoPage);
    }

    @Override
    public ArticleVo getDetail(int id) {
        return articleMapper.getDetail(id);
    }

    @Override
    public Article postArticle(Article article) {
        article.setCover("https://picsum.photos/1920/1080?random&rand=" + Math.random());
        article.setCreateTime(LocalDateTime.now());
        articleMapper.insertArticle(article);
        List<ArticleTag> tagList = article.getTagList();
        tagList.forEach(tag -> tag.setArticleId(article.getId()));
        articleTagMapper.batchInsert(tagList);
        return article;
    }
}
