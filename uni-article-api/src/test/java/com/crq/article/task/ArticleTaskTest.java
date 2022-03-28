package com.crq.article.task;

import com.crq.article.mapper.ArticleMapper;
import com.crq.article.model.Article;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

import java.util.List;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
class ArticleTaskTest {
    @Resource
    private ArticleTask articleTask;
    @Resource
    private ArticleMapper articleMapper;

    @Test
    void getArticles() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4,8,5, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        Future<List<Article>> future = executor.submit(articleTask);
        List<Article> articleList = future.get();
        int count = articleMapper.batchInsert(articleList);
        System.out.println("受影响行数-------------------"+count);
    }

}