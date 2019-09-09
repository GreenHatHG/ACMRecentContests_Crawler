package team.huoguo.crawler.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.JsonPipeline;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import team.huoguo.crawler.common.CrawlConfig;
import team.huoguo.crawler.common.DateUtils;
import team.huoguo.crawler.entity.Bestcoder;
import team.huoguo.crawler.entity.Contest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pistachio
 * @date 2019/8/21 下午10:38
 */
@PipelineName("BestcoderService")
public class BestcoderService extends JsonPipeline {
    private List<Bestcoder> list= null;

    private String getEndTime(String url) throws IOException {
        String selector = "#contest-time > tbody > tr:nth-child(1) > td:nth-child(3)";
        Document doc = Jsoup.connect(url).get();
        String elements = doc.select(selector).text();
        String endTime = elements.substring(22, 23 + 15);
        return endTime;
    }

    private void addItem() throws Exception {
        Contest contest = null;
        for (Bestcoder item : list) {
            if("Ended".equals(item.getStatus())){
                continue;
            }
            contest = new Contest();
            String endTime = getEndTime(item.getLink());
            String role = "yyyy-MM-dd HH:mm:ss";
            String startTime = DateUtils.dateFormat(item.getStartTime(), role);
            String length = DateUtils.getLength(startTime, endTime);
            String week = DateUtils.getWeek(startTime);

            contest.setOj("百度之星");
            contest.setName(item.getName());
            contest.setStartTime(startTime);
            contest.setEndTime(endTime);
            contest.setLink(item.getLink());
            contest.setLength(length);
            contest.setWeek(week);
           // System.out.println(contest);
            CrawlConfig.items.add(contest);
        }

    }

    @Override
    public void process(JSONObject jsonObject) {
 //       System.out.println(jsonObject);
        list = new ArrayList<Bestcoder>();
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            for (Object obj : jsonArray) {
                JSONObject objo = (JSONObject) obj;
                list.add(objo.toJavaObject(Bestcoder.class));
            }
            addItem();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
