package com.hesine.common.utils;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: jason
 * Date: 13-11-28
 * Time: 上午10:38
 * To change this template use File | Settings | File Templates.
 */
public class TimeBean implements Serializable {
    private boolean result;
    public String time;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
