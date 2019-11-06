package team.huoguo.crawler.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @description: 比赛信息规范定义类
 * @author: GreenHatHG
 * @create: 2019-07-16 19:45
 **/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contest {

    private String oj;
    private String name;
    private String link;
    private String startTime;
    private String endTime;
    private String length;
    private String week;

}
