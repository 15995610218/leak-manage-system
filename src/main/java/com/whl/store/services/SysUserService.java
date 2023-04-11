package com.whl.store.services;

import com.whl.store.entity.SysPermissions;
import com.whl.store.entity.SysRole;
import com.whl.store.entity.UserInfo;
import com.whl.store.entity.UserRole;

import java.util.List;
import java.util.Map;

public interface SysUserService {

    /**
     * 业务层添加用户接口
     * @param user
     */
    public void addUser(UserInfo user);

    /**
     * 业务层根据用户名查询用户信息
     * @param username
     * @return 返回用户对象
     */
    public UserInfo findUserByUsername(String username);

    /**
     * 业务层根据用户ID查询角色对象
     * @param uid
     * @return
     */
    public SysRole findUserRoleByUid(Integer uid);

    /**
     * 业务层根据用户名查询对应的权限码
     * @param uid
     * @return
     */
    public List<String> findUserPermissionsByUid(Integer uid);

    /**
     * 业务层联合查询用户名和角色名
     * @return
     */
    public List<UserRole> findAllUserRoleService();

    /**
     * 业务层添加用户关联角色
     * @param uid
     * @param rid
     */
    public void addUserRoleService(Integer uid,Integer rid);

    /**
     * 业务层删除用户和角色
     * @param uid
     */
    public void delUserRoleService(int[] uid);

    /**
     * 业务层根据用户名修改用户状态
     * @param username
     * @param status
     */
    public void updateUserStatusService(String username,String status);

    /**
     * 业务层根据用户名修改用户状态
     * @param username
     * @param password
     */
    public void updateUserPasswordService(String username,String password);

    /**
     * 业务层根据用户UID修改用户角色
     * @param uid
     * @param rid
     */
    public void updateUserRoleService(Integer uid,Integer rid);
}
