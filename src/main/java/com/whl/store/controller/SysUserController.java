package com.whl.store.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.whl.store.entity.RequestAddUser;
import com.whl.store.entity.ResultUser;
import com.whl.store.entity.UserInfo;
import com.whl.store.entity.UserRole;
import com.whl.store.services.SysUserService;
import com.whl.store.services.ex.UserNotFoundException;
import com.whl.store.services.ex.UserUpdateExeception;
import com.whl.store.util.AllException;
import com.whl.store.util.JsonResult;
import com.whl.store.util.PasswordResult;
import com.whl.store.util.jwt.JwtUtils;
import com.whl.store.util.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("whl")
public class SysUserController extends AllException {
    /**
     * author:weihailong
     * 方法：登陆和用户管理controller接口
     */
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("login")
    public JsonResult<ResultUser> login(String username, String password, HttpServletResponse response) throws JsonProcessingException {
        //获取当前登陆的用户
        UserInfo userInfo = sysUserService.findUserByUsername(username);
        if (userInfo == null || "".equals(userInfo)){
            throw new AuthenticationException("账号或密码错误!!判断用户名");
        }
        String salt = userInfo.getSalt();
        String md5password = PasswordResult.getMD5Password(password,salt);
        if (!userInfo.getPassword().equals(md5password)){
            log.info(new Date(System.currentTimeMillis())+": username="+userInfo.getUsername()+"密码错误!");
            throw new AuthenticationException("账号或密码错误!!!!判断密码");
        }
        Long currentTimeMillis = System.currentTimeMillis();
        String token = JwtUtils.sign(username,currentTimeMillis);
        //String token = JwtTools.createToken(username);
        redisUtils.set(userInfo.getUsername(),currentTimeMillis,JwtUtils.REFRESH_EXPIRE_TIME);
        List<String> pers = sysUserService.findUserPermissionsByUid(userInfo.getUid());
        ResultUser resultUser = new ResultUser();
        response.setHeader("Authorization",token);
        response.setHeader("Access-Control-Expose-Headers","Authorization");
        resultUser.setUsername(userInfo.getUsername());
        resultUser.setPermissions(pers);
        resultUser.setToken(token);
        return new JsonResult<>(ok,resultUser);
    }
    @RequestMapping("noauth")
    public String unauth(){
        return "未经授权无法访问此数据";
    }

    /**
     * 查询用户名和角色名
     * @return
     */
    @RequestMapping("userinfo")
    public JsonResult<List> userInfo(){
        List<UserRole> userRoles = sysUserService.findAllUserRoleService();
        return new JsonResult<>(ok,userRoles);
    }

    /**
     * 添加用户
     * @param userForm
     * @return
     */
    @PostMapping("adduser")
    //@ResponseBody
    public JsonResult<Void> addUser(@RequestBody RequestAddUser userForm){
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(userForm.getUsername());
        userInfo.setPassword(userForm.getPassword());
        userInfo.setStatus(userForm.getStatus());
        sysUserService.addUser(userInfo);
        UserInfo userInfo1 = sysUserService.findUserByUsername(userForm.getUsername());
        if (userInfo1 == null){
            throw new UserNotFoundException("用户添加失败");
        }
        Integer uid = userInfo1.getUid();
        sysUserService.addUserRoleService(uid,userForm.getRid());
        return new JsonResult<>(ok);
    }

    /**
     * 删除用户
     * @param uids
     * @return
     */
    @PostMapping("deluser")
    public JsonResult<String> delUser(@RequestBody int[] uids){
        sysUserService.delUserRoleService(uids);
        return new JsonResult<>(ok,"账号删除成功");
    }

    /**
     * 更新用户信息
     * @param requestAddUser
     * @return
     */
    @PostMapping("updateuser")
    public JsonResult<String> updateuser(@RequestBody RequestAddUser requestAddUser){
        if (requestAddUser.getRid() != null){
            UserInfo userInfo = sysUserService.findUserByUsername(requestAddUser.getUsername());
            sysUserService.updateUserRoleService(userInfo.getUid(), requestAddUser.getRid());
        }
        sysUserService.updateUserStatusService(requestAddUser.getUsername(), requestAddUser.getStatus());
        return new JsonResult<>(ok,"操作成功");
    }

    /**
     * 管理员修改用户密码
     * @param requestAddUser
     * @return
     */
    @PostMapping("updatepassword")
    public JsonResult<Void> updatePassword(@RequestBody RequestAddUser requestAddUser){
        if (requestAddUser.getUsername() != null && requestAddUser.getPassword() != null){
            sysUserService.updateUserPasswordService(requestAddUser.getUsername(), requestAddUser.getPassword());
        }else {
            throw new UserUpdateExeception("修改密码失败!");
        }
        return new JsonResult<>(ok);
    }

    @PostMapping("revisepassword")
    public JsonResult<Void> revisepassword(@RequestBody RequestAddUser requestAddUser){
        if (requestAddUser.getUsername() != null && requestAddUser.getOldpassword() != null && requestAddUser.getPassword() != null){
            UserInfo userInfo = sysUserService.findUserByUsername(requestAddUser.getUsername());
            String salt = userInfo.getSalt();
            String oldmd5password = PasswordResult.getMD5Password(requestAddUser.getOldpassword(),salt);
            if (!userInfo.getPassword().equals(oldmd5password)){
                log.info(new Date(System.currentTimeMillis())+": username="+userInfo.getUsername()+"原始密码错误!");
                throw new AuthenticationException("原始密码错误!");
            }
            sysUserService.updateUserPasswordService(requestAddUser.getUsername(), requestAddUser.getPassword());
        }
        return new JsonResult<>(ok);
    }


    @PostMapping("logout")
    public JsonResult<Void> logout(HttpServletRequest request){
        String token = request.getHeader("token");
        String account = JwtUtils.getAccount(token);
        RedisUtils.del(account);
        return new JsonResult<>(ok);
    }


}
