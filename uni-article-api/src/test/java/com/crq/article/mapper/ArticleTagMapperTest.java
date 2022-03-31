package com.crq.article.mapper;

import com.crq.article.model.ArticleTag;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Slf4j
class ArticleTagMapperTest {
    @Resource
    private ArticleTagMapper articleTagMapper;

    @Test
    void batchInsert() {
        List<ArticleTag> articleList = new ArrayList<>();
        Random random = new Random();
        for (int i = 37; i <= 96; i++) {
            ArticleTag articleTag = new ArticleTag();
            articleTag.setArticleId(i);
            articleTag.setTagId(random.nextInt(10)+1);
            articleList.add(articleTag);
        }

//        int count = articleTagMapper.batchInsert(articleList);
    }
}