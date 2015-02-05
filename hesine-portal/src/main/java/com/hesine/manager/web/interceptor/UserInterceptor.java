package com.hesine.manager.web.interceptor;

import com.hesine.manager.utils.Constants;
import com.hesine.manager.utils.GsonUtils;
import com.hesine.manager.vo.User;
import com.hesine.manager.web.init.SystemConfig;
import com.hesine.manager.webservice.utils.ResultDto;
import com.hesine.manager.webservice.utils.StatusCode;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * Created with IntelliJ IDEA.
 * User: jason
 * Date: 14-8-22
 * Time: 上午11:23
 * To change this template use File | Settings | File Templates.
 */
public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        // default session 验证用户登录
        HttpSession session = request.getSession();
        User tokenUser = (User) session.getAttribute(Constants.HTTP_SESSION_LOGIN_USER);
        if (tokenUser == null){
            String accept = request.getHeader(Constants.REQUEST_ACCEPT);
            if (accept.contains(Constants.REQUEST_CONTENTTYPE_JSON)) {
                ResultDto dto = new ResultDto();
                dto.setStatus(StatusCode.STATUS_FAIL);
                dto.setErrorCode(StatusCode.ERROR_ILLEGAL_USER);
                String result =  GsonUtils.toJson(dto, ResultDto.class);
                response.getWriter().append(result);
            } else {
                response.sendRedirect("/login.html");
            }
            return false;
        }

        System.out.print(request.getHeader("accept")) ;


        //TODO use generation token
//        String token = WebUtil.getTokenFromRequest(request);
//        // use redis cache token
//        User cookieeToken = MockSession.map.get(token);
//        if (cookieeToken == null){
//            ResultDto dto = new ResultDto();
//            dto.setStatus(StatusCode.STATUS_FAIL);
//            dto.setErrorCode(StatusCode.ERROR_ILLEGAL_USER);
//            String result =  GsonUtils.toJson(dto, ResultDto.class);
//            response.getWriter().append(result);
//            return false;
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
