/*
 * Copyright (c) 2014, hesine Technologies,Inc. All rights reserved.
 */

package com.hesine.common.exception;

import com.hesine.common.constant.ExceptionType;
import com.hesine.common.constant.ResponseErrorCode;

/**
 * 应用异常
 *
 * @author zhanghongbing
 * @version 14-11-10
 */
public class AppException  extends BaseException {
    /**
     *
     */
    private static final long serialVersionUID = -5528753652736280221L;

    protected ResponseErrorCode errorCode;

    public void setErrorCode(ResponseErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    protected ExceptionType exceptionType;

    public ExceptionType getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    public AppException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage);
    }

    public AppException(ResponseErrorCode errorCode) {
        super();
        this.errorCode = errorCode;
        exceptionType = ExceptionType.ERROR;
    }

    public AppException(String message, ResponseErrorCode errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        exceptionType = ExceptionType.ERROR;
    }

    public AppException(String message, ResponseErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
        exceptionType = ExceptionType.ERROR;
    }

    public AppException(Throwable cause, ResponseErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
        exceptionType = ExceptionType.ERROR;
    }

    public AppException(String message, ResponseErrorCode errorCode, ExceptionType exceptionType) {
        super(message);
        this.errorCode = errorCode;
        this.exceptionType = exceptionType;
    }

    public AppException(Throwable cause, ResponseErrorCode errorCode, ExceptionType exceptionType) {
        super(cause);
        this.errorCode = errorCode;
        this.exceptionType = exceptionType;
    }

    public AppException(String message, ResponseErrorCode errorCode, Throwable cause, ExceptionType exceptionType) {
        super(message, cause);
        this.errorCode = errorCode;
        this.exceptionType = exceptionType;
    }

    public ResponseErrorCode getErrorCode() {
        return errorCode;
    }



}