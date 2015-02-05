package com.hesine.common.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: jason
 * Date: 13-11-18
 * Time: 下午6:26
 * 日期操作工具类
 */
public class DateUtil {
    
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static Date convertToDate(long source){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(source);
		return calendar.getTime();				
	}
    
    /*
     *
     */
    public static String formatDate(Date date, String format){
        String dateFormat = "";
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
     * @param parsePatterns
     * @return
     * @throws java.text.ParseException
     */
    public static Date parseDate(String str,String ...parsePatterns) throws ParseException{
    	 if (str == null || parsePatterns == null) {
             throw new IllegalArgumentException("Date and Patterns must not be null");
         }
         
         SimpleDateFormat parser = null;
         ParsePosition pos = new ParsePosition(0);
         for (int i = 0; i < parsePatterns.length; i++) {
             if (i == 0) {
                 parser = new SimpleDateFormat(parsePatterns[0]);
             } else {
                 parser.applyPattern(parsePatterns[i]);
             }
             pos.setIndex(0);
             Date date = parser.parse(str, pos);
             if (date != null && pos.getIndex() == str.length()) {
                 return date;
             }
         }
         throw new ParseException("Unable to parse the date: " + str, -1);
    }
}
