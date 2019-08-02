package team.huoguo.crawler;

import team.huoguo.crawler.common.CrawlConfig;
import team.huoguo.crawler.entity.Contest;

/**
 * @description: 启动类
 * @author: GreenHatHG
 * @create: 2019-07-16 11:29
 **/

public class Main {

    public static void main(String[] args) {
        CrawlConfig.crawl();
        for(Contest contest: CrawlConfig.items){
            System.out.println(contest);
        }
/*        MongoDBJDBC.connect("acm");
        CrawlConfig.saveToMongoDB();
        MongoDBJDBC.close();*/
    }

}
