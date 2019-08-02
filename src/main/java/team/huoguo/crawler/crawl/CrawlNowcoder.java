package team.huoguo.crawler.crawl;

import com.geccocrawler.gecco.dynamic.DynamicGecco;

/**
 * @description 爬取牛客网
 * @author: GreenHatHG
 * @create: 2019-08-02 10:55
 **/

public class CrawlNowcoder extends Crawl {

    @Override
    public void crawl() {
        role = "body > div.nk-container.acm-container > div.nk-main.with-banner-page.clearfix.js-container > " +
                "div.platform-mod.js-current > div:nth-child(n) > div.platform-item-main > div.platform-item-cont";
        DynamicGecco.html()
                .gecco("https://ac.nowcoder.com/acm/contest/vip-index", "NowCoderService")
                .listField("list",
                        DynamicGecco.html()
                                .stringField("name").csspath("h4 > a[href^=/acm/]").text().build()
                                .stringField("startTime").csspath("ul > li.match-time-icon").text().build()
                                .stringField("link").csspath("h4 > a[href^=/acm/]").href().build()
                                .register()).csspath(role).build().register();
    }

}
