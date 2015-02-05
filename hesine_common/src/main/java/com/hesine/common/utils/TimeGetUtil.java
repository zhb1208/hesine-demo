package com.hesine.common.utils;


import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: jason
 * Date: 13-11-28
 * Time: 上午10:37
 * To change this template use File | Settings | File Templates.
 */
public class TimeGetUtil {

    public static final String DATE_TIME_FORMAT_CN = "yyyy年MM月dd日 HH:mm";
    public static final String DATE_TIME_FORMAT_TYPE1 = "yyyy-MM-dd HH:mm";
    public static final String DATE_TIME_FORMAT_TYPE2 = "yyyy/MM/dd HH:mm";
    public static final String DATE_TIME_FORMAT_TYPE3 = "yyyyMMdd HH:mm";
    public static final String DATE_FORMAT = "yyyy年MM月dd日";
    public static final String DAY_AM = " 09:00";
    public static final String DAY_PM = " 20:00";
    public static final String YEAR = "年";
    public static final String MONTH = "月";
    public static final String DAY = "日";

    public static Date getNow(){
        return new Date();
    }

    /**
     * 正则验证
     * @param el
     * @param value
     * @return
     */
    public static Boolean commonPattern(String el, String value){
        Pattern p = Pattern.compile(el);
        Matcher m = p.matcher(value);
        boolean b = m.matches();
        return b;
    }

    /**
     * 查找包含el字符内容
     * @param el
     * @param value
     * @return
     */
    public static String commonPatternFind(String el, String value){
        Pattern p = Pattern.compile(el);
        Matcher m = p.matcher(value);
        if (m.find(0))
            return m.group(0);
        return null;
    }

    /**
     * 查找是否包含el字符
     * @param el
     * @param value
     * @return
     */
    public static Boolean checkPatternFind(String el, String value){
        Pattern p = Pattern.compile(el);
        Matcher m = p.matcher(value);
        return m.find(0);
    }

    /**
     * 获取当前时间
     * @param msg
     * @return
     */
    public static String getDateTime(String msg){
        String result  = null;
        String el = String.valueOf("((((1[6-9]|[2-9]\\d)\\d{2})[年|\\-|/]*" +
                "(0?[13578]|1[02])[月|\\-|/]*(0?[1-9]|[12]\\d|3[01][日]))|" +
                "(((1[6-9]|[2-9]\\d)\\d{2})[年|\\-|/]*(0?[13456789]|1[012])[月|\\-|/]*(0?[1-9]|[12]\\d|30)[日])|" +
                "(((1[6-9]|[2-9]\\d)\\d{2})[年|\\-|/]*0?2[月|\\-|/]*(0?[1-9]|1\\d|2[0-8])[日])|" +
                "(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))" +
                "[年|\\-|/]*0?2[月|\\-|/]*29-[日])) " +
                "(20|21|22|23|[0-1]?\\d):[0-5]?\\d");
        result = commonPatternFind(el, msg);
        return result;
    }

    /**
     * 年检查
     * @param msg
     * @return
     */
    public static String getYear(String msg){
        String result  = null;
        String el = "(((1[6-9]|[2-9]d)d{2})年)";
        result = commonPatternFind(el, msg);
        return result;
    }

    /**
     * 月检查
     * @param msg
     * @return
     */
    public static String getMonth(String msg){
        String result  = null;
        String el = "((0?[13578]|1[02])月)|((0?[13456789]|1[012])月)|((0?2)月)";
        result = commonPatternFind(el, msg);
        return result;
    }

    /**
     * 日期检查
     * @param msg
     * @return
     */
    public static String getDay(String msg){
        String result  = null;
        String el = "(((0?[1-9]|[12]\\d|3[01])日)|((0?[1-9]|[12]\\d|30)日)|((0?[1-9]|1\\d|2[0-8])日)|((29)日))";
        result = commonPatternFind(el, msg);
        return result;
    }

    /**
     * 时分检查
     * @param msg
     * @return
     */
    public static String getHM(String msg){
        String result  = null;
        String el = "(20|21|22|23|[0-1]?\\d):[0-5]?\\d";
        result = commonPatternFind(el, msg);
        return result;
    }

    /**
     * 获取TimeBean
     * @param msg
     * @return
     */
    public static TimeBean getTimeBean(String msg){
        TimeBean timeBean = new TimeBean();
        timeBean.setResult(Boolean.FALSE);
        String now = formatDate(getNow(),TimeGetUtil.DATE_TIME_FORMAT_CN);
        // 检查是否含有时候并大于当前时间，有为true，没有为false
        String time = checkTime(msg);
//        String time = "20131203 10:10";
        if(StringUtils.isNotBlank(time)){
            try {
                Date msgTime = null;
                char type = time.charAt(4);
                switch (type){
//                    case '-':
//                        msgTime = parseDate(time, TimeGetUtil.DATE_TIME_FORMAT_TYPE1);
//                        break;
//                    case '/':
//                        msgTime = parseDate(time, TimeGetUtil.DATE_TIME_FORMAT_TYPE2);
//                        break;
//                    case '年':
//                        msgTime = parseDate(time, TimeGetUtil.DATE_TIME_FORMAT_CN);
//                        break;
                    default:
                        msgTime = parseDate(time, TimeGetUtil.DATE_TIME_FORMAT_TYPE3);
                        break;
                }
                //大于当前时间
                if (msgTime.compareTo(getNow()) > 0){
                    timeBean.setResult(Boolean.TRUE);
                    timeBean.setTime(formatDate(msgTime, TimeGetUtil.DATE_TIME_FORMAT_CN));
                }else{
                    timeBean.setTime(now);
                }
            } catch (ParseException e) {
                e.printStackTrace();
                timeBean.setTime(now);
            }
        }else{
            timeBean.setTime(now);
        }
        return timeBean;
    }

