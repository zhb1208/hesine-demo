/*
 * Copyright  2013. 360buy.com All Rights Reserved. 
 * Application  vc_auth_integration 
 * Class Name   AuthPassportFilter.java 
 * Date Created 2013-6-22 
 * Author       jason
 * 
 * Revision History 
 * 2013-6-22 下午03:03:29 By jason
 */
package com.hesine.manager.sercurity;

import com.hesine.boss.integration.AuthConstants;
import com.hesine.boss.integration.CookieUtils;
import com.hesine.passport.client.PassportClient;
import com.hesine.passport.client.utils.MD5;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ClassName AuthPassportFilter
 * @Description TODO
 * @author jason
 * @date 2014-6-22 下午03:03:29
 */

public class AuthUserLogoutFilter extends LogoutFilter {
	
	private static final Logger log = LoggerFactory.getLogger(AuthUserLogoutFilter.class);

    private String casLogin;
	

	/**
	 * 覆盖父类的preHandle方法
	 * 第一步清空passport设置的name为hesine的cookie;
	 * 第二步获取Subject实例和redirectUrl;
	 * 第三步调用Subject的logout方法,执行相关的退出操作;
	 * 第四步调用issueRedirect设置用户退出之后显示页面的URL
	 * 第五步返回false,注意只能返回false.
	 */
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		Subject subject = getSubject(request, response);
        String redirectUrl = getRedirectUrl(request, response, subject);
//        String redirectUrl = "http://boss.hesine.com:8080/a/logoutsuccess";
        
        try {
            clearSession((HttpServletRequest) request, (HttpServletResponse) response);
            subject.logout();
            clearCookie((HttpServletRequest) request, (HttpServletResponse) response);
            setSession((HttpServletRequest) request, (HttpServletResponse) response);
        } catch (SessionException ise) {
            log.debug("Encountered session exception during logout.  This can generally safely be ignored.", ise);
        }
        
//        issueRedirect(request, response, redirectUrl);
        WebUtils.issueRedirect(request, response, redirectUrl);
        
		return false;
	}

    private void clearSession(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        //对服务器端发出登出指令
        PassportClient client = new PassportClient(request);
        boolean result = client.logout();


        //删除本地相关数据
        session.removeAttribute(AuthConstants.HTTP_SESSION_LOGIN_USER);
        session.removeAttribute(AuthConstants.HTTP_SESSION_SUBJECT);
        session.removeAttribute( "com.hesine.passport.receipt" );
        session.removeAttribute( "com.hesine.passport.user" );
        session.removeAttribute( "com.hesine.passport.didGateway" );

        request.removeAttribute( "ticket" );
        session.removeAttribute( "loginRedirectUrl" );


    }

    private void setSession(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute( "passport_logout", "true" );
        session.setAttribute( "passport_logout_renew", "true" );
        CookieUtils.setCookie(response,
                AuthConstants.HTTP_COOKIE_PASSPORT_LOGOUT,"true",
                60*60*24,"/",  AuthConstants.DOMAIN);
        CookieUtils.setCookie(response,
                AuthConstants.HTTP_COOKIE_PASSPORT_LOGOUT_RENEW,
                "true", 60*60*24,"/",  AuthConstants.DOMAIN);
        CookieUtils.setCookie(response,
                AuthConstants.COOKIE_LOGIN_USER_NAME,
                MD5.encodeString("auth_logout", "UTF-8"),
                60*60*24, "/",  AuthConstants.DOMAIN);
    }

	/**
	 * 清空passport设置的name为hesine的cookie
	 */
	private void clearCookie(HttpServletRequest request, HttpServletResponse response) {
		try {
            //删除所有网站相关Cookie
            Cookie cookies[] = request.getCookies();
            if(cookies != null && cookies.length > 0)
            {
                for(int i=0; i<cookies.length; i++)
                {
                    Cookie cookietemp = cookies[i];
                    CookieUtils.deleteCookie(response, cookietemp, AuthConstants.DOMAIN);
                }
            }

        } catch (Exception ex) {

		}
	}

//    /**
//     * Redirects the user to CAS, determining the service from the request.
//     */
//    private void redirectToCAS( HttpServletRequest request, HttpServletResponse response )
//            throws IOException, ServletException
//    {
//        String casLoginString =
//                casLogin
//                        + "?service="
//                        + getService((HttpServletRequest) request)
//                        + ((casRenew)
//                        ? "&renew=true"
//                        : "")
//                        + (casGateway ? "&gateway=true" : "");
//
//        WebUtils.issueRedirect(request, response, casLoginString);
//
//    }

    public String getCasLogin() {
        return casLogin;
    }

    public void setCasLogin(String casLogin) {
        this.casLogin = casLogin;
    }
}
