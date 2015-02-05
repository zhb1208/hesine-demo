package com.hesine.manager.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Hongbing.Zhang
 * @desc Cookie的公共类
 * @date 2008-3-21
 */
public class CookieUtils {
    public CookieUtils() {
    }

    /**
     * @param request
     * @param name
     * @return
     * @desc 根据Cookie名称得到Cookie的值，没有返回Null
     * @author Hongbing.Zhang
     */
    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie cookie = getCookie(request, name);
        if (cookie != null) {
            return cookie.getValue();
        } else {
            return null;
        }
    }

    /**
     * @param request
     * @param name
     * @return
     * @desc 根据Cookie名称得到Cookie对象，不存在该对象则返回Null
     * @author Hongbing.Zhang
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie cookies[] = request.getCookies();
        if (cookies == null || name == null || name.length() == 0)
            return null;
        Cookie cookie = null;
        for (int i = 0; i < cookies.length; i++) {
            if (!cookies[i].getName().equals(name))
                continue;
            cookie = cookies[i];
            if (request.getServerName().equals(cookie.getDomain()))
                break;
        }

        return cookie;
    }

    /**
     * @param response
     * @param cookie
     * @desc 删除指定Cookie
     * @author Hongbing.Zhang
     */
    public static void deleteCookie(HttpServletResponse response, Cookie cookie) {
        if (cookie != null) {
            cookie.setPath("/");
            cookie.setValue("");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }

    /**
     * @param response
     * @param cookie
     * @param domain
     * @desc 删除指定Cookie
     * @author Hongbing.Zhang
     */
    public static void deleteCookie(HttpServletResponse response, Cookie cookie, String domain) {
        if (cookie != null) {
            cookie.setPath("/");
            cookie.setValue("");
            cookie.setMaxAge(0);
            cookie.setDomain(domain);
            response.addCookie(cookie);
        }
    }

    /**
     * @param response
     * @param name
     * @param value
     * @desc 添加一条新的Cookie信息，默认有效时间为一个月
     * @author Hongbing.Zhang
     */
    public static void setCookie(HttpServletResponse response, String name, String value) {
        setCookie(response, name, value, 0x278d00);
    }

    /**
     * @param response
     * @param name
     * @param value
     * @param maxAge
     * @desc 添加一条新的Cookie信息，可以设置其最长有效时间(单位：秒)
     * @author Hongbing.Zhang
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int maxAge) {
        if (value == null)
            value = "";
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * @param response
     * @param name
     * @param value
     * @param maxAge
     * @param path
     * @param domain
     * @desc 添加一条新的Cookie信息，可以设置其Name，Value，MaxAge，Path，Domain(单位：秒)
     * @author Hongbing.Zhang
     */
    public static void setCookie(
            HttpServletResponse response,
            String name,
            String value,
            int maxAge,
            String path,
            String domain) {
        if (value == null)
            value = "";
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath(path);
        cookie.setDomain(domain);
        response.addCookie(cookie);
    }
}
