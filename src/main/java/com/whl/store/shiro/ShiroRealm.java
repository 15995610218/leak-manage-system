//package com.whl.store.shiro;
//
//import com.whl.store.entity.UserInfo;
//import com.whl.store.services.SysUserService;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.SimpleAuthenticationInfo;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
//@Slf4j
//public class ShiroRealm extends AuthorizingRealm {
//
//    @Autowired
//    private SysUserService sysUserService;
//
//    /**
//     * 根据token判断是否使用次realm
//     */
//    @Override
//    public boolean supports(AuthenticationToken token){
//        return token instanceof JwtToken;
//    }
//
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        //System.out.println("===doGetAuthorizationInfo===");
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        String token = principalCollection.getPrimaryPrincipal().toString();
//        String username = JwtTools.getUsername(token);
//        UserInfo userInfo = sysUserService.findUserByUsername(username);
//        //获取用户的权限码
//        List<String> pers = sysUserService.findUserPermissionsByUid(userInfo.getUid());
//        //添加用户的权限码
//        authorizationInfo.addStringPermissions(pers);
//        return authorizationInfo;
//    }
//
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        //System.out.println("====AuthenticationToken====");
//        String token = (String) authenticationToken.getCredentials();
//        //获取token中的username信息与数据库中的用户信息对比
//        String username = null;
//        try{
//            username = JwtTools.getUsername(token);
//        }catch (Exception e){
//            throw new AuthenticationException("非法token，不是规范的token，可能被篡改或者过期了");
//        }
//        //判断username是否为空或者token是否有效
//        if (username == null|| !JwtTools.verify(token)){
//            throw new AuthenticationException("token认证失败，token错误或过期，请重新登陆");
//        }
//        return new SimpleAuthenticationInfo(token,token,"ShiroRealm");
//    }
//}
