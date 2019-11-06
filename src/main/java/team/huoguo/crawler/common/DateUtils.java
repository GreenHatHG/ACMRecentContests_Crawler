package team.huoguo.crawler.common;


import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @description: 日期工具类
 * @author: GreenHatHG
 * @create: 2019-07-16 19:52
 **/
public class DateUtils {

    /**
     * 将字符串类型的时间规范到yyyy-MM-dd HH:mm格式
     *
     * @param dateStr 字符串
     * @param role    当前字符串的时间格式
     * @return String  规范后格式的时间
     **/

    public static String dateFormat(String dateStr, String role) throws Exception {
/*        Locale locale = Locale.US;
        SimpleDateFormat frm = new SimpleDateFormat(role, locale);
        Date date = frm.parse(dateStr);
        return DateUtil.formatDateTime(date);*/
        //第三个参数是为了防止输入的英文识别成中文
        Date date = DateUtil.parse(dateStr, role, Locale.US);
        return DateUtil.format(date, "yyyy-MM-dd HH:mm");
    }

    /**
     * 计算两个时间间隔，精确到分钟
     *
     * @param start
     * @param end
     * @return String
     */
    public static String getLength(String start, String end) {
        Date date1 = DateUtil.parse(start);
        Date date2 = DateUtil.parse(end);
        //除以1000是为了转换成秒
        int between = (int) (date2.getTime() - date1.getTime()) / 1000;
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

    /**
     * 传入一个已经规范好的时间，计算出是星期几
     *
     * @param dateString
     * @return String
     */
    public static String getWeek(String dateString) {
        DateTime dateTime = new DateTime(dateString, DatePattern.NORM_DATETIME_MINUTE_FORMAT);
        return dateTime.dayOfWeekEnum().toChinese();
    }

    /**
     * 传入时间和时长,计算偏移后的时间
     *
     * @param time
     * @param minute
     * @return
     */
    public static String offset(String time, int minute) throws Exception {
        Date date = DateUtil.parse(time);
        String offsetTime = DateUtil.offsetMinute(date, minute).toString();
        return DateUtils.dateFormat(offsetTime, "yyyy-MM-dd HH:mm");
    }

    public static String timestampTotime(String timestamp) {
        if (timestamp == null || timestamp.equals(" "))
            return null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Long time = Long.valueOf(timestamp) * 1000;  //10位时间戳转为Date
        String d = format.format(time);
        return d;
    }

}
