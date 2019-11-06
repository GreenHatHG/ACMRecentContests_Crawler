package team.huoguo.crawler.crawl;

import cn.hutool.core.date.DateUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import team.huoguo.crawler.common.CrawlConfig;
import team.huoguo.crawler.common.DateUtils;
import team.huoguo.crawler.entity.Contest;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @description 爬取codeforces
 * @author: GreenHatHG
 * @create: 2019-08-02 10:50
 **/
public class CrawlCodeForces extends Crawl {

    @Override
    public void crawl() throws Exception {
        Document doc = Jsoup.connect("https://codeforces.com/contests/")
                .userAgent("Mozilla/5.0")
                .referrer("http://www.baidu.com")
                .get();
        final String selector = "#pageContent > div.contestList > div.datatable > div:nth-child(6) > table > tbody > tr:nth-child(n+2) > ";
        List<String> names = doc.select(selector + "td:nth-child(1)").eachText();

        List<String> startTimes = doc.select(selector + "td:nth-child(3) > a").eachText();

        List<String> lengths = doc.select(selector + "td:nth-child(4)").eachText();

        List<String> links = new ArrayList<>();
        Elements elements = doc.select(selector + "td:nth-child(6) > a.red-link");
        for(Element element : elements){
            links.add(element.attr("href"));
        }

        int length = names.size();
        for(int i = 0; i < length; i++){
            String link = i >= links.size() ? null : "https://codeforces.com" + links.get(i);

            //获取时长中的小时和分钟,以计算偏移后的时间
            int h = Integer.parseInt(lengths.get(i).substring(0, 2));
            int m = Integer.parseInt(lengths.get(i).substring(3, 5));
            int minute = h * 60 + m;

            //格式化时间，并计算相差5个小时后的时间
            String startTime = DateUtil.offsetHour(DateUtil.parse(startTimes.get(i), "MMM/dd/yyyy HH:mm", Locale.US), 5).toString("yyyy-MM-dd HH:mm");
            Contest contest = Contest.builder()
                    .oj("CodeForces")
                    .name(names.get(i))
                    .startTime(startTime)
                    .endTime(DateUtils.offset(startTime, minute))
                    .week(DateUtils.getWeek(startTime))
                    .length(lengths.get(i))
                    .link(link)
                    .build();
            CrawlConfig.items.add(contest);
        }
    }
}
