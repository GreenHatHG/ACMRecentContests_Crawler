package team.huoguo.crawler.crawl;

import com.geccocrawler.gecco.dynamic.DynamicGecco;

/**
 * @author Pistachio
 * @date 2019/8/21 下午10:36
 */

public class CrawlBestcoder extends Crawl {
    @Override
    public void crawl() {
        role = ".hidden-xs > table > tbody > tr:nth-child(n)";  //此处有简写
        //body > div:nth-child(4) > div.hidden-xs > table > tbody > tr:nth-child(n)
        DynamicGecco.html()
                .gecco("http://bestcoder.hdu.edu.cn/contests/contest_list.php", "BestcoderService")
                .listField("list",
                        DynamicGecco.html()
                                .stringField("name").csspath("td:nth-child(2) > a").text().build()
                                .stringField("link").csspath("td:nth-child(2) > a").href().build()
                                .stringField("startTime").csspath("td:nth-child(3)").text().build()
                                .stringField("type").csspath("td:nth-child(4) > span.text-green").text().build()
                                .stringField("status").csspath("td:nth-child(5) > span").text().build()
                                .register()).csspath(role).build().register();
    }
}

