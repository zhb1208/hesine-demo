package com.hesine.manager.web.init;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: hongbing.zhang
 * Date: 2010-6-21
 * Time: 14:44:02
 * To change this template use File | Settings | File Templates.
 */
public class SystemConfig {
    public static final String PROPERTY_FILE = "important.properties";

    private static SystemConfig instance = null;

    private static Properties paraProps = new Properties();

    private SystemConfig() {
        InputStream is = getClass().getResourceAsStream("/conf/" + PROPERTY_FILE);
        try {
            paraProps.load(is);
        } catch (Exception e) {
            System.err.println("不能读取属性文件. " + "请确保" + PROPERTY_FILE + "在CLASSPATH指定的路径中");
        }
    }

    private SystemConfig(String propertyFile) {
        InputStream is = getClass().getResourceAsStream("/conf/" + propertyFile);
        try {
            paraProps.load(is);
        } catch (Exception e) {
            System.err.println("不能读取属性文件. " + "请确保" + propertyFile + "在CLASSPATH指定的路径中");
        }
    }

    public static synchronized SystemConfig getInstance() {
        if (instance == null) {
            instance = new SystemConfig();
        }
        return instance;

    }

    public static synchronized SystemConfig getInstance(String propertyFile) {
        if (instance == null) {
            instance = new SystemConfig(propertyFile);
        }
        return instance;

    }

    public String getProperty(String paraName) {
        return paraProps.getProperty(paraName);
    }
}
