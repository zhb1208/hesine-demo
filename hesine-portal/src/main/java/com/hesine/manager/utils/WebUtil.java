package com.hesine.manager.utils;

import com.hesine.manager.utils.token.TokenProvider;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * TODO:add description of class here, then delete the line.
 *
 * @author zhanghongbing
 * @version 14-12-9
 */
public class WebUtil {

    /**
     * 获取请求中的token
     * @param request
     * @return
     */
    public static String getTokenFromRequest(final HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (TokenProvider.TOKEN_COOKIE_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        String authHeader = request.getHeader("Authorization");
        return authHeader;
    }

    /**
     * 获取客户端ip地址，如果有代理则通过X-Forwarded-For获取
     *
     * @param request
     *            http请求
     * @return String ip地址
     */
    public static String getRemoteIP(HttpServletRequest request) {
        if (request.getHeader("X-Forwarded-For") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("X-Forwarded-For");
    }

    /**
     * 获取ip日志信息
     *
     * @param request
     *            http请求
     * @return String ip日志格式
     */
    public static String getIPStr(HttpServletRequest request) {
        StringBuffer sb = new StringBuffer("ip(").append(getRemoteIP(request)).append(")");

        return sb.toString();
    }

}
