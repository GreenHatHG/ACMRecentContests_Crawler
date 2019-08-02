package team.huoguo.crawler.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.JsonPipeline;
import team.huoguo.crawler.common.CrawlConfig;
import team.huoguo.crawler.common.DateUtils;
import team.huoguo.crawler.entity.Contest;
import team.huoguo.crawler.entity.Jisuanke;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description: 对计蒜客的数据进行处理
 * @author: GreenHatHG
 * @create: 2019-08-01 21:37
 **/

@PipelineName("JisuankeService")
public class JisuankeService extends JsonPipeline {

    private List<Jisuanke> list = null;

    /**
     * @param regex 正则表达式
     * @param source  需要匹配的字符串
     * @return  匹配到的结果
     */
    private String getMatcher(String regex, String source) {
        String result = "";
        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(source);
            while (matcher.find()) {
                result = matcher.group(1);//只取第一组
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    private void addItem() throws Exception{
        Contest contest = null;
        for (Jisuanke item : list) {
            if(item.getStatus().equals("已结束")){
                continue;
            }
            contest = new Contest();
            contest.setName(item.getName());
            contest.setLink(item.getLink());
            contest.setOj("计蒜客");
            contest.setStartTime(item.getStartTime());

            //得到length格式为5小时 0 分钟,利用正则表达式提取里面的数字
            //转换为05:00这种格式
            String source = item.getLength();
            //去掉空格、制表符、换页符等空白字符
            source = source.replaceAll("\\s*", "");
            String hour = getMatcher("(\\d+)小时\\d+分钟", source);
            String Min = getMatcher("\\d+小时(\\d+)分钟", source);
            int h = Integer.parseInt(hour);
            int M = Integer.parseInt(Min);
            contest.setLength(String.format("%02d:%02d", h, M));
            String endTime = DateUtils.offset(item.getStartTime(), h*60+M);
            contest.setEndTime(endTime);
            contest.setWeek(DateUtils.getWeek(item.getStartTime()));
            CrawlConfig.items.add(contest);
        }
    }
    @Override
    public void process(JSONObject jsonObject) {
        list = new ArrayList<Jisuanke>();
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            for (Object obj : jsonArray) {
                JSONObject jobj = (JSONObject) obj;
                list.add(jobj.toJavaObject(Jisuanke.class));
            }
            addItem();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
