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
public class ArticleDetailTask implements Callable<List<Article>> {


    //    private static final String BASE_URL = "https://www.oschina.net/news/industry";
    private List<Article> list;

    public ArticleDetailTask(List<Article> list) {
        this.list = list;
    }


    @Override
    public List<Article> call() throws Exception {
        List<Article> articleList = new ArrayList<>(100);
        List<Article> returnList = new ArrayList<>(100);
        Document document = null;
        String url = null;
        for (int i = 0; i < list.size() - 1; i++) {
            url = list.get(i).getUrl();
            document = Jsoup.connect(url).get();
            Elements cards = document.getElementsByClass("article-box");
            articleList = parseCards(cards, list.get(i).getId());
            returnList.addAll(articleList);
        }
        return returnList;
    }

    private List<Article> parseCards(Elements cards, Integer id) {
        List<Article> articleList = new ArrayList<>(100);
        for (Element card : cards) {
            //文章id自增
            //阅读量
            String read = "";
            if(card.select(".lm").size() != 0) {
                Element titleNode = card.select(".lm").get(1);
                read = titleNode.html();
            }

            //文章封面图


            Element contentNode = card.select(".content").get(0);


            //封装文章对象
            Article article = Article.builder()
                    .id(id)
//                    .pageView(read)
                    .content(String.valueOf(contentNode))
                    .build();
            articleList.add(article);
        }
        return articleList;
    }

    public static void main(String[] args) throws Exception {
        ArticleDetailTask at = new ArticleDetailTask();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<List<Article>> future = executor.submit(at);
        List<Article> articles = future.get();
        articles.forEach(article -> System.out.println(article.getTitle() + ',' + article.getCover()));
    }
}
