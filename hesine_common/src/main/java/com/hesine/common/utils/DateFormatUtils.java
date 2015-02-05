package com.hesine.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: jason
 * Date: 13-11-18
 * Time: 下午6:26
 * To change this template use File | Settings | File Templates.
 */
public class DateFormatUtils {
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

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

}
