package com.crq.article.task;

import com.crq.article.model.Article;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @description: 文章爬虫类
 * @author: crq
 * @create: 2022-03-28 15:59
 **/
@Component
@Slf4j
public class ArticleTask implements Callable<List<Article>> {

    private static final String BASE_URL = "https://godweiyang.com";
    private List<Article> articleList;

    @Override
    public List<Article> call() throws Exception {
        articleList = new ArrayList<>(100);
        Document document = null;
        //定义页码
        int index;
        for (index = 2; index < 5; index++) {
            document = Jsoup.connect(BASE_URL + "/page/" + index).get();
            Elements cards = document.getElementsByClass("card");
            parseCards(cards);
        }
        return articleList;
    }

    private void parseCards(Elements cards) {
        for (Element card : cards) {
            //文章id自增
            //标题
            Element titleNode = card.select(".card-title").get(0);
            String title = titleNode.text();
            //文章封面图
            Element imgNode = card.select(".responsive-img").get(0);
            String cover = BASE_URL + imgNode.attr("src");

            //文章分类
            Element categoryNode = card.select(".post-category").get(0);
            String category = categoryNode.html();
            //文章链接
            String url = BASE_URL + categoryNode.attr("href");
            //文章摘要
            Element summaryNode = card.select(".summary").get(0);
            String summary = summaryNode.html();


            //随机生成用户id 1-3
            Random random = new Random();
            int userId = random.nextInt(3)+1;

            //封装文章对象
            Article article = Article.builder()
                    .title(title)
                    .cover(cover)
                    .category(category)
                    .url(url)
                    .summary(summary)
                    .userId(userId)
                    .build();
            articleList.add(article);
        }
    }

    public static void main(String[] args) throws Exception {
        ArticleTask at = new ArticleTask();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<List<Article>> future = executor.submit(at);
        List<Article> articles = future.get();
        articles.forEach(article -> System.out.println(article.getTitle() + ',' + article.getCover()));
    }
}
