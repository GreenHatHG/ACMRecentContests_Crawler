package team.huoguo.crawler.crawl;

import com.geccocrawler.gecco.dynamic.DynamicGecco;

public class CrawlJisuanke extends Crawl{

    @Override
    public void crawl() {
        role = ".jsk-panel-bd.jsk-padding-top-sm > table > tbody > tr:nth-child(n)";
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
