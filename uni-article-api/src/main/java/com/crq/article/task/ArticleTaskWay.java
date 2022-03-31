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
 * @description: 开源中国数据爬取
 * @author: crq
 * @create: 2022-03-29 10:46
 **/
@Component
@Slf4j
public class ArticleTaskWay implements Callable<List<Article>> {

    private static final String BASE_URL = "https://www.oschina.net/news/industry";
    private List<Article> articleList;

    @Override
    public List<Article> call() throws Exception {
        articleList = new ArrayList<>(100);
        Document document = null;
        //定义页码
//        int index;
//        for (index = 2; index < 5; index++) {
            document = Jsoup.connect(BASE_URL).get();
            Elements cards = document.getElementsByClass("news-item-hover");
            parseCards(cards);
//        }
        return articleList;
    }

    private void parseCards(Elements cards) {
        for (Element card : cards) {
            //文章id自增
            //标题
            Element titleNode = card.select(".title").get(0);
            String title = titleNode.html();
            System.out.println("标题："+title);
            //文章封面图
            String cover = null;
            if(card.select("img").size() != 0) {
                Element imgNode = card.select("img").get(0);
                cover = imgNode.attr("src");
                System.out.println("图片："+cover);
            }


            String url = card.attr("data-url");


            //文章分类
//            Element categoryNode = card.select(".post-category").get(0);
//            String category = categoryNode.html();
            List<String> list = new ArrayList(20);
            list.add("计算机知识");
            list.add("资讯");
            list.add("开源软件");
            list.add("博客");
            list.add("码云");
            list.add("众包");
            list.add("活动");
            list.add("开源访谈");
            list.add("周刊");
            list.add("源创会");
            Random random1 = new Random();
            String category = list.get(random1.nextInt(9) + 1);

            //文章链接
//            String url = BASE_URL + categoryNode.attr("href");
            //文章摘要
            Element summaryNode = card.select(".line-clamp").get(0);
            String summary = summaryNode.html();


            //随机生成用户id 1-3
            Random random = new Random();
            int userId = random.nextInt(3) + 1;

            //封装文章对象
            Article article = Article.builder()
                    .title(title)
                    .cover(cover)
                    .category(category)
                    .url(url)
                    .summary(summary)
                    .userId(userId)
//                    .createTime(new Date())
                    .build();
            articleList.add(article);
        }
    }

    public static void main(String[] args) throws Exception {
        ArticleTaskWay at = new ArticleTaskWay();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<List<Article>> future = executor.submit(at);
        List<Article> articles = future.get();
        articles.forEach(article -> System.out.println(article.getTitle() + ',' + article.getCover()));
    }
}
