package com.hesine.manager.generate.property;

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
    public static final String PROPERTY_FILE = "important-gen.properties";

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

    public static synchronized SystemConfig getInstance() {
        if (instance == null) {
            instance = new SystemConfig();
        }
        return instance;

    }

    public String getProperty(String paraName) {
        return paraProps.getProperty(paraName);
    }
}
