package com.crq.article.task;

import com.crq.article.model.Article;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @description: 开源中国数据爬取
 * @author: crq
 * @create: 2022-03-29 10:46
 **/
@Component
@Slf4j
@NoArgsConstructor
@Data
public class ArticleImgTask implements Callable<List<Article>> {


    private static final String BASE_URL = "https://www.hippopx.com/zh";
    private List<Article> list;

    public ArticleImgTask(List<Article> list) {
        this.list = list;
    }


    @Override
    public List<Article> call() throws Exception {
        List<Article> articleList = new ArrayList<>(100);
        List<Article> returnList = new ArrayList<>(100);
        Document document = null;
        document = Jsoup.connect(BASE_URL).get();
        Elements cards = document.getElementsByClass("container");
        System.out.println("-------------");
        System.out.println(cards);
        articleList = parseCards(cards);
        returnList.addAll(articleList);
        return returnList;
    }

    private List<Article> parseCards(Elements cards) {
        List<Article> articleList = new ArrayList<>(100);
        for (Element card : cards) {

            Element contentNode = card.select("img").get(0);
            String src = contentNode.attr("src");
            System.out.println(src);


            Article article = Article.builder()
                    .cover(src)
                    .build();
            articleList.add(article);
        }
        return articleList;
    }

    public static void main(String[] args) throws Exception {
        ArticleImgTask at = new ArticleImgTask();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<List<Article>> future = executor.submit(at);
        List<Article> articles = future.get();
        articles.forEach(article -> System.out.println(article.getTitle() + ',' + article.getCover()));
    }
}
