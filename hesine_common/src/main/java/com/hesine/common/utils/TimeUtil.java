package com.hesine.common.utils;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created with IntelliJ IDEA.
 * User: jason
 * Date: 13-11-18
 * Time: 下午6:26
 * 时间操作工具类
 */
public class TimeUtil {

    /**
     * 根据传入的日期进行日期的加减后返回新日期
     *
     * @param day  格式为yyyyMMdd
     * @param days 要加减的天数
     * @return 返回的日期格式为yyyyMMdd
     */
    public static String getNewDay(String day, int days) {
        day = day.substring(0, 4) + "-" + day.substring(4, 6) + "-" + day.substring(6);
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(df.parse(day));
        } catch (Exception e) {
            ;
        }
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + days);//让日期进行加减
        return sf.format(calendar.getTime());
    }

    /**
     * 根据传入的日期时间进行日期加减后返回新的
     *
     * @param dateTime 时间
     * @param days     要加减的天数
     * @return
     */
    public static String getNewDateTime(String dateTime, int days) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(df.parse(dateTime));
        } catch (Exception e) {

        }
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + days);//让日期进行加减
        return sf.format(calendar.getTime());
    }

    /**
     * 根据传入的日期返回日期所在的周范围
     *
     * @param day 一个日期
     * @return
     */
    public static String[] getWeekDate(Date day) {
        String[] weekDay = new String[2];
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(day);
            // 默认时，每周第一天为星期日，需要更改一下，将每周的第一天设置为周一
            c.setFirstDayOfWeek(Calendar.MONDAY);
            c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            weekDay[0] = df.format(c.getTime());
            c.set(c.DAY_OF_WEEK, Calendar.SUNDAY);
            weekDay[1] = df.format(c.getTime());
        } catch (Exception e) {
        }
        return weekDay;
    }

    /**
     * 根据传入的日期返回日期所在的当月的日期范围
     *
     * @param getWeekDate
     * @return
     */
    public static String[] getMonthDay(Date getWeekDate) {
        String[] monthDay = new String[2];
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(getWeekDate);
            c.set(c.DAY_OF_MONTH, 1);
            monthDay[0] = df.format(c.getTime());
            c.set(Calendar.DATE, 1);
            c.roll(Calendar.DATE, -1);
            monthDay[1] = df.format(c.getTime());
        } catch (Exception e) {
        }
        return monthDay;
    }

    /**
     * 根据传入的日期返回日期所在的上月的日期范围
     *
     * @param day
     * @return
     */
    public static String[] getLastMonthDay(Date day) {
        String[] lastMonthDay = new String[2];
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(day);
            c.set(Calendar.DATE, 1);
            c.add(Calendar.MONTH, -1);
            lastMonthDay[0] = df.format(c.getTime());
            Calendar c2 = Calendar.getInstance();
            c2.setTime(day);
            c2.add(Calendar.MONTH, -1);   //加一个月
            c2.set(Calendar.DATE, 1);    //把日期设置为当月第一天
            c2.roll(Calendar.DATE, -1);
            lastMonthDay[1] = df.format(c2.getTime());
        } catch (Exception e) {
        }
        return lastMonthDay;
    }

    /**
     * 根据月份获取本季度的时间范围
     *
     * @param day
     * @return
     */
    public static String[] getThisSeasonDay(Date day) {
        SimpleDateFormat df_month = new SimpleDateFormat("MM");
        int month = Integer.parseInt(df_month.format(day));
        int array[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
        int season = 1;
        if (month >= 1 && month <= 3) {
            season = 1;
        }
        if (month >= 4 && month <= 6) {
            season = 2;
        }
        if (month >= 7 && month <= 9) {
            season = 3;
        }
        if (month >= 10 && month <= 12) {
            season = 4;
        }
        int start_month = array[season - 1][0];
        int end_month = array[season - 1][2];

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");//可以方便地修改日期格式
        String years = dateFormat.format(date);
        int years_value = Integer.parseInt(years);

        int start_days = 1;
        int end_days = getLastDayOfMonth(years_value, end_month);
        String[] seasonDay = new String[2];
        try {
            seasonDay[0] = formatDate(parseDate(years_value + "-" + start_month + "-" + start_days, "yyyy-MM-dd"), "yyyy-MM-dd");
            seasonDay[1] = formatDate(parseDate(years_value + "-" + end_month + "-" + end_days, "yyyy-MM-dd"), "yyyy-MM-dd");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return seasonDay;

    }

    /**
     * 根据传入的日期获取此日期上季度的日期范围
     *
     * @param day
     * @return
     */
    public static String[] getLastSeasonDay(Date day) {
        String[] thisSeasonDay = getThisSeasonDay(day);
        String[] lastSeasonDay = new String[2];
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(df.parse(thisSeasonDay[0]));
            c.set(Calendar.DATE, 1);
            c.add(Calendar.MONTH, -3);
            lastSeasonDay[0] = df.format(c.getTime());
            c.add(Calendar.MONTH, 3);
            c.set(Calendar.DAY_OF_MONTH, 0);
            lastSeasonDay[1] = df.format(c.getTime());
        } catch (Exception e) {

        }
        return lastSeasonDay;
    }

    /**
     * 获取某年某月的最后一天
     *
     * @param year  年
     * @param month 月
     * @return 最后一天
     */
    public static int getLastDayOfMonth(int year, int month) {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
                || month == 10 || month == 12) {
            return 31;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }
        if (month == 2) {
            if (isLeapYear(year)) {
                return 29;
            } else {
                return 28;
            }
        }
        return 0;
    }

    /**
     * 是否闰年
     *
     * @param year 年
     * @return
     */
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    /**
     * 日期之差
     *
     * @param date        起始日期，注意，时间一定要00：00：00，否则结果会由于毫秒数相差不到一天而不正确
     * @param anotherDate
     * @return 相差天数
     */
    public static int compareDate(Date date, Date anotherDate) {
        if (null == date || null == anotherDate) {
            return -1;
        }
        long intervalMilli = anotherDate.getTime() - date.getTime();
        return (int) (intervalMilli / (24 * 60 * 60 * 1000));
    }

    /**
     * 今天
     *
     * @param dateFormat 素要的时间格式
     * @return 时间
     */
    public static String getNow(String dateFormat) {
        if (null == dateFormat) {
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    /**
     * 昨天
     *
     * @param dateFormat 素要的时间格式
     * @return 时间
     */
    public static String getLastDay(String dateFormat) {
        if (null == dateFormat) {
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return df.format(calendar.getTime());
    }

    /**
     * 12天前
     *
     * @param dateFormat 素要的时间格式
     * @return 时间
     */
    public static String getLastTwelveDay(String dateFormat) {
        if (null == dateFormat) {
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -12);
        return df.format(calendar.getTime());
    }

    /**
     * 获取日期类型
     *
     * @param birthdayString
     * @return
     */
    public static Date getPrintDate(String birthdayString) {
        Date birthday = new Date();
        try {
            if (birthdayString != null && !birthdayString.equals("")) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                birthday = sdf.parse(birthdayString);
            } else {
                birthday = null;
            }
        } catch (Exception e) {
            System.out.println("String to Date error");
        }
        return birthday;
    }

    public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) {

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        XMLGregorianCalendar gc = null;
        try {
            gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return gc;
    }

    /**
     * 方法说明: 将日期加一天
     *
     * @param date 要增加的日期
     * @return
     */
    public static Date addOneDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);   //设置当前日期
        c.add(Calendar.DATE, 1); //日期加1
        Date change = c.getTime(); //结果
        return change;
    }

    /**
     * 方法说明: 将日期加hours小时
     *
     * @param date 日期
     * @param hours 要增加的hours小时
     * @return
     */
    public static Date addHour(Date date, int hours) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);   //设置当前日期
        c.add(Calendar.HOUR, hours);  // 增加多少小时
        Date change = c.getTime(); //结果
        return change;
    }

    /**
     * 方法说明: 将日期加minutes分钟
     *
     * @param date  日期
     * @param minutes 要增加的minutes分钟
     *
     * @return
     */
    public static Date addMinute(Date date, int minutes) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);   //设置当前日期
        c.add(Calendar.MINUTE, minutes); // 增加多少分钟
        Date change = c.getTime(); //结果
        return change;
    }

    /**
     * 格式化日期 added by bjwqp 2011-10-28
     *
     * @param date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatDate(Date date) {
        if (date == null) {
            date = new Date();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 根据pattern格式日期，获得日期字符串
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        if (date == null) {
            date = new Date();
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     *
     * @title: parseDate
     * @category:转化日期的方法
     * @param str
     * @param pattern
     * @return
     * @throws java.text.ParseException
     */
    public static Date parseDate(String str,String pattern) throws ParseException {
        if (str == null || pattern == null) {
            throw new IllegalArgumentException("Date and pattern must not be null");
        }

        SimpleDateFormat parser = new SimpleDateFormat(pattern);
        Date date = parser.parse(str);
        return date;
    }

    /**
     * @param startDay 需要比较的时间 不能为空(null),需要正确的日期格式 ,如：2009-09-12
     * @param endDay 被比较的时间  为空(null)则为当前时间
     * @param stype 返回值类型   0为多少天，1为多少个月，2为多少年
     * @return
     * 举例：
     * compareDate("2009-09-12", null, 0);//比较天
     * compareDate("2009-09-12", null, 1);//比较月
     * compareDate("2009-09-12", null, 2);//比较年
     */
    public static int compareDate(String startDay,String endDay,int stype){
        int n = 0;
        String[] u = {"天","月","年"};
        String formatStyle = stype==1?"yyyy-MM":"yyyy-MM-dd";

        endDay = endDay==null?getCurrentDate("yyyy-MM-dd"):endDay;

        DateFormat df = new SimpleDateFormat(formatStyle);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(df.parse(startDay));
            c2.setTime(df.parse(endDay));
        } catch (Exception e3) {
            System.out.println("wrong occured");
        }
        //List list = new ArrayList();
        while (!c1.after(c2)) {                   // 循环对比，直到相等，n 就是所要的结果
            //list.add(df.format(c1.getTime()));    // 这里可以把间隔的日期存到数组中 打印出来
            n++;
            if(stype==1){
                c1.add(Calendar.MONTH, 1);          // 比较月份，月份+1
            }
            else{
                c1.add(Calendar.DATE, 1);           // 比较天数，日期+1
            }
        }
        n = n-1;
        if(stype==2){
            n = (int)n/365;
        }
        System.out.println(startDay+" -- "+endDay+" 相差多少"+u[stype]+":"+n);
        return n;
    }

    public static String getCurrentDate(String format){
        Calendar day=Calendar.getInstance();
        day.add(Calendar.DATE,0);
        SimpleDateFormat sdf=new SimpleDateFormat(format);//"yyyy-MM-dd"
        String date = sdf.format(day.getTime());
        return date;
    }



    public static void main(String[] args){
        String day = TimeUtil.getNewDay("2014-01-01", 1);
        System.out.println(day);

        int month = TimeUtil.compareDate("2009-09-12", "2014-01-12", 1);
        System.out.println(month);

        int month1 = TimeUtil.compareDate("2013-11-12", null, 1);
        System.out.println(month1);

        int month2 = TimeUtil.compareDate("2014-05-12", "2015-08-12", 1);
        System.out.println(month2);
    }
}
