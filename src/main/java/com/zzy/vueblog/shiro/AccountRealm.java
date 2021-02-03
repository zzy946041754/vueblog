package com.zzy.vueblog.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.zzy.vueblog.entity.User;
import com.zzy.vueblog.service.UserService;
import com.zzy.vueblog.utils.JwtUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.net.Authenticator;

/**
 * @blog: blog.csdn.net/qq_43365046
 * @email zzz946041754@163.com
 * @description:
 * @author: zzy
 * @data: 2021/1/30-18:52
 **/
@Component
public class AccountRealm extends AuthorizingRealm {

    private final JwtUtil jwtUtil;

    private final UserService userService;

    public AccountRealm(JwtUtil jwtUtil, UserService userService){
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) token;
        String userId = jwtUtil.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();
        User user = userService.getById(Long.valueOf(userId));
        if (null == user){
            throw new UnknownAccountException("用户不存在");
        }
        if (user.getStatus() == -1){
            throw new LockedAccountException("用户已被锁定");
        }
        AccountProfile profile = new AccountProfile();
        BeanUtil.copyProperties(user,profile);
        return new SimpleAuthenticationInfo(profile,jwtToken.getCredentials(),getName());
    }
}
