package team.huoguo.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @description: 启动类
 * @author: GreenHatHG
 * @create: 2019-07-16 11:29
 **/

public class Main {

    public static void main(String[] args) throws IOException {
//        CrawlConfig.crawl();
//       for(Contest item : CrawlConfig.items){
//            System.out.println(item);
//        }
//        MongoDBJDBC.connect("acm");
//        CrawlConfig.saveToMongoDB();
//        MongoDBJDBC.close();
        String selector= "body > table > tbody > tr:nth-child(4) > td > table > tbody > tr > td:nth-child(2) > div";
        String url = "http://acm.hdu.edu.cn/userloginex.php?cid=857";
        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.select(selector);
        System.out.println(elements);

    }

}
