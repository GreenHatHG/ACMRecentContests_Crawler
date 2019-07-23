package team.huoguo.crawler.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.JsonPipeline;
import team.huoguo.crawler.entity.Contest;
import team.huoguo.crawler.entity.CodeChef;
import team.huoguo.crawler.common.Crawl;
import team.huoguo.crawler.common.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 对抓取codechef数据进行处理
 * @author: GreenHatHG
 * @create: 2019-07-16 11:53
 **/

@PipelineName("CodeChefService")
public class CodeChefService extends JsonPipeline {
    private List<CodeChef> codeChefs = null;

    private void addItem() throws Exception{
        String startTime = null;
        String endTime = null;
        //爬虫得到的时间格式
        Contest contest = null;
        String role = "dd MMM yyyy HH:mm:ss";
        for (CodeChef codeChef : codeChefs) {
            contest = new Contest();
            startTime = codeChef.getStartTime() + " " + codeChef.getStartTimeHms();
            endTime = codeChef.getEndTime() + " " + codeChef.getEndTimeHms();
            //转换为规范的时间格式
            startTime = DateUtils.dataFormat(startTime, role);
            endTime = DateUtils.dataFormat(endTime, role);
            contest.setStartTime(startTime);
            contest.setEndTime(endTime);
            contest.setLength(DateUtils.getLength(startTime, endTime));
            contest.setOj("CodeChef");
            contest.setLink(codeChef.getLink());
            contest.setWeek(DateUtils.getWeek(startTime));
            contest.setName(codeChef.getName());
            Crawl.items.add(contest);
        }
    }

    @Override
    public void process(JSONObject jsonObject){
        codeChefs = new ArrayList<CodeChef>();
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            for (Object obj : jsonArray) {
                JSONObject jobj = (JSONObject) obj;
                codeChefs.add(jobj.toJavaObject(CodeChef.class));
            }
            addItem();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
