package com.hesine.manager.webservice.controller;

import com.hesine.manager.service.UserService;
import com.hesine.manager.utils.*;
import com.hesine.manager.utils.token.TokenProvider;
import com.hesine.manager.webservice.utils.ResultDto;
import com.hesine.manager.webservice.utils.StatusCode;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * TODO:add description of class here, then delete the line.
 *
 * @author zhanghongbing
 * @version 14-12-10
 */
@Controller
@RequestMapping(value = "/account")
@SessionAttributes("appSession")
public class AccountRestController {

    private final static Logger LOGGER = LoggerFactory.getLogger(AccountRestController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private TokenProvider tokenProvider;

    /**
    * 用户登录
    */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResultDto login(HttpServletRequest request,
                           HttpServletResponse response,
                           @RequestParam("username") String username,
                           @RequestParam("password") String password) {

        ResultDto dto = new ResultDto();
        // 验证参数
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            dto.setStatus(StatusCode.STATUS_FAIL);
            dto.setErrorCode(StatusCode.ERROR_EMPTY_PARAM);
            return dto;
        }

        System.out.println("用户[" + username + "]");

        UsernamePasswordToken token = new UsernamePasswordToken(username,
                DigestUtils.md5Hex(password.trim()));
        token.setRememberMe(true);
        // 获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        // 所以这一步在调用login(token)方法时,
        // SystemAuthorizingRealm.doGetAuthenticationInfo()方法中,
        // 具体验证方式详见此方法
        try{
            //提交实体/凭据信息,且认证处理
            currentUser.login(token);

            //保存cookie
            CookieUtils.setCookie(response,
                    TokenProvider.LOGIN_USER, username,
                    -1, "/", Constants.DOMAIN);
        }catch(Exception e){
            dto.setStatus(StatusCode.STATUS_FAIL);
            dto.setErrorCode(StatusCode.ERROR_NAME_PASSWORD);
            return dto;
        }

        // 验证是否登录成功
        if (currentUser.isAuthenticated()) {
            System.out.println("用户[" + username
                    + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
        } else {
            token.clear();
        }
        return dto;
    }

//    /**
//     * 基于ContentNegotiationManager,根据URL的后缀渲染不同的格式
//     * /user/login 返回json
//     */
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    @ResponseBody
//    public ResultDto login(HttpServletRequest request,
//                           HttpServletResponse response,
//                           @RequestParam("userName") String username,
//                           @RequestParam("password") String password,
//                           ModelMap modelMap) {
//        ResultDto dto = new ResultDto();
//
//        // 验证参数
//        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
//            dto.setStatus(StatusCode.STATUS_FAIL);
//            dto.setErrorCode(StatusCode.ERROR_EMPTY_PARAM);
//            return dto;
//        }
//
//        try {
//            // default use session 用户登录
//            String enPassword = MD5.encodeString(password.trim());
//            User user = userService.getUserByLoginName(username.trim());
//            if (user == null) {
//                dto.setStatus(StatusCode.STATUS_FAIL);
//                dto.setErrorCode(StatusCode.ERROR_NOT_EXIST);
//                return dto;
//            } else if (!user.getPassword().equals(enPassword)) {
//                dto.setStatus(StatusCode.STATUS_FAIL);
//                dto.setErrorCode(StatusCode.ERROR_NAME_PASSWORD);
//                return dto;
//            }
//
//            HttpSession session = request.getSession();
//            session.setAttribute(Constants.HTTP_SESSION_LOGIN_USER, user);
//
//
//
//            //TODO option user token
////            String token = tokenProvider.generateToken(null);
////            redis 缓存
////            MockSession.map.put(token, user);
////            // 设置成功参数
//////            CookieUtils.setCookie(response,
//////                    TokenProvider.TOKEN_COOKIE_NAME, token,
//////                    60 * 60 * 24, "/", Constants.DOMAIN);
//////            CookieUtils.setCookie(response,
//////                    TokenProvider.LOGIN_USER, user.getLoginName(),
//////                    60 * 60 * 24, "/", Constants.DOMAIN);
////            CookieUtils.setCookie(response,
////                    TokenProvider.TOKEN_COOKIE_NAME, token,
////                    -1, "/", Constants.DOMAIN);
////            dto.setT(token);
//
//            CookieUtils.setCookie(response,
//                    TokenProvider.LOGIN_USER, user.getLoginName(),
//                    -1, "/", Constants.DOMAIN);
//            return dto;
//        } catch (Exception ex) {
//            LOGGER.error(ex.getMessage());
//            dto.setStatus(StatusCode.STATUS_FAIL);
//            dto.setErrorCode(StatusCode.SYSTEM_ERROR);
//            return dto;
//        }
//    }

    /**
     * 检查用户登录
     * /user/checkLogin 返回json
     */
    @RequestMapping(value = "/checkLogin", method = RequestMethod.GET)
    @ResponseBody
    public ResultDto checkLogin() {
        ResultDto dto = new ResultDto();
        try {
            return dto;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            dto.setStatus(StatusCode.STATUS_FAIL);
            dto.setErrorCode(StatusCode.SYSTEM_ERROR);
            return dto;
        }
    }

    /**
     * 基于ContentNegotiationManager,根据URL的后缀渲染不同的格式
     * /user/logout.json 返回json
     */
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    @ResponseBody
    public ResultDto logout(HttpServletRequest request,
                            HttpServletResponse response) {
        ResultDto dto = new ResultDto();
        try {
            // 获取当前的Subject
            Subject currentUser = SecurityUtils.getSubject();
            //提交实体/凭据信息,且认证处理
            currentUser.logout();

            //defaul use session
            HttpSession session = request.getSession();
            session.removeAttribute(Constants.HTTP_SESSION_LOGIN_USER);
            CookieUtils.setCookie(response,
                    TokenProvider.LOGIN_USER, "",
                    0, "/", Constants.DOMAIN);
            return dto;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            dto.setStatus(StatusCode.STATUS_FAIL);
            dto.setErrorCode(StatusCode.SYSTEM_ERROR);
            return dto;
        }
    }
}
