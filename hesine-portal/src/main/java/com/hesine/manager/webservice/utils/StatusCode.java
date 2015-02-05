package com.hesine.manager.webservice.utils;

/**
 * TODO:add description of class here, then delete the line.
 *
 * @author zhanghongbing
 * @version 14-12-9
 */
public class StatusCode {
    /**
     * 系统错误
     */
    public static final int SYSTEM_ERROR = 10000;
    /**
     * 参数为空.
     */
    public static final int ERROR_EMPTY_PARAM = 20001;
    /**
     * 参数格式不正确.
     */
    public static final int ERROR_INCORRECT_FORMAT_PARAM = 20002;
    /**
     * 参数超过长度.
     */
    public static final int ERROR_OVERLENGTH_PARAM = 20003;
    /**
     * 用户认证失败.
     */
    public static final int ERROR_ILLEGAL_USER = 20004;
    /**
     * 数据不存在
     */
    public static final int ERROR_NOT_EXIST = 20005;
    /**
     * 用户密码不正确
     */
    public static final int ERROR_NAME_PASSWORD = 20006;
    /**
     * 状态成功.
     */
    public static final int STATUS_SUCCESS = 0;
    /**
     * 状态失败.
     */
    public static final int STATUS_FAIL = 1;
}
