package edu.hust.common.util;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * 时间工具类
 * @author chain
 * @date 2020/9/4
 **/
public class DateUtil {


    /**
     * 得到几天后的时间
     */
    public static Date getDateAfter(Date date, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    /**
     * 得到几天前的时间
     *
     */
    public static Date getDateBefore(Date date, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    /**
     * 得到多少秒之后的时间
     *
     */
    public static Date createBySecond(Date date, int jwtValidTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, jwtValidTime);
        return calendar.getTime();
    }

}
