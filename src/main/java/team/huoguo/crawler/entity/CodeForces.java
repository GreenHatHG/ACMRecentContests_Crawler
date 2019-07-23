package team.huoguo.crawler.entity;

import lombok.Data;
import sun.dc.pr.PRError;

/**
 * @description: CodeForces比赛信息定义类
 * @author: GreenHatHG
 * @create: 2019-07-19 15:14
 **/

@Data
public class CodeForces {

    private String name;
    private String link;
    private String startTime;
    private String length;

}
