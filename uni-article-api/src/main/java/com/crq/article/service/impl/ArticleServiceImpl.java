package com.crq.article.service.impl;

import com.crq.article.mapper.ArticleMapper;
import com.crq.article.mapper.ArticleTagMapper;
import com.crq.article.mapper.UserMapper;
import com.crq.article.model.Article;
import com.crq.article.model.ArticleTag;
import com.crq.article.model.User;
import com.crq.article.model.dto.AddArticleDto;
import com.crq.article.model.vo.ArticleVo;
import com.crq.article.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author crq
 */
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private ArticleTagMapper articleTagMapper;

    @Override
    public PageInfo<Article> selectAllByPage(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Article> articles = articleMapper.selectAll();
        return PageInfo.of(articles);
    }

    @Override
    public ArticleVo getArticleById(Integer articleId) {
        Article article = articleMapper.getArticle(articleId);
        User user = userMapper.fineUserById(article.getUserId());
        return ArticleVo.builder().article(article).userName(user.getNickname()).userAvatar(user.getAvatar()).build();
    }

    @Override
    public Article addArticle(AddArticleDto addArticleDto) {
        Article article = new Article();
        BeanUtils.copyProperties(addArticleDto, article);
        article.setCreateTime(new Date());
        log.info(String.valueOf(article));
        articleMapper.addArticle(article);
        String ids = addArticleDto.getIds();
        List<ArticleTag> articleTags = new ArrayList<>(10);
        if (!Objects.equals(ids, "")) {
            String[] idsArray = ids.split(",");
            for (int i = 0; i < idsArray.length; i++) {
                ArticleTag articleTag = new ArticleTag();
                articleTag.setArticleId(article.getId());
                articleTag.setTagId(Integer.valueOf(idsArray[i]));
                articleTags.add(articleTag);
            }
            articleTagMapper.batchInsert(articleTags);
        }

        return article;
    }

}
