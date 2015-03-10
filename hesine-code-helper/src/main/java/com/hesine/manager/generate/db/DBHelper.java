package com.hesine.manager.generate.db;

import com.hesine.manager.generate.property.SystemConfig;
import org.apache.commons.lang.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * TODO:add description of class here, then delete the line.
 *
 * @author zhanghongbing
 * @version 15/1/13
 */
public class DBHelper {

    public static String url = "jdbc:mysql://127.0.0.1:3306/philips_db?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true";
    public static String name = "com.mysql.jdbc.Driver";
    public static String user = "root";
    public static String password = "";

    public Connection conn = null;
    public PreparedStatement pst = null;

    public DBHelper(String sql) {
        try {
            if (DBConstant.isProperties) {
                SystemConfig systemConfig = SystemConfig.getInstance();
                // db config
                url = systemConfig.getProperty("jdbc.url");
                name = systemConfig.getProperty("jdbc.driver");
                user = systemConfig.getProperty("jdbc.username");
                password = systemConfig.getProperty("jdbc.password");
            }  else {
                url = String.format(DBConstant.url, DBConstant.hostname, DBConstant.database);
                user = DBConstant.user;
                password = DBConstant.password;
            }
            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);//获取连接
            pst = conn.prepareStatement(sql);//准备执行语句
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            this.conn.close();
            this.pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
