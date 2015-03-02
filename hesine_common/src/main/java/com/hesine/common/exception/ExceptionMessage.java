/*
 * Copyright (c) 2014, hesine Technologies,Inc. All rights reserved.
 */

package com.hesine.common.exception;

/**
 * 异常信息
 *
 * @author zhanghongbing
 * @version 14-11-10
 */
public class ExceptionMessage {
    /**
     * 类名
     */
    private String className;
    /**
     * 时间
     */
    private String createTime;
    /**
     * ipAddress
     */
    private String ipAddress;
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 方法参数
     */
    private String methodParams;
    /**
     * 标题
     */
    private String title;
    /**
     * 备注
     */
    private String remark;

    /**
     * @return the className
     */
    public String getClassName() {
        return className;
    }
    /**
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }
    /**
     * @return the createTime
     */
    public String getCreateTime() {
        return createTime;
    }
    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    /**
     * @return the ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }
    /**
     * @param ipAddress the ipAddress to set
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    /**
     * @return the methodName
     */
    public String getMethodName() {
        return methodName;
    }
    /**
     * @param methodName the methodName to set
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    /**
     * @return the methodParams
     */
    public String getMethodParams() {
        return methodParams;
    }
    /**
     * @param methodParams the methodParams to set
     */
    public void setMethodParams(String methodParams) {
        this.methodParams = methodParams;
    }
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }
    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}