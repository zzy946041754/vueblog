package com.zzy.vueblog.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @blog: blog.csdn.net/qq_43365046
 * @email zzz946041754@163.com
 * @description:
 * @author: zzy
 * @data: 2021/1/30-19:42
 **/
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String jwt){
      this.token = jwt;
    }
    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
