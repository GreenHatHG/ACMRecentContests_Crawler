package team.huoguo.crawler.crawl;

import com.geccocrawler.gecco.dynamic.DynamicGecco;

/**
 * @description 爬取Atcoder
 * @author: GreenHatHG
 * @create: 2019-08-02 15:29
 **/

public class CrawlAtcoder extends Crawl {

    @Override
    public void crawl() {
        role = "#main-container > div.row > div.col-lg-9.col-md-8 > div:nth-child(5) > div > table > tbody > tr:nth-child(n)";
        DynamicGecco.html()
                .gecco("https://atcoder.jp/contests/", "AtcoderService")
                .listField("list",
                        DynamicGecco.html()
                                .stringField("name").csspath("td:nth-child(2) > a").text().build()
                                .stringField("startTime").csspath("td:nth-child(1) > a > time").text().build()
                                .stringField("length").csspath("td:nth-child(3)").text().build()
                                .stringField("link").csspath("td:nth-child(2) > a").href().build()
                                .register()).csspath(role).build().register();
    }
}
