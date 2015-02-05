/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hesine.manager.sercurity;

import com.hesine.manager.utils.Constants;
import com.hesine.manager.utils.GsonUtils;
import com.hesine.manager.webservice.utils.ResultDto;
import com.hesine.manager.webservice.utils.StatusCode;
import com.hesine.passport.client.utils.MD5;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 表单验证（包含验证码）过滤类
 * @author ThinkGem
 * @version 2013-5-19
 */
@Service
public class UserAuthenticationFilter extends PassThruAuthenticationFilter {

    private boolean isAjax(ServletRequest request){
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String accept = httpRequest.getHeader(Constants.REQUEST_ACCEPT);
        if (accept.contains(Constants.REQUEST_CONTENTTYPE_XML)
                || accept.contains(Constants.REQUEST_CONTENTTYPE_JSON)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
            throws IOException {
        if (isAjax(request)) {
            ResultDto dto = new ResultDto();
            dto.setStatus(StatusCode.STATUS_FAIL);
            dto.setErrorCode(StatusCode.ERROR_ILLEGAL_USER);
            String result =  GsonUtils.toJson(dto, ResultDto.class);
            response.getWriter().append(result);
        } else {
            saveRequestAndRedirectToLogin(request, response);
        }
        return false;
    }

}
