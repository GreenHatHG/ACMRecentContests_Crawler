package team.huoguo.crawler.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.JsonPipeline;
import team.huoguo.crawler.common.Crawl;
import team.huoguo.crawler.common.DateUtils;
import team.huoguo.crawler.entity.Contest;
import team.huoguo.crawler.entity.NowCoder;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 对nowcoder的数据进行处理
 * @author: GreenHatHG
 * @create: 2019-07-16 13:53
 **/

@PipelineName("NowCoderService")
public class NowCoderService extends JsonPipeline {

    private List<NowCoder> nowCoders = null;


    private void addItem() throws Exception{
        Contest contest = null;
        for(NowCoder nowCoder : nowCoders){
            contest = new Contest();
            contest.setLink(nowCoder.getLink());
            contest.setOj("NowCoder");
            contest.setName(nowCoder.getName());

            /**
             * 获取到startTime格式为
             * "startTime":"比赛时间：2019-07-20 12:00 至 2019-07-20 17:00 （时长：5小时）"
             * 需要进行提取并且规范
             */
            String s = nowCoder.getStartTime();
            String startTime = s.substring(5,21) + ":00";
            String endTime = s.substring(24,40) + ":00";
            contest.setLength(DateUtils.getLength(startTime,endTime));
            contest.setStartTime(startTime);
            contest.setEndTime(endTime);
            contest.setWeek(DateUtils.getWeek(startTime));
            Crawl.items.add(contest);
        }
    }

    @Override
    public void process(JSONObject jsonObject) {
        nowCoders = new ArrayList<NowCoder>();
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            for (Object obj : jsonArray) {
                JSONObject jobj = (JSONObject) obj;
                nowCoders.add(jobj.toJavaObject(NowCoder.class));
            }
            addItem();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

