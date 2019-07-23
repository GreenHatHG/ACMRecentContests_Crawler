package team.huoguo.crawler.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.JsonPipeline;
import team.huoguo.crawler.common.Crawl;
import team.huoguo.crawler.common.DateUtils;
import team.huoguo.crawler.entity.CodeChef;
import team.huoguo.crawler.entity.CodeForces;
import team.huoguo.crawler.entity.Contest;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 对CodeForces的数据进行处理
 * @author: GreenHatHG
 * @create: 2019-07-19 15:16
 **/

@PipelineName("CodeForcesService")
public class CodeForcesService extends JsonPipeline {

    private void addItem() throws Exception {
        Contest contest = null;
        //爬虫得到的时间格式
        String role = "MMM/dd/yyyy HH:mm";
        String startTime = null;
        String endTime = null;
        for(CodeForces codeForces : list){
            contest = new Contest();
            contest.setOj("CodeForces");
            contest.setName(codeForces.getName());
            startTime = codeForces.getStartTime();
            startTime = DateUtils.dataFormat(startTime, role);
            contest.setStartTime(startTime);

            String length = codeForces.getLength();
            contest.setLength(length);

            //获取时长中的小时和分钟,以计算偏移后的时间
            int h = Integer.parseInt(length.substring(0,2));
            int m = Integer.parseInt(length.substring(3,5));
            int minute = h*60+m;
            endTime = DateUtils.offset(startTime, minute);
            contest.setEndTime(endTime);
            contest.setWeek(DateUtils.getWeek(startTime));

            //此处获取到link可能为null
            if(codeForces.getLink() != null) {
                contest.setLink(codeForces.getLink());
            }

            Crawl.items.add(contest);
        }
    }
    private List<CodeForces> list = null;
    @Override
    public void process(JSONObject jsonObject) {
        list = new ArrayList<CodeForces>();
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            for (Object obj : jsonArray) {
                JSONObject jobj = (JSONObject) obj;
                list.add(jobj.toJavaObject(CodeForces.class));
            }
            addItem();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
