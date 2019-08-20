package team.huoguo.crawler.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.JsonPipeline;
import team.huoguo.crawler.common.CrawlConfig;
import team.huoguo.crawler.common.DateUtils;
import team.huoguo.crawler.entity.Contest;
import team.huoguo.crawler.entity.LuoGu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pistachio
 * @date 2019/8/4 下午3:36
 */
@PipelineName("LuoGuoService")
public class LuoGuService extends JsonPipeline {
    private List<LuoGu> list = null;
    private void addItem() throws Exception{
        Contest contest = null;
        for(LuoGu Luogu : list){
            contest = new Contest();
            contest.setOj("洛谷");
            contest.setName(Luogu.getName());
            contest.setLink(Luogu.getLink());
            System.out.println(Luogu.getName());
            /*
            08-13 13:30 ~ 19:30
             */
            String s =Luogu.getStartTime();
            System.out.println(s);
            String startTime  = s.substring(1,11);
            String endTime = s.substring(14,19);

            contest.setLength(DateUtils.getLength(startTime,endTime));
            contest.setStartTime(startTime);
            contest.setEndTime(endTime);
            contest.setWeek(DateUtils.getWeek(startTime));
            CrawlConfig.items.add(contest);

        }
    }
    @Override
    public void process(JSONObject jsonObject) {
        list = new ArrayList<LuoGu>();
        try{
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            for(Object obj : jsonArray){
                JSONObject objo = (JSONObject) obj;
                list.add(objo.toJavaObject(LuoGu.class));
            }
            addItem();
        }catch(Exception e){
            e.printStackTrace();
        }
    }




}
