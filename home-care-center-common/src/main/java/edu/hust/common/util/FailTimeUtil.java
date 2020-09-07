package edu.hust.common.util;

import java.util.Date;

import edu.hust.common.constant.FailTime;
/**
 * JWT工具类
 * @author chain
 * @date 2020/9/4
 **/
public class FailTimeUtil {
    public static Date creatValidTime(FailTime failureTime, int jwtValidTime) {
        Date date = new Date();
        if (failureTime.name().equals(FailTime.SECOND)) {
            return   DateUtil.createBySecond(date, jwtValidTime);
        }
        if (failureTime.name().equals(FailTime.MINUTE)) {
            return   DateUtil.createBySecond(date, jwtValidTime * 60);
        }
        if (failureTime.name().equals(FailTime.HOUR)) {
            return   DateUtil.createBySecond(date, jwtValidTime * 60 * 60);
        }
        if (failureTime.name().equals(FailTime.DAY)) {
            return   DateUtil.getDateAfter(date, jwtValidTime);
        }
        return null;
    }

}
