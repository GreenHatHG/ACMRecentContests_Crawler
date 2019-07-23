package team.huoguo.crawler.common;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.dynamic.DynamicGecco;
import com.geccocrawler.gecco.request.HttpGetRequest;
import team.huoguo.crawler.dao.ContestDao;
import team.huoguo.crawler.dao.impl.ContestDaoImpl;
import team.huoguo.crawler.entity.Contest;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 爬取oj信息
 * @author: GreenHatHG
 * @create: 2019-07-16 21:55
 **/
public class Crawl {

    /**
     * 爬虫得到的所有条目
     */
    public static List <Contest> items = new ArrayList<Contest>();

    private static ContestDao contestDao = new ContestDaoImpl();

    /**
     * 爬虫主代码
     */
    public static void crawl(){
        String codechefRole = "#primary-content > div > div:nth-child(19) > table > tbody > tr:nth-child(n)";
        String nowcoderRole = "body > div.nk-container.acm-container > div.nk-main.with-banner-page.clearfix.js-container > " +
                "div.platform-mod.js-current > div:nth-child(n) > div.platform-item-main > div.platform-item-cont";
        String codeforcesRole = "#pageContent > div > div.datatable > div:nth-child(6) > table > tbody  > tr:nth-child(n+2)";

        //爬取CodeChef
        DynamicGecco.html()
                .gecco("https://www.codechef.com/contests", "CodeChefService")
                .listField("list",
                        DynamicGecco.html()
                            .stringField("name").csspath("td:nth-child(2) > a").text().build()
                            .stringField("startTime").csspath("td.start_date").text().build()
                            .stringField("startTimeHms").csspath("td.start_date > br").text().build()
                            .stringField("endTime").csspath("td.end_date").text().build()
                            .stringField("endTimeHms").csspath("td.end_date > br").text().build()
                            .stringField("link").csspath("td:nth-child(2) > a").href().build()
                            .register()).csspath(codechefRole).build().register();

        //爬取NowCoder
        DynamicGecco.html()
                .gecco("https://ac.nowcoder.com/acm/contest/vip-index", "NowCoderService")
                .listField("list",
                        DynamicGecco.html()
                            .stringField("name").csspath("h4 > a[href^=/acm/]").text().build()
                            .stringField("startTime").csspath("ul > li.match-time-icon").text().build()
                            .stringField("link").csspath("h4 > a[href^=/acm/]").href().build()
                            .register()).csspath(nowcoderRole).build().register();

        //爬取CodeForces
        DynamicGecco.html()
                .gecco("http://codeforces.com/contests", "CodeForcesService")
                .listField("list",
                        DynamicGecco.html()
                            .stringField("name").csspath("td:nth-child(1)").text().build()
                            .stringField("startTime").csspath("td:nth-child(3) > a > span").text().build()
                            .stringField("length").csspath("td:nth-child(4)").text().build()
                            .stringField("link").csspath("td:nth-child(6) > a").href().build()
                            .register()).csspath(codeforcesRole).build().register();

        List urls = new ArrayList();
        urls.add(new HttpGetRequest("https://www.codechef.com/contests"));
        urls.add(new HttpGetRequest("https://ac.nowcoder.com/acm/contest/vip-index"));
        urls.add(new HttpGetRequest("http://codeforces.com/contests"));

        GeccoEngine.create()
                .classpath("team.huoguo.crawler.service")
                .start(urls)
                .mobile(false)
                .run();
    }

    /**
     * 将items保存到MongoDB
     */
    public static void saveToMongoDB(){
        try {
            contestDao.insertAll(items);
        }catch (Exception e){
            System.out.println("存在重复数据,默认只插入重复数据中的一条");
        }
    }
}
