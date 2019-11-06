package team.huoguo.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import team.huoguo.crawler.common.CrawlConfig;
import team.huoguo.crawler.common.MongoDBJDBC;
import team.huoguo.crawler.entity.Contest;

import java.io.IOException;

/**
 * @description: 启动类
 * @author: GreenHatHG
 * @create: 2019-07-16 11:29
 **/

public class Main {

    public static void main(String[] args) throws Exception {

        CrawlConfig.crawl();
        System.out.println("开始连接数据库");
        MongoDBJDBC.connect("acm");
        CrawlConfig.saveToMongoDB();
        MongoDBJDBC.close();
        System.out.println("连接完成");

    }

}
