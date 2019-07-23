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
     * 将字符串类型的时间规范到yyyy-MM-dd HH:mm:ss格式
     * @param dateStr 字符串
     * @param role 当前字符串的时间格式
     * @return String  规范后格式的时间
     **/

    public static String dataFormat(String dateStr, String role) throws Exception{
        Locale locale = Locale.US;
        SimpleDateFormat frm = new SimpleDateFormat(role, locale);
        Date date = frm.parse(dateStr);
        return DateUtil.formatDateTime(date);
    }

    /**
     * 计算两个时间间隔，精确到分钟
     * @param start
     * @param end
     * @return String
     */
    public static String getLength(String start, String end){
        Date date1 = DateUtil.parse(start);
        Date date2 = DateUtil.parse(end);
        //除以1000是为了转换成秒
        int between=(int)(date2.getTime()-date1.getTime())/1000;
        int d=between/(24*3600);
        int h=between%(24*3600)/3600;
        int m=between%3600/60;
        String dStr = null;
        if(d != 0){
            dStr = String.format("%02d", d);
        }
        String hStr = String.format("%02d", h);
        String mStr = String.format("%02d", m);
        if(d == 0){
            return hStr + ":" + mStr;
        }else{
            return dStr + ":" + hStr + ":" + mStr;
        }
    }

    /**
     * 传入一个已经规范好的时间，计算出是星期几
     * @param dateString
     * @return String
     */
    public static String getWeek(String dateString){
        DateTime dateTime = new DateTime(dateString, DatePattern.NORM_DATETIME_FORMAT);
        return dateTime.dayOfWeekEnum().toChinese();
    }

    /**
     * 传入时间和时长,计算偏移后的时间
     * @param time
     * @param minute
     * @return
     */
    public static String offset(String time, int minute){
        Date date = DateUtil.parse(time);
        return DateUtil.offsetMinute(date, minute).toString();
    }

}
