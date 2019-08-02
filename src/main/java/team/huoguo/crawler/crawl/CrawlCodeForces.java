package team.huoguo.crawler.crawl;

import com.geccocrawler.gecco.dynamic.DynamicGecco;

/**
 * @description 爬取codeforces
 * @author: GreenHatHG
 * @create: 2019-08-02 10:50
 **/
public class CrawlCodeForces extends Crawl{

    @Override
    public void crawl() {
        role = "#pageContent > div > div.datatable > div:nth-child(6) > table > tbody  > tr:nth-child(n+2)";
        DynamicGecco.html()
                .gecco("http://codeforces.com/contests", "CodeForcesService")
                .listField("list",
                        DynamicGecco.html()
                                .stringField("name").csspath("td:nth-child(1)").text().build()
                                .stringField("startTime").csspath("td:nth-child(3) > a > span").text().build()
                                .stringField("length").csspath("td:nth-child(4)").text().build()
                                .stringField("link").csspath("td:nth-child(6) > a").href().build()
                                .register()).csspath(role).build().register();
    }
}
