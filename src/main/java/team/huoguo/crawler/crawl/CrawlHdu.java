package team.huoguo.crawler.crawl;

import com.geccocrawler.gecco.dynamic.DynamicGecco;

/**
 * @author Pistachio
 * @date 2019/8/11 下午5:45
 */
public class CrawlHdu extends Crawl{
    @Override
    public void crawl() {
        role = "body > table > tbody > tr:nth-child(4) > td:nth-child(1) > table > tbody > tr:nth-child(n+2)";
        DynamicGecco.html()
                .gecco("http://acm.hdu.edu.cn/recentcontest/", "HduService")
                .listField("list",
                        DynamicGecco.html()
                                .stringField("oj").csspath("td:nth-child(2)").text().build()
                                //body > table > tbody > tr:nth-child(4) > td:nth-child(1) > table > tbody > tr:nth-child(2) > td:nth-child(2)
                                //body > table > tbody > tr:nth-child(4) > td:nth-child(1) > table > tbody > tr:nth-child(3) > td:nth-child(2)
                                //body > table > tbody > tr:nth-child(4) > td:nth-child(1) > table > tbody > tr:nth-child(4) > td:nth-child(2)
                                .stringField("name").csspath("td:nth-child(3) > a").text().build()

//                                .stringField("startTime").csspath(" td:nth-child(4)").text().build()
//                                .stringField("length").csspath("td.jsk-hide-md-down").text().build()
                                .stringField("link").csspath("td:nth-child(3) > a").href().build()
//                                .stringField("status").csspath("td:nth-child(7)").text().build()
                                .register()).csspath(role).build().register();
    }

}
