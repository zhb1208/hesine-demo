package com.hesine.common.utils;

import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: jason
 * Date: 13-11-18
 * Time: 下午11:27
 * To change this template use File | Settings | File Templates.
 */
public class TimeTest {

    @Test
    public void timeUtilTest(){
        Date date = new Date();
        System.out.println(date);
        // 小时
        Date date1  = TimeUtil.addHour(date, 1);
        System.out.println(date1);
        Date date2  = TimeUtil.addHour(date, -1);
        System.out.println(date2);
        System.out.println(TimeUtil.formatDate(date2));
        System.out.println(TimeUtil.formatDate(date2, "yyyy年MM月dd日 HH:mm"));

        // 分钟
        Date date3  = TimeUtil.addMinute(date, -10);
        System.out.println(date3);
        Date date4  = TimeUtil.addMinute(date, 10);
        System.out.println(date4);

        System.out.println(TimeUtil.formatDate(date4));
        System.out.println(TimeUtil.formatDate(date4, "yyyy年MM月dd日 HH:mm"));

        try {
            // string convert to date
            Date date5 = TimeUtil.parseDate("2013年11月18日 23:50", "yyyy年MM月dd日 HH:mm");
            System.out.println(date5);
            System.out.println(TimeUtil.formatDate(date5, "yyyy年MM月dd日 HH:mm"));
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        int minutes = TimingEnum.getMinutes(1);
        Date date6  = TimeUtil.addMinute(date, minutes);
        System.out.println(TimeUtil.formatDate(date6, "yyyy年MM月dd日 HH:mm"));
    }

}
