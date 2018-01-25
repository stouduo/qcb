package com.stouduo.qcb.filter;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        String rcode = request.getParameter("code");
        String scode = (String) request.getSession().getAttribute("code");
        if (StringUtils.isEmpty(rcode) || StringUtils.isEmpty(scode) || !rcode.equalsIgnoreCase(scode)) {
            throw new AuthenticationServiceException("验证码错误！");
        }
        return super.attemptAuthentication(request, response);
    }
}    