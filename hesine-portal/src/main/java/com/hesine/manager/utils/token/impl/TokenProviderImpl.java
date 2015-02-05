package com.hesine.manager.utils.token.impl;

import com.hesine.manager.utils.WebUtil;
import com.hesine.manager.utils.token.TokenProvider;
import com.hesine.manager.utils.token.TokenProviderException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.SortedMap;
import java.util.UUID;

/**
 * TODO:add description of class here, then delete the line.
 *
 * @author zhanghongbing
 * @version 14-12-9
 */
@Service("tokenProvider")
public class TokenProviderImpl implements TokenProvider {


    @Override
    public String generateToken(SortedMap<String, String> paramSortedMap) throws TokenProviderException {
        String token = UUID.randomUUID().toString();
        return token;
    }

    @Override
    public String getTokenFromRequest(HttpServletRequest paramHttpServletRequest) {
        return WebUtil.getTokenFromRequest(paramHttpServletRequest);
    }
}
