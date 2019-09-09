package team.huoguo.crawler.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.JsonPipeline;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import team.huoguo.crawler.common.CrawlConfig;
import team.huoguo.crawler.common.DateUtils;
import team.huoguo.crawler.entity.Contest;
import team.huoguo.crawler.entity.Hdu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pistachio
 * @date 2019/8/11 下午5:40
 */

@PipelineName("HduService")
public class HduService extends JsonPipeline {
    private List<Hdu> list = null;

    private String getEndTime(String url) throws IOException {
        String selector= "body > table > tbody > tr:nth-child(4) > td > table > tbody > tr > td:nth-child(2) > div";
        Document doc = Jsoup.connect(url).get();
        String elements = doc.select(selector).text();
        int index = elements.indexOf("End Time : ");
        String endTime = elements.substring(index+11,index+11+16);
        return endTime;
    }


    private void addItem() throws Exception {
        Contest contest = null;
        for (Hdu item : list) {
            contest = new Contest();
            if ("HDU".equals(item.getOj())) {
                if(item.getStatus().equals("Ended")) {
                    continue;
                }
                String endTime = getEndTime(item.getLink());
                String role = "yyyy-MM-dd HH:mm:ss";
                String startTime = DateUtils.dateFormat(item.getStartTime(),role);

                String length = DateUtils.getLength(startTime,endTime);
                String week = DateUtils.getWeek(item.getStartTime());

                contest.setOj("杭电");
                contest.setName(item.getName());
                contest.setStartTime(startTime);
                contest.setEndTime(endTime);
                contest.setLength(length);
                contest.setWeek(week);
                contest.setLink(item.getLink());
                //System.out.println(contest);
                CrawlConfig.items.add(contest);
            }
        }
    }

    @Override
    public void process(JSONObject jsonObject) {
     //   System.out.println(jsonObject);
        list = new ArrayList<Hdu>();
        try{
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            for(Object obj : jsonArray){
                JSONObject objo = (JSONObject) obj;
                list.add(objo.toJavaObject(Hdu.class));
            }
            addItem();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
