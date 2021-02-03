package com.zzy.vueblog.shiro;

import cn.hutool.json.JSONUtil;
import com.zzy.vueblog.common.Result;
import com.zzy.vueblog.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @blog: blog.csdn.net/qq_43365046
 * @email zzz946041754@163.com
 * @description:
 * @author: zzy
 * @data: 2021/1/30-19:36
 **/
@Component
public class JwtFilter extends AuthenticatingFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServerRequest = (HttpServletRequest) request;
        String jwt = httpServerRequest.getHeader("Authorization");
        if (StringUtils.isEmpty(jwt)){
            return null;
        }
        return new JwtToken(jwt);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServerRequest = (HttpServletRequest) request;
        String jwt = httpServerRequest.getHeader("Authorization");
        if (StringUtils.isEmpty(jwt)){
            return true;
        }else {
            //校验jwt
            Claims claim = jwtUtil.getClaimByToken(jwt);
            if (claim == null || jwtUtil.isTokenExpired(claim.getExpiration())){
                throw new ExpiredCredentialsException("token已失效，请重新登录");
            }
            //执行登录
            return executeLogin(request,response);
        }
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        Throwable throwable = e.getCause() == null ? e : e.getCause();
        Result result = Result.fail(throwable.getMessage());
        String json = JSONUtil.toJsonStr(result);
        try {
            httpServletResponse.getWriter().print(json);
        } catch (IOException ex) {

        }
        return false;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {

        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个OPTIONS请求，这里我们给OPTIONS请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(org.springframework.http.HttpStatus.OK.value());
            return false;
        }

        return super.preHandle(request, response);
    }
}
