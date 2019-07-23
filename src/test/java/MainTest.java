import cn.hutool.core.date.DateUtil;
import team.huoguo.crawler.Main;

import java.util.Date;

/**
 * @description: 代码片段测试
 * @author: GreenHatHG
 * @create: 2019-07-19 15:02
 **/
public class MainTest {

    public static String getLength(String start, String end) {
        Date date1 = DateUtil.parse(start);
        Date date2 = DateUtil.parse(end);
        int between = (int) (date2.getTime() - date1.getTime()) / 1000;//除以1000是为了转换成秒
        int d = between / (24 * 3600);
        int h = between % (24 * 3600) / 3600;
        int m = between % 3600 / 60;
        String dStr = null;
        if (d != 0) {
            dStr = String.format("%02d", d);
        }
        String hStr = String.format("%02d", h);
        String mStr = String.format("%02d", m);
        if (d == 0) {
            return hStr + ":" + mStr;
        } else {
            return dStr + ":" + hStr + ":" + mStr;
        }
    }

    public static void main(String[] args) {
        String s1 = "2019-07-21 08:00:00";
        String s2 = "2019-07-21 10:55:00";
        System.out.println(getLength(s1, s2));
    }
}
