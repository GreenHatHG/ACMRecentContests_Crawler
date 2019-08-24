package team.huoguo.crawler.common;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.request.HttpGetRequest;
import team.huoguo.crawler.crawl.*;
import team.huoguo.crawler.dao.ContestDao;
import team.huoguo.crawler.dao.impl.ContestDaoImpl;
import team.huoguo.crawler.entity.Contest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 爬取oj信息
 * @author: GreenHatHG
 * @create: 2019-07-16 21:55
 **/
public class CrawlConfig {

    /**
     * 爬虫得到的所有条目
     */
    public static List <Contest> items = new ArrayList<Contest>();

    private static ContestDao contestDao = new ContestDaoImpl();

    /**
     * 配置爬虫
     */

    public static void crawl() throws IOException {

        new CrawlCodeChef().crawl();
        new CrawlCodeForces().crawl();
        new CrawlJisuanke().crawl();
        new CrawlNowcoder().crawl();
        new CrawlAtcoder().crawl();
        new CrawlHdu().crawl();
        new CrawlLuoGu().crawl();
        new CrawlBestcoder().crawl();

        List urls = new ArrayList();
        urls.add(new HttpGetRequest("https://www.codechef.com/contests"));
        urls.add(new HttpGetRequest("https://ac.nowcoder.com/acm/contest/vip-index"));
        urls.add(new HttpGetRequest("http://codeforces.com/contests"));
        urls.add(new HttpGetRequest("https://nanti.jisuanke.com/contest"));
        urls.add(new HttpGetRequest("https://atcoder.jp/contests/"));
        urls.add(new HttpGetRequest("http://acm.hdu.edu.cn/recentcontest/"));
        urls.add(new HttpGetRequest("https://www.luogu.org/contest/list"));
        HttpGetRequest bestCoder = new HttpGetRequest("http://bestcoder.hdu.edu.cn/contests/contest_list.php");
        bestCoder.setCharset("gb2312");
        bestCoder.addHeader("user-agent","Mozilla/5.0");
        urls.add(bestCoder);
        HttpGetRequest luogu= new HttpGetRequest("https://www.luogu.org/contest/list");
        luogu.addHeader("user-agent","Mozilla/5.0");  //加ua
//        urls.add(luogu);
        GeccoEngine.create()
                .classpath("team.huoguo.crawler.service")
                .start(urls)
  //              .debug(true)
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
