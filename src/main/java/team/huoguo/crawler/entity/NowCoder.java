package team.huoguo.crawler.entity;

import lombok.Data;

/**
 * @description: NowCoder比赛信息定义类
 * @author: GreenHatHG
 * @create: 2019-07-19 14:51
 **/

@Data
public class NowCoder {

    private String name;
    private String link;
    private String startTime;
    private String endTime;
//    private String status;
}
