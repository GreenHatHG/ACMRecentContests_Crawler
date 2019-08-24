package team.huoguo.crawler.crawl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import team.huoguo.crawler.common.CrawlConfig;
import team.huoguo.crawler.common.DateUtils;
import team.huoguo.crawler.entity.Contest;
import team.huoguo.crawler.entity.LuoGu;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




/**
 * @desciption 爬取洛谷
 * @author Pistachio
 * @date 2019/8/4 下午3:34
 */
public class CrawlLuoGu extends Crawl{
    String ss1;
    public static String unicodeToString(String str) {

        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch + "");
        }
        return str;
    }
    @Override
    public void crawl() throws IOException{
        Contest contest = null;
        String url = "https://www.luogu.org/contest/list";
        String selector= "head > script:nth-child(9)";
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0")
                .referrer("http://www.baidu.com")
                .get();
        String elements = doc.select(selector).toString();
        Pattern p = Pattern.compile("\"(.*?)\"");
        Matcher m = p.matcher(elements);

        while(m.find())
        {
            String ss = m.group();
            int len = ss.length();
            ss1 = ss.substring(1,len-1);
        }
        String jsonData = unicodeToString(URLDecoder.decode(ss1,"UTF-8"));
       // System.out.println(jsonData);
        JSONObject json = JSONObject.parseObject(jsonData);
        JSONObject currentData = json.getJSONObject("currentData");
        JSONObject contests = currentData.getJSONObject(("contests"));
        JSONArray result = contests.getJSONArray("result");
        LuoGu luoGu = null;

        for(Iterator iterator = result.iterator(); iterator.hasNext();){
            contest = new Contest();
            TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
            JSONObject jsonObject = (JSONObject) iterator.next();
            luoGu = new LuoGu();
            //luoGu.setName(jsonObject.getString("name"));
           // System.out.println(jsonObject.getString("name"));
            String startTime = DateUtils.timestampTotime(jsonObject.getString("startTime"));
            String endTime = DateUtils.timestampTotime(jsonObject.getString("endTime"));
            String link = "https://www.luogu.org/contest/"+jsonObject.getString("id");
            String length = DateUtils.getLength(startTime,endTime);
            String week = DateUtils.getWeek(startTime);
            SimpleDateFormat now = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
            String nowTime = now.format(new Date());
            int res = nowTime.compareTo(startTime);   //现在和开始时间相比
            if(res>0){
                continue;   //已结束
            }
            contest.setOj("洛谷");
            contest.setName(jsonObject.getString("name"));
            contest.setStartTime(startTime);
            contest.setEndTime(endTime);
            contest.setLength(length);
            contest.setWeek(week);
            contest.setLink(link);
            CrawlConfig.items.add(contest);

            try{
               Document docs = Jsoup.connect(url)
                        .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:49.0) Gecko/20100101 Firefox/49.0")
                        .header("Connection", "close")//如果是这种方式，这里务必带上
                        .timeout(8000)//超时时间
                        .get();
            } catch (Exception e) {//可以精确处理timeoutException
                //超时处理
            }
            // System.out.println(contest);
        }
    }


}
