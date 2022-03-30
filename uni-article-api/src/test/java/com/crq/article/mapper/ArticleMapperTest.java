package com.crq.article.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Slf4j
class ArticleMapperTest {
    @Resource
    private ArticleMapper articleMapper;

    @Test
    void selectAll() {

        log.info(String.valueOf(articleMapper.selectAll()));
    }
}