package team.huoguo.crawler.crawl;

import com.geccocrawler.gecco.dynamic.DynamicGecco;

/**
 * @desciption 爬取洛谷
 * @author Pistachio
 * @date 2019/8/4 下午3:34
 */
public class CrawlLuoGu extends Crawl{

    @Override
    public void crawl() {
       // role = "div.main-container > main > div > div > div > div:nth-child(1) > div:nth-child(2) > div > div.part.left-part> span.time > time:nth-child(n)";
        role = "div.main-container > main > div > div > div > div:nth-child(1) > div:nth-child(2) > div > div.part.left-part";
        //#app > div.main-container > main > div > div > div > div:nth-child(1) > div:nth-child(2) > div > div.part.left-part > a
        //#app > div.main-container > main > div > div > div > div:nth-child(1) > div:nth-child(2) > div > div.part.left-part > span.time > time:nth-child(1)
        //#app > div.main-container > main > div > div > div > div:nth-child(1) > div:nth-child(2) > div > div.part.left-part > span.time > time:nth-child(2)
        //#app > div.main-container > main > div > div > div > div:nth-child(1) > div:nth-child(2) > div > div.part.left-part > a

        DynamicGecco.html()
                .gecco("https://www.luogu.org/contest/list", "LuoGuService")
                .listField("list",
                        DynamicGecco.html()
                                .stringField("name").csspath("div.part.left-part > a[href^=/contest/]").text().build()
                                .stringField("startTime").csspath("span.time > time:nth-child(1)").text().build()
                                .stringField("endTime").csspath("span.time > time:nth-child(2)").text().build()
                                .stringField("link").csspath("div.part.left-part > a[href^=/contest/]").href().build()
                                .stringField("status").csspath("#app > div.main-container > main > div > div > div > div:nth-child(1) > div:nth-child(n) > div > div.part.left-part > span.status").href().build()
                                .register()).csspath(role).build().register();

    }
}
