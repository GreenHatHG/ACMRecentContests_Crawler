package team.huoguo.crawler.entity;

import lombok.Data;


/**
 * @description: 比赛信息规范定义类
 * @author: GreenHatHG
 * @create: 2019-07-16 19:45
 **/

@Data
public class Contest {

    private String oj;
    private String name;
    private String link;
    private String startTime;
    private String endTime;
    private String length;
    private String week;

}
