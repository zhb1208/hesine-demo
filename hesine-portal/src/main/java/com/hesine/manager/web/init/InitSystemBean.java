package com.hesine.manager.web.init;

import com.hesine.manager.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created with IntelliJ IDEA.
 * User: jason
 * Date: 14-8-7
 * Time: 上午11:29
 * To change this template use File | Settings | File Templates.
 */
public class InitSystemBean implements InitializingBean{

    // 配置文件
    private String propertyFile;

    public void initProperties(){
        //读取配置文件变量
        SystemConfig systemConfig = null;
        if (StringUtils.isNotEmpty(this.getPropertyFile())) {
            systemConfig = SystemConfig.getInstance(this.getPropertyFile());
        }   else {
            systemConfig = SystemConfig.getInstance();
        }

        // pn config
        Constants.SERVER_URL = systemConfig.getProperty("server.url");

        // TODO ...
    }


    public void afterPropertiesSet() throws Exception {
        this.initProperties();
    }

    public String getPropertyFile() {
        return propertyFile;
    }

    public void setPropertyFile(String propertyFile) {
        this.propertyFile = propertyFile;
    }
}
