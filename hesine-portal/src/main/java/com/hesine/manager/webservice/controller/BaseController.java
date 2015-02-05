package com.hesine.manager.webservice.controller;

import com.hesine.manager.utils.Constants;
import com.hesine.manager.vo.User;
import com.hesine.manager.webservice.utils.ResultDto;
import com.hesine.manager.webservice.utils.StatusCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * TODO:add description of class here, then delete the line.
 *
 * @author zhanghongbing
 * @version 14-12-9
 */
public class BaseController {

    /**
     * 获取当前用户
     * @return
     */
    public User getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.HTTP_SESSION_LOGIN_USER);
        return user;
    }


}
