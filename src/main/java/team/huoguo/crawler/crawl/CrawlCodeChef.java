package team.huoguo.crawler.crawl;

import com.geccocrawler.gecco.dynamic.DynamicGecco;

/**
 * @description 爬取Codechef
 * @author: GreenHatHG
 * @create: 2019-08-02 10:46
 **/

public class CrawlCodeChef extends Crawl {

    @Override
    public void crawl() {
        role = "#primary-content > div > div:nth-child(19) > table > tbody > tr:nth-child(n)";
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
                                .register()).csspath(role).build().register();
    }

}
