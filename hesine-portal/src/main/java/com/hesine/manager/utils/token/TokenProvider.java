package com.hesine.manager.utils.token;

import javax.servlet.http.HttpServletRequest;
import java.util.SortedMap;

/**
 * Created with IntelliJ IDEA.
 * User: jason
 * Date: 14-12-9
 * Time: 下午9:31
 * To change this template use File | Settings | File Templates.
 */
public interface TokenProvider {

    public static final String TOKEN_COOKIE_NAME = "login_token";
    public static final String LOGIN_ID = "login_id";
    public static final String LOGIN_USER = "login_user";

    public abstract String generateToken(SortedMap<String, String> paramSortedMap)
            throws TokenProviderException;

    public abstract String getTokenFromRequest(HttpServletRequest paramHttpServletRequest);


}
