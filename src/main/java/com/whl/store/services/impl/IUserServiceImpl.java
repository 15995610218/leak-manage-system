package com.whl.store.services.impl;

import com.whl.store.entity.User;
import com.whl.store.mapper.UserMapper;
import com.whl.store.services.IUserService;
import com.whl.store.services.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.jws.soap.SOAPBinding;
import java.util.Date;
import java.util.UUID;

/**
 * 业务层 用户模块接口的实现类
 */
@Service
public class IUserServiceImpl implements IUserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    /**
     * 用户注册实现方法
     * @param user  用户注册数据的对象
     */
    @Override
    public void reg(User user) {
        String username = user.getUsername();
        User result = userMapper.findByUsername(username);
        if (result != null){
            //抛出异常
            throw new UsernameDuplicatedException("用户名已存在");
        }
        /**
         * 将密码加盐值后加密存储数据库
         */
        //获取用户的密码
        String oldPassword = user.getPassword();
        //生成一个随机的盐值
        String salt = UUID.randomUUID().toString().toUpperCase();
        //将盐值存储到用户的表中，提供解密使用
        user.setSalt(salt);
        //调用md5将密码hash三次
        String md5Password = getMD5Password(oldPassword,salt).toUpperCase();
        //将加密后的密码封装user对象中
        user.setPassword(md5Password);

        /**
         * 补全数据：is_delete created_user created_time modified_user modified_time
         */
        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        Integer rows = userMapper.insert(user);
        if (rows != 1){
            throw new InsertException("用户在注册时遇到未知的错误");
        }
    }

    private String getMD5Password(String password,String salt){
        for (int i = 0; i<3;i++){
            password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes());
        }
        return password;
    }

    /**
     * 用户登陆功能实现父类的方法
     * @param username  用户名
     * @param password  用户密码
     * @return
     */
    @Override
    public User login(String username, String password) {
        //查询用户名数据
        User result = userMapper.findByUsername(username);
        //判断用户名是否存在，如果不存在抛出异常
        if (result == null){
            throw new UserNotFoundException("用户名不存在");
        }
        //如果用户名存在，继续判断用户密码是否正确
        //1.先获取数据库中的密码
        String oldPassword = result.getPassword();
        //2.和用户传递的密码比较
            //2.1先获取盐值：用户注册时的盐值
        String salt = result.getSalt();
            //2.2将用户传递的密码按照相同的md5算法的规则进行加密
        String newPassword = getMD5Password(password,salt).toUpperCase();
        //3.将密码进行比较,如果密码不相等，则抛出异常
        if (!newPassword.equals(oldPassword)){
            throw new PasswordNotMatchException("用户密码错误");
        }
        //继续判断用户is_delete是否为1，如果为1则抛出异常
        if (result.getIsDelete() == 1){
            throw new UserNotFoundException("用户名已删除");
        }
        //验证成功后，
        User user = new User();
        user.setUsername(result.getUsername());
        user.setUid(result.getUid());
        user.setAvatar(result.getAvatar());
        //将当前的用户数据返回
        return user;
    }

    /**
     * 业务层-用户修改密码功能实现类
     * @param uid
     * @param username
     * @param oldpassword
     * @param newpassword
     */
    @Override
    public void changePassword(Integer uid,String username,String oldpassword,String newpassword){
        //查询用户信息
        User result = userMapper.findByUid(uid);
        //判断查询结果
        if (result == null || result.getIsDelete() == 1){
            throw new UserNotFoundException("changepassword用户不存在");
        }
        //原始密码和数据库密码进行判断
        String oldMd5Password = getMD5Password(oldpassword,result.getSalt()).toUpperCase();
        if (!result.getPassword().equals(oldMd5Password)){
            throw new PasswordNotMatchException("changepassword原始密码不正确");
        }
        //将新的密码设置到数据库中，将密码加密后存储更新
        String newMd5Password = getMD5Password(newpassword,result.getSalt()).toUpperCase();
        Integer rows = userMapper.updatePasswordByUid(uid,newMd5Password,username,new Date());
        if (rows != 1){
            throw new UpdateException("changepassword用户修改密码更新遇到未知错误");
        }

    }

    @Override
    public User getByUid(Integer uid) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete()==1){
            throw new UserNotFoundException("getbyuid用户数据不存在");
        }
        User user = new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());
        return user;
    }

    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User result = userMapper.findByUid(uid);
        if (result == null||result.getIsDelete()==1){
            throw new UserNotFoundException("changeinfo用户数据不存在");
        }
        user.setUid(uid);
        user.setUsername(username);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        Integer rows = userMapper.updateInfoByUid(user);
        if (rows != 1){
            throw new UpdateException("changeinfo修改用户信息产生未知错误");
        }
    }

    @Override
    public void changeAvatar(Integer uid, String avatar, String username) {
        //先判断用户是否存在
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete().equals(1)){
            throw new UserNotFoundException("用户数据不存在");
        }
        //用户数据存在，开始更新用户头像
        Integer rows = userMapper.updateAvatarByUid(uid,avatar,username,new Date());
        if (rows != 1){
            throw new UpdateException("用户更新头像时遇到未知错误");
        }
    }
}
