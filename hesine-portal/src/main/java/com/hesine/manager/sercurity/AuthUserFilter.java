package com.hesine.manager.sercurity;

import com.hesine.manager.utils.Constants;
import com.hesine.manager.utils.GsonUtils;
import com.hesine.manager.webservice.utils.ResultDto;
import com.hesine.manager.webservice.utils.StatusCode;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;

public class AuthUserFilter extends AccessControlFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthUserFilter.class);

    /**
     * Returns <code>true</code> if the request is a
     * {@link #isLoginRequest(javax.servlet.ServletRequest, javax.servlet.ServletResponse) loginRequest} or
     * if the current {@link #getSubject(javax.servlet.ServletRequest, javax.servlet.ServletResponse) subject}
     * is not <code>null</code>, <code>false</code> otherwise.
     *
     * @return <code>true</code> if the request is a
     * {@link #isLoginRequest(javax.servlet.ServletRequest, javax.servlet.ServletResponse) loginRequest} or
     * if the current {@link #getSubject(javax.servlet.ServletRequest, javax.servlet.ServletResponse) subject}
     * is not <code>null</code>, <code>false</code> otherwise.
     */
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginRequest(request, response)) {
            return true;
        } else {
            Subject subject = getSubject(request, response);
            // If principal is not null, then the user is known and should be allowed access.
            return subject.getPrincipal() != null;
        }
    }

    /**
     * This default implementation simply calls
     * {@link #saveRequestAndRedirectToLogin(javax.servlet.ServletRequest, javax.servlet.ServletResponse) saveRequestAndRedirectToLogin}
     * and then immediately returns <code>false</code>, thereby preventing the chain from continuing so the redirect may
     * execute.
     */
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        WebUtils.saveRequest(request);
        StringBuffer loginUrl = new StringBuffer(getLoginUrl());
        String accept = httpRequest.getHeader(Constants.REQUEST_ACCEPT);
        if (accept.contains(Constants.REQUEST_CONTENTTYPE_XML)
                || accept.contains(Constants.REQUEST_CONTENTTYPE_JSON)) {
            ResultDto dto = new ResultDto();
            dto.setStatus(StatusCode.STATUS_FAIL);
            dto.setErrorCode(StatusCode.ERROR_ILLEGAL_USER);
            String result =  GsonUtils.toJson(dto, ResultDto.class);
            response.getWriter().append(result);
        } else {
            WebUtils.issueRedirect(request, response, loginUrl.toString());
        }
        return false;
    }


}
