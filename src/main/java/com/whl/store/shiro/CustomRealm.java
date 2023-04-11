package com.whl.store.shiro;

import com.whl.store.entity.UserInfo;
import com.whl.store.services.SysUserService;
import com.whl.store.util.jwt.JwtUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public boolean supports(AuthenticationToken token){
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("用户授权");
        String username = JwtUtils.getAccount(principalCollection.toString());
        System.out.println("username="+username);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        UserInfo userInfo = sysUserService.findUserByUsername(username);
        info.addStringPermissions(sysUserService.findUserPermissionsByUid(userInfo.getUid()));
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("身份认证");
        String token = (String) authenticationToken.getPrincipal();
        String username = JwtUtils.getAccount(token);
        System.out.println("AuthenticationInfo=="+username);
        if (username == null){
            throw new AuthenticationException("认证失败");
        }
        return new SimpleAuthenticationInfo(token,token,"MyRealm");
    }
}
