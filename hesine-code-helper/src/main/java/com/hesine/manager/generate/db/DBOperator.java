package com.hesine.manager.generate.db;

import com.hesine.manager.generate.property.SystemConfig;
import org.apache.commons.lang.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO:add description of class here, then delete the line.
 *
 * @author zhanghongbing
 * @version 15/1/13
 */
public class DBOperator {

    private static String sql = null;
    private static DBHelper db = null;
    private static ResultSet ret = null;
    private static String database = null;
    private static String prefix = null;

    /**
     * 获取全库表
     * @param inputPrefix
     * @return
     */
    public static List<Map<String,String>> getTables(String inputPrefix){

        if (DBConstant.isProperties) {
            SystemConfig systemConfig = SystemConfig.getInstance();
            // db config
            database = systemConfig.getProperty("jdbc.database");
            if (StringUtils.isNotBlank(inputPrefix)) {
                prefix = inputPrefix;
            } else {
                prefix = systemConfig.getProperty("gen.prefix");
            }
        } else {
            database = DBConstant.database;
            if (StringUtils.isNotBlank(inputPrefix)) {
                prefix = inputPrefix;
            } else {
                prefix = DBConstant.prefix;
            }
        }

        sql = "select table_name as tableName, table_comment as tableComment from information_schema.tables " +
                "where table_schema = ?";//SQL语句
        System.out.println(sql);
        db = new DBHelper(sql);//创建DBHelper对象
        List<Map<String,String>> tables = new ArrayList<Map<String, String>>();
        try {
            db.pst.setString(1, database);
            ret = db.pst.executeQuery();//执行语句，得到结果集
            String tmpTableName = null;
            Map<String,String> table = null;
            while (ret.next()) {
                table = new HashMap<String, String>();
                String tableName = ret.getString(1);
                String tableComment = ret.getString(2);
                tmpTableName = DataTypeHelper.getClassNameByConvert(prefix, tableName);
                table.put("tableName", tableName);
                table.put("className", StringUtils.uncapitalize(tmpTableName));
                table.put("ClassName", tmpTableName);
                table.put("comment", tableComment);
                tables.add(table);
                System.out.println(tableName + "\t" + tableComment + "\t"
                        + StringUtils.uncapitalize(tmpTableName) + "\t" + tmpTableName + "\t"  );
            }//显示数据
            ret.close();
            return tables;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();//关闭连接
        }
        return tables;
    }

    /**
     * 获取单表的数据
     * @param inputPrefix
     * @param dbTableName
     * @return
     */
    public static Map<String,String> getTable( String inputPrefix, String dbTableName){
        if (DBConstant.isProperties) {
            SystemConfig systemConfig = SystemConfig.getInstance();
            // db config
            database = systemConfig.getProperty("jdbc.database");
            if (StringUtils.isNotBlank(inputPrefix)) {
                prefix = inputPrefix;
            } else {
                prefix = systemConfig.getProperty("gen.prefix");
            }
        } else {
            database = DBConstant.database;
            if (StringUtils.isNotBlank(inputPrefix)) {
                prefix = inputPrefix;
            } else {
                prefix = DBConstant.prefix;
            }
        }
        sql = "select table_name as tableName, table_comment as tableComment from information_schema.tables " +
                "where table_schema = ? and table_name = ?";//SQL语句
        System.out.println(sql);
        db = new DBHelper(sql);//创建DBHelper对象
        Map<String,String> table = null;
        try {
            db.pst.setString(1, database);
            db.pst.setString(2, dbTableName);
            ret = db.pst.executeQuery();//执行语句，得到结果集
            String tmpTableName = null;
            while (ret.next()) {
                table = new HashMap<String, String>();
                String tableName = ret.getString(1);
                String tableComment = ret.getString(2);
                tmpTableName = DataTypeHelper.getClassNameByConvert(prefix, tableName);
                table.put("tableName", tableName);
                table.put("className", StringUtils.uncapitalize(tmpTableName));
                table.put("ClassName", tmpTableName);
                table.put("comment", tableComment);
                System.out.println(tableName + "\t" + tableComment + "\t"
                        + StringUtils.uncapitalize(tmpTableName) + "\t" + tmpTableName + "\t"  );
            }//显示数据
            ret.close();
            return table;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();//关闭连接
        }
        return table;
    }


    /**
     * desc olalaq_user_order
     */
    public static List<Map<String,String>> getTableColumns(String table){
        sql = "select column_name, column_type, column_comment from information_schema.columns " +
                "where table_name= ?";//SQL语句
        System.out.println(sql);
        db = new DBHelper(sql);//创建DBHelper对象
        List<Map<String,String>> fileds = new ArrayList<Map<String, String>>();
        try {
            db.pst.setString(1, table);
            ret = db.pst.executeQuery();//执行语句，得到结果集
            Map<String,String> field = null;
            String tmpMethodName = null;
            while (ret.next()) {
                String columnName = ret.getString(1);
                String columnType = ret.getString(2);
                String columnComment = ret.getString(3);
                field = new HashMap<String, String>();
                field.put("dbName", columnName);
                field.put("comment", columnComment);
                field.put("dataType", DataTypeHelper.getDataTypeByConvert(columnType));
                field.put("dbDataType", columnType);
                tmpMethodName = DataTypeHelper.getColumnNameByConvert(columnName);
                field.put("methodName", tmpMethodName);
                field.put("name", StringUtils.uncapitalize(tmpMethodName));
                fileds.add(field);
                System.out.println(columnName + "\t" + columnType + "\t" + columnComment
                        + "\t" + tmpMethodName  + "\t" + StringUtils.uncapitalize(tmpMethodName)
                        + "\t" + DataTypeHelper.getDataTypeByConvert(columnType)  + "\t" );
            }//显示数据
            ret.close();
            return fileds;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();//关闭连接
        }
        return fileds;
    }

    public static void main(String[] args) {
        DBOperator.getTables(null);
        String table = "olalaq_user_order";
        DBOperator.getTableColumns(table);
        DBOperator.getTable(null, table);
    }

}
