/*
 * Copyright (c) 2014, hesine Technologies,Inc. All rights reserved.
 */

package com.hesine.common.constant;

/**
 * Created with IntelliJ IDEA.
 * User: jason
 * Date: 14-11-10
 * Time: 下午6:42
 * 错误码
 */
public enum ResponseErrorCode {

    DAOEXCEPTION("DAOEXCEPTION"),
    WEBSERVICEEXCEPTION("WEBSERVICEEXCEPTION"),
    BUSINESSEXCEPTION("BUSINESSEXCEPTION"),
    RUNTIMEEXCEPTION("RUNTIMEEXCEPTION"),
    SECURITYINVALID("SECURITYINVALID");

    private final String errorCode;

    private ResponseErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String toString(){
        return errorCode;
    }

}
