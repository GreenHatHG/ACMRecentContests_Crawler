package team.huoguo.crawler;

import team.huoguo.crawler.common.Crawl;
import team.huoguo.crawler.common.MongoDBJDBC;
import team.huoguo.crawler.entity.Contest;


/**
 * @description: 启动类
 * @author: GreenHatHG
 * @create: 2019-07-16 11:29
 **/

public class Main {

    public static void main(String[] args) {
        Crawl.crawl();
        System.out.println("----------获取到的数据------------------------");
        System.out.println("----------获取到的数据------------------------");
        for(Contest contest : Crawl.items){
            System.out.println(contest);
        }
        System.out.println("----------获取到的数据------------------------");
        System.out.println("----------获取到的数据------------------------");
        MongoDBJDBC.connect("acm");
        Crawl.saveToMongoDB();
        MongoDBJDBC.close();
    }

}
