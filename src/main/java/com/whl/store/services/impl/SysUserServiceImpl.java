package com.whl.store.services.impl;
import com.whl.store.entity.*;
import com.whl.store.mapper.SysUserMapper;
import com.whl.store.services.SysUserService;
import com.whl.store.services.ex.*;
import com.whl.store.util.PasswordResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired(required = false)
    private SysUserMapper sysUserMapper;
    @Override
    public void addUser(UserInfo user) {
        UserInfo tmpuser = sysUserMapper.findUserByUsername(user.getUsername());
        if (tmpuser != null){
            throw new UserAddExeception("用户已存在");
        }
        String password = user.getPassword();
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5password = PasswordResult.getMD5Password(password,salt);
        user.setPassword(md5password);
        user.setSalt(salt);
        Integer row = sysUserMapper.addUser(user);
        if (row != 1){
            throw new UserAddExeception("用户添加失败");
        }
    }

    /**
     * 业务层用户添加角色关联关系
     * @param uid
     * @param rid
     */
    @Override
    public void addUserRoleService(Integer uid, Integer rid) {
        Integer row = sysUserMapper.addUserAndRole(uid,rid);
        if (row != 1){
            throw new UserAddExeception("用户角色关联失败");
        }
    }

    /**
     * 业务层根据用户名查询数据库的用户对象信息
     * @param username
     * @return
     */
    @Override
    public UserInfo findUserByUsername(String username) {
        UserInfo user = sysUserMapper.findUserByUsername(username);
        if (user == null){
            throw new UserNotFoundException("用户名或密码错误");
        }
        return user;
    }
    /**
     * 业务层根据用户ID查询用户的角色
     * @param uid
     * @return
     */
    @Override
    public SysRole findUserRoleByUid(Integer uid) {
        Integer rid = sysUserMapper.findUserRoleIdByUid(uid);
        if (rid == null){
            throw new UserNotRoleException("用户未关联角色");
        }
        SysRole role = sysUserMapper.findRoleByRid(rid);
        return role;
    }
    /**
     * 业务层根据用户ID查询用户的权限
     * @param uid
     * @return 返回权限码列表
     */
    @Override
    public List<String> findUserPermissionsByUid(Integer uid) {
        Integer rid = sysUserMapper.findUserRoleIdByUid(uid);
        List<Integer> pids = sysUserMapper.findRolePermissionsIdByRid(rid);
        if (pids.size() < 1){
            throw new UserNotPermissionsException("用户未关联权限");
        }
        List<String> pers = sysUserMapper.findPermissionsNameByPids(pids);
        return pers;
    }

    /**
     * 业务层接口实现，查询用户信息和角色关联关系
     * @return
     */
    @Override
    public List<UserRole> findAllUserRoleService() {
        List<UserRole> userRoles = sysUserMapper.findAllUserRole();
        if (userRoles.size() == 0){
            throw new UserNotFoundException("未找到对应的用户");
        }
        return userRoles;
    }

    /**
     * 根据用户UID删除用户和角色
     * @param uids
     */
    @Override
    public void delUserRoleService(int[] uids) {
        Integer rRows = sysUserMapper.delUserRoleByUid(uids);
        if (rRows < 1){
            throw new UserRoleDeleteException("账号关联角色删除失败");
        }
        Integer uRows = sysUserMapper.delUserByUid(uids);
        if (uRows < 1){
            throw new UserDeleteExeception("账号删除失败");
        }
    }

    @Override
    public void updateUserStatusService(String username, String status) {
        UserInfo userInfo = sysUserMapper.findUserByUsername(username);
        if (userInfo == null){
            throw new UserNotFoundException("无此用户！");
        }
        Integer row = sysUserMapper.updateUserStatusByUsername(username,status);
        if (row != 1){
            throw new UserUpdateExeception("用户状态修改失败");
        }
    }

    @Override
    public void updateUserPasswordService(String username, String password) {
        UserInfo userInfo = sysUserMapper.findUserByUsername(username);
        if (userInfo == null){
            throw new UserNotFoundException("无此用户!");
        }
        String salt = userInfo.getSalt();
        String md5password = PasswordResult.getMD5Password(password,salt);
        Integer row = sysUserMapper.updateUserPasswordByUsername(username,md5password);
        if (row != 1){
            throw new UserUpdateExeception("用户修改密码失败!");
        }
    }

    @Override
    public void updateUserRoleService(Integer uid, Integer rid) {
        Integer row = sysUserMapper.updateUserRoleByUid(uid,rid);
        if (row != 1){
            throw new UserUpdateExeception("用户角色更新失败");
        }
    }
}
