package com.crq.article.task;

import com.crq.article.mapper.ArticleMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
class ArticleDetailTaskTest {
//    @Resource
//    private ArticleDetailTask articleDetailTask;

    @Resource
    private ArticleMapper articleMapper;

    @Test
    void getArticles() throws ExecutionException, InterruptedException {
////        ArticleDetailTask articleDetailTask = new ArticleDetailTask(articleMapper.selectAll());
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(4,8,5, TimeUnit.SECONDS,
//                new LinkedBlockingQueue<>(),
//                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy()
//        );
////        articleDetailTask.setList(articleMapper.selectAll());
//        Future<List<Article>> future = executor.submit(articleDetailTask);
//        List<Article> articleList = future.get();
//        System.out.println("-------------------------------");
//        System.out.println(articleList);
//        int count = articleMapper.batchUpdate(articleList);
//        System.out.println("受影响行数-------------------"+count);
    }

}