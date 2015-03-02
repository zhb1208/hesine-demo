/*
 * Copyright (c) 2014, hesine Technologies,Inc. All rights reserved.
 */

package com.hesine.common.constant;

/**
 * Created with IntelliJ IDEA.
 * User: jason
 * Date: 14-11-10
 * Time: 下午6:44
 * 返回结果集的状态
 */
public enum ExceptionType {

    WARN("WARN"), ERROR("ERROR");

    private final String type;

    private ExceptionType(String type) {
        this.type = type;
    }

    public String toString(){
        return type;
    }

}
