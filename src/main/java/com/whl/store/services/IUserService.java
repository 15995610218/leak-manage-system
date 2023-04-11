package com.whl.store.services;

import com.whl.store.entity.KsLeak;
import com.whl.store.entity.User;

/**
 * 业务层 用户模块接口
 */
public interface IUserService {

    /**
     * 用户注册接口方方法
     * @param user  用户注册数据的对象
     */
    public void reg(User user);

    /**
     * 用户登陆功能
     * @param username  用户名
     * @param password  用户密码
     * @return  当前匹配的用户数据，如果不匹配返回null值
     */
    User login(String username,String password);

    /**
     * 用户修改密码功能
     * @param uid
     * @param username
     * @param oldpassword
     * @param newpassword
     */
    void changePassword(Integer uid,String username,String oldpassword,String newpassword);

    /**
     * 根据用户的ID查询用户信息
     * @param uid 用户ID数据
     * @return  返回user对象
     */
    User getByUid(Integer uid);

    /**
     *更新用户的信息
     * @param uid
     * @param username
     * @param user
     */
    void changeInfo(Integer uid,String username,User user);

    /**
     * 更新用户头像的功能
     * @param uid   用户ID
     * @param avatar    用户头像的路径
     * @param username  更新用户名
     */
    void changeAvatar(Integer uid,String avatar,String username);

}
