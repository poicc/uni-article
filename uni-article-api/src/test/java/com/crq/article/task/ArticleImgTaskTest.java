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
class ArticleImgTaskTest {


    @Resource
    private ArticleMapper articleMapper;

    @Test
    void getArticles() throws ExecutionException, InterruptedException {
//        ArticleImgTask articleDetailTask = new ArticleImgTask(articleMapper.selectAll());
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(4,8,5, TimeUnit.SECONDS,
//                new LinkedBlockingQueue<>(),
//                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy()
//        );
//
//        Future<List<Article>> future = executor.submit(articleDetailTask);
//        List<Article> articleList = future.get();
//        for (int i = 0; i < articleList.size(); i++) {
//            articleList.get(i).setId(38+i);
//            int count = articleMapper.batchUpdate(articleList);
//        }

//        System.out.println("受影响行数-------------------"+count);
    }

}