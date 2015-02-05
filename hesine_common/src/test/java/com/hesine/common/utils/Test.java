package com.hesine.common.utils;

import java.util.Calendar;
/**
 * 得到前一个月的年月日时分秒
 * @author Mr.hu
 * 2013-6-28上午12:00:35
 * Class Explain
 */
public class Test {

    public static String getLastMonthTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int date = calendar.get(Calendar.DATE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        return year+"-"+(month<10?"0"+month:month)+"-"+(date<10?"0"+date:date)+" "+(hour<10?"0"+hour:hour)+":"+(minute<10?"0"+minute:minute)+":"+(second<10?"0"+second:second);
    }

    public static void main(String[] args) {
        System.out.println(Test.getLastMonthTime());
    }

}
