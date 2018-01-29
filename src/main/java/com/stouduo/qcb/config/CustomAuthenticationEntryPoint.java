package com.stouduo.qcb.config;

import com.alibaba.fastjson.JSON;
import com.stouduo.qcb.util.RestResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
    public CustomAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(JSON.toJSONString(RestResult.error("没有权限！")));
    }
}
