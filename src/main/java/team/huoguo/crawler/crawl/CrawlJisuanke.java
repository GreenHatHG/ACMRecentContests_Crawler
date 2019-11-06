package team.huoguo.crawler.crawl;

import com.geccocrawler.gecco.dynamic.DynamicGecco;

/**
 * @description 爬取计蒜客
 * @author: GreenHatHG
 * @create: 2019-08-02 10:52
 **/

public class CrawlJisuanke extends Crawl {

    @Override
    public void crawl() {
        role = ".jsk-panel-bd.jsk-padding-top-sm > table > tbody > tr:nth-child(n)";
        //role="#app > div.jsk-panel.jsk-margin-top-0 > div.jsk-panel-bd.jsk-padding-top-sm"
        //name = "#app > div.jsk-panel.jsk-margin-top-0 > div.jsk-panel-bd.jsk-padding-top-sm > table > tbody > tr:nth-child(1) > td:nth-child(2) > a"
        //startTime = "#app > div.jsk-panel.jsk-margin-top-0 > div.jsk-panel-bd.jsk-padding-top-sm > table > tbody > tr:nth-child(1) > td:nth-child(4)"
        //length =" #app > div.jsk-panel.jsk-margin-top-0 > div.jsk-panel-bd.jsk-padding-top-sm > table > tbody > tr:nth-child(1) > td.jsk-hide-md-down"
        //status = "#app > div.jsk-panel.jsk-margin-top-0 > div.jsk-panel-bd.jsk-padding-top-sm > table > tbody > tr:nth-child(1) > td:nth-child(7)"
        DynamicGecco.html()
                .gecco("https://nanti.jisuanke.com/contest", "JisuankeService")
                .listField("list",
                        DynamicGecco.html()
                                .stringField("name").csspath("td:nth-child(2) > a[title]").text().build()
                                .stringField("startTime").csspath(" td:nth-child(4)").text().build()
                                .stringField("length").csspath("td.jsk-hide-md-down").text().build()
                                .stringField("link").csspath("td:nth-child(2) > a").href().build()
                                .stringField("status").csspath("td:nth-child(7)").text().build()
                                .register()).csspath(role).build().register();
    }

}
