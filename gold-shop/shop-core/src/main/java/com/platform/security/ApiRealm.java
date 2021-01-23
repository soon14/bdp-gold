package com.platform.security;

import com.platform.bean.core.AuthorizationUser;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author wlhbdp
 * @date ：Created in 2019/7/30 22:58
 */
@Service
public class ApiRealm extends AuthorizingRealm {
    @Lazy
    @Autowired
    private UserService userService;


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = JwtUtil.getUsername(principals.toString());

        AuthorizationUser user = userService.getAuthorizationInfo(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(user.getRoleCodes());
        Set<String> permission = user.getPermissions();
        simpleAuthorizationInfo.addStringPermissions(permission);
        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JwtUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token invalid,please relogin");
        }

        AuthorizationUser userBean =  UserService.me().getAuthorizationInfo(username);
        if (userBean == null) {
            throw new AuthenticationException("User didn't existed!please relogin");
        }

        try {
            if (!JwtUtil.verify(token,  userBean.getPassword())) {
                throw new AuthenticationException("Username or password error");
            }
        }catch (Exception e){
            throw  new AuthenticationException(e.getMessage());
        }

        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}
