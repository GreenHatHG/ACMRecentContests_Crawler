package team.huoguo.crawler.entity;


import lombok.Data;

/**
 * @description: codechef比赛信息定义类
 * @author: GreenHatHG
 * @create: 2019-07-16 15:35
 **/

@Data
public  class CodeChef{

    private String name;
    private String startTime;
    private String startTimeHms;
    private String endTime;
    private String endTimeHms;
    private String link;

}
