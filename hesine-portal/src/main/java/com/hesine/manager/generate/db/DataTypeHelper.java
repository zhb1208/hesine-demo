package com.hesine.manager.generate.db;

import org.apache.commons.lang3.StringUtils;

/**
 * TODO:add description of class here, then delete the line.
 *
 * @author zhanghongbing
 * @version 15/1/13
 */
public class DataTypeHelper {

    public static final String INTEGER = "Integer";
    public static final String INT = "int";
    public static final String LONG = "Long";
    public static final String BIGINT = "bigint";
    public static final String DOUBLEOBJ = "Double";
    public static final String FLOAT = "float";
    public static final String DOUBLE = "double";
    public static final String STRING = "String";
    public static final String VARCHAR = "varchar";
    public static final String TEXT = "text";
    public static final String BLOB = "blob";
    public static final String CLOB = "clob";
    public static final String CHAR = "char";
    public static final String DATETIME = "datetime";
    public static final String DATE = "Date";
    public static final String SPLIT = "_";


    /**
     * 数据类型转换
     * @param dataType
     * @return
     */
    public static String getDataTypeByConvert(String dataType) {
        if (dataType.startsWith(INT)) {
            return INTEGER;
        }
        if (dataType.startsWith(BIGINT)) {
            return LONG;
        }
        if (dataType.startsWith(FLOAT) || dataType.startsWith(DOUBLE)) {
            return DOUBLEOBJ;
        }
        if (dataType.startsWith(VARCHAR) || dataType.startsWith(CHAR)
                || dataType.startsWith(TEXT) || dataType.startsWith(BLOB)
                || dataType.startsWith(CLOB)) {
            return STRING;
        }
        if (dataType.startsWith(DATETIME)) {
            return DATE;
        }
        return STRING;
    }

    /**
     * 获取column name
     * @param columnName
     * @return
     */
    public static String getColumnNameByConvert(String columnName) {
        String result = null;
        String[] strs = columnName.split(SPLIT);
        if (strs.length > 1){
            StringBuffer stringBuffer = new StringBuffer();
            for (String str: strs) {
                stringBuffer.append(StringUtils.capitalize(str));
            }
            result = stringBuffer.toString();
        } else {
            result  = StringUtils.capitalize(columnName);
        }
        return result;
    }

    /**
     * 获取Class name
     * @param prefix
     * @param tableName
     * @return
     */
    public static String getClassNameByConvert(String prefix,String tableName) {
        String result = null;
        String curTableName = null;
        if (StringUtils.isNotBlank(prefix)) {
            curTableName = tableName.replaceFirst(prefix, "");
        } else {
            curTableName = tableName;
        }
        String[] strs = curTableName.split(SPLIT);
        if (strs.length > 1){
            StringBuffer stringBuffer = new StringBuffer();
            for (String str: strs) {
                stringBuffer.append(StringUtils.capitalize(str));
            }
            result = stringBuffer.toString();
        } else {
            result  = StringUtils.capitalize(curTableName);
        }
        return result;
    }
}
