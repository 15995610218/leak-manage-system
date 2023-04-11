package com.whl.store.mapper;
import com.whl.store.entity.SysPermissions;
import com.whl.store.entity.SysRole;
import com.whl.store.entity.UserInfo;
import com.whl.store.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {

    /**
     * 根据用户名查询用户对象
     * @param username
     * @return 返回用户对象
     */
    UserInfo findUserByUsername(String username);

    /**
     * 根据用户UID查询用户信息
     * @param uid
     * @return
     */
    UserInfo findUserByUid(Integer uid);

    /**
     * 根据用户UID查询角色对应的RID
     * @param uid
     * @return 返回对应的角色RID
     */
    Integer findUserRoleIdByUid(Integer uid);

    /**
     * 根据角色ID查询信息
     * @param rid
     * @return
     */
    SysRole findRoleByRid(Integer rid);

    /**
     * 根据角色RID查询对应的权限PID列表
     * @param rid
     * @return 返回权限PID列表
     */
    List<Integer> findRolePermissionsIdByRid(Integer rid);

    /**
     * 根据权限PID列表查询对应的权限
     * @param pids
     * @return 返回权限列表
     */
    List<String> findPermissionsNameByPids(List pids);

    /**
     * 添加用户
     * @param User
     * @return 返回影响行数
     */
    Integer addUser(UserInfo User);

    /**
     * 添加角色
     * @param role
     * @return 返回影响行数
     */
    Integer addRole(SysRole role);

    /**
     * 添加用户和角色关联关系
     * @param uid 用户uid
     * @param rid 角色rid
     * @return 返回影响行数
     */
    Integer addUserAndRole(Integer uid,Integer rid);

    /**
     * 添加权限
     * @param permissions
     * @return 返回影响行数
     */
    Integer addPermissions(SysPermissions permissions);

    /**
     * 添加角色和权限关联关系
     * @param rid 角色ID
     * @param pid 权限ID
     * @return 返回影响行数
     */
    Integer addRoleAndPermissions(Integer rid,Integer pid);


    /**
     * 用户联合查询用户名和角色名
     * @return
     */
    List<UserRole> findAllUserRole();

    /**
     * 删除用户关联的角色
     * @param uids
     * @return
     */
    Integer delUserRoleByUid(@Param("uids") int[] uids);

    /**
     * 删除用户
     * @param uids
     * @return
     */
    Integer delUserByUid(@Param("uids")int[] uids);

    /**
     * 根据用户名修复用户状态：启动、禁用
     * @param username
     * @return
     */
    Integer updateUserStatusByUsername(String username,String status);

    /**
     * 根据用户名修复用户密码
     * @param username
     * @return
     */
    Integer updateUserPasswordByUsername(String username,String password);

    /**
     * 根据用户uid
     * @param uid
     * @param rid
     * @return
     */
    Integer updateUserRoleByUid(Integer uid,Integer rid);
}
