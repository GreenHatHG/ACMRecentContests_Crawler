package team.huoguo.crawler.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.JsonPipeline;
import team.huoguo.crawler.common.CrawlConfig;
import team.huoguo.crawler.common.DateUtils;
import team.huoguo.crawler.entity.Atcoder;
import team.huoguo.crawler.entity.Contest;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 对抓取Atcoder数据进行处理
 * @author: GreenHatHG
 * @create: 2019-08-02 15:54
 **/

@PipelineName("AtcoderService")
public class AtcoderService extends JsonPipeline {

    private List<Atcoder> atcoders = null;

    private void addItem() throws Exception {
        Contest contest = null;
        for (Atcoder atcoder : atcoders) {
            contest = new Contest();

            contest.setOj("Atcoder");
            contest.setLength(atcoder.getLength());
            contest.setName(atcoder.getName());
            contest.setLink(atcoder.getLink());

            //抓取到的数据2019-08-04 21:00:00+0900，需要进行提取
            //而且得到的是日本时间，所以时间需要减一个小时
            String startTime = atcoder.getStartTime().substring(0, 16);
            startTime = DateUtils.offset(startTime, -60);
            contest.setStartTime(startTime);

            //用时长来得到结束时间
            String length = atcoder.getLength();
            int h = Integer.parseInt(length.substring(0, 2));
            int m = Integer.parseInt(length.substring(3, 5));
            int minute = h * 60 + m;
            String endTime = DateUtils.offset(startTime, minute);
            contest.setEndTime(endTime);

            contest.setWeek(DateUtils.getWeek(atcoder.getStartTime()));

            CrawlConfig.items.add(contest);
        }
    }

    @Override
    public void process(JSONObject jsonObject) {
        atcoders = new ArrayList<Atcoder>();
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            for (Object obj : jsonArray) {
                JSONObject jobj = (JSONObject) obj;
                atcoders.add(jobj.toJavaObject(Atcoder.class));
            }
            addItem();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
