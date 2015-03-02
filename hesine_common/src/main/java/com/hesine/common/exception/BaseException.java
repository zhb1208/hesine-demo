/*
 * Copyright (c) 2014, hesine Technologies,Inc. All rights reserved.
 */

package com.hesine.common.exception;

/**
 * 运行时异常，基础类
 *
 * @author zhanghongbing
 * @version 14-11-10
 */
public class BaseException  extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 6617290302299702290L;

    private ExceptionMessage exceptionMessage;

    public BaseException() {
        super();
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
        exceptionMessage = new ExceptionMessage();
        exceptionMessage.setTitle(message);
    }

    public BaseException(String message) {
        super(message);
        exceptionMessage = new ExceptionMessage();
        exceptionMessage.setTitle(message);
    }

    public BaseException(Throwable cause) {
        super(cause);
        exceptionMessage = new ExceptionMessage();
    }

    public BaseException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.getTitle());
        this.exceptionMessage = exceptionMessage;
    }

    /**
     * @return the exceptionMessage
     */
    public ExceptionMessage getExceptionMessage() {
        return exceptionMessage;
    }

    /**
     * @param exceptionMessage the exceptionMessage to set
     */
    public void setExceptionMessage(ExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

}