package team.huoguo.crawler.crawl;

/**
 * @description: 爬虫父类
 * @author: GreenHatHG
 * @create: 2019-08-02 10:46
 **/

public abstract class Crawl {

    //爬取规则
    public static String role = null;

    //爬取代码
    public abstract void crawl();

}