    /**
     * 检查时间
     * @param msg
     * @return
     */
    public static String checkTime(String msg){
        String result = null;
        // 1. 查找(2013年5月10日 10:00、 2013-5-10 10:00、 2013/5/10 10:00）
        result = getDateTime(msg);
        if (StringUtils.isNotBlank(result)){
            return result;
        }
        // 2. 查找2013年、月、日、时间
        result = checkPartition(msg);
        if (StringUtils.isNotBlank(result)){
            return result;
        }
        // 3. 其它
        result = checkOthers(msg);
        return result;
    }

    /**
     * 分段检查年、月、日、时间
     * @param msg
     * @return
     */
    public static String checkPartition(String msg){
        boolean flag = false;
        Calendar c = Calendar.getInstance();
        c.setTime(getNow());   //设置当前日期
        // 年
        String year = getYear(msg);
        int yearInt = c.get(Calendar.YEAR);
        if (StringUtils.isNotBlank(year)){
            yearInt = Integer.valueOf(StringUtils.replace(year, YEAR, ""));
            flag = true;
        }

        // 月
        String month = getMonth(msg);
        int monthInt = c.get(Calendar.MONTH);
        if (StringUtils.isNotBlank(month)){
            monthInt = Integer.valueOf(StringUtils.replace(month, MONTH, "")) - 1;
            flag = true;
        }

        // 日
        String day = getDay(msg);
        int dayInt = c.get(Calendar.DAY_OF_MONTH);
        if (StringUtils.isNotBlank(day)){
            dayInt = Integer.valueOf(StringUtils.replace(day, DAY, ""));
            flag = true;
        }

        // 小时、分钟
        String hm = getHM(msg);
        int hourInt = c.get(Calendar.HOUR_OF_DAY);
        int minuteInt = c.get(Calendar.MINUTE);
        if (StringUtils.isNotBlank(hm)){
            String[] hms = StringUtils.split(hm,':');
            hourInt = Integer.valueOf(hms[0]);
            minuteInt = Integer.valueOf(hms[1]);
            flag = true;
        }
        if (flag){
            c.set(yearInt, monthInt, dayInt, hourInt, minuteInt);
            Date change = c.getTime(); //结果
            return formatDate(change, DATE_TIME_FORMAT_CN);
        } else {
            return null;
        }
    }

    /**
     * 方法说明: 将日期加一天
     *
     * @param date 要增加的日期
     * @return
     */
//    public static Date addOneDay(Date date) {
//        Calendar c = Calendar.getInstance();
//        c.setTime(date);   //设置当前日期
//        c.add(Calendar.DATE, 1); //日期加1
//        Date change = c.getTime(); //结果
//        return change;
//    }

    /**
     * 检查其他
     * @param others
     * @return
     */
    public static String checkOthers(String others){
//        String elN = "今晚";
//        if(checkPatternFind(elN, others)){
//            String date = formatDate(getNow(), DATE_FORMAT);
//            return date + DAY_PM;
//        }
//        String elA = "明天";
//        if(checkPatternFind(elA, others)){
//            String date = formatDate(addOneDay(getNow()), DATE_FORMAT);
//            return date + DAY_AM;
//        }
        return null;
    }

    /**
     * 格式化date
     * @param date
     * @param format
     * @return
     */
    public static String formatDate(Date date, String format){
        String dateFormat = null;
        try{
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            dateFormat = sdf.format(date);
        } catch (Exception e){
        }
        return dateFormat;
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

    public static void main(String[] strs){
        String[] msgs = {"星期天一2013年12月3日 10:10下去",
                "2013年12月3日 10:10",
                "星期天一2013年11月3日 10:10下去",
                "2013年11月3日 10:10",
                "星期天一2013-12-03 10:10下去",
                "星期天一2013-11-03 10:10下去",
                "星期天一2013/12/3 10:10下去",
                "星期天一2013/11/3 10:10下去",
                "星期天一20131203 10:10下去",
                "星期天一20131103 10:10下去",
                "星期天一2013123 10:10下去",
                "星期天一2013113 10:10下去",
                "星期天一2013年下去",
                "星期天一12月3日 19:10下去",
                "星期天一3日 19:10下去",
                "星期天一19:10下去",
                "星期天今晚下去",
                "星期天明天下去",
                "星期天一2013/12/下122`下13下去"};
        TimeBean timeBean = null;
        int count = 1;
        for (String msg: msgs){
            timeBean = TimeGetUtil.getTimeBean(msg);
            System.out.println("第：" + count);
            System.out.println("msg内容：" + msg);
            System.out.println("是否有时间：" + timeBean.isResult());
            System.out.println("时间：" + timeBean.getTime());
            System.out.println("");
            count++;
        }

    }
}
