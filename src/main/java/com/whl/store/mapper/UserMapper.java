package com.whl.store.mapper;

import com.whl.store.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface UserMapper {
    /**
     * 插入用户的数据
     * @param user
     * @return 返回影响的行数
     */
    Integer insert(User user);

    /**
     * 根据用户名来查询用户的数据
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 根据UID修改用户密码
     * @param uid
     * @return  如果修改成功，返回影响条数
     */
    Integer updatePasswordByUid(Integer uid,String password, String modifiedUser, Date modifiedTime);

    /**
     * 根据UID查询用户信息
     * @param uid
     * @return  如果查询到了，返回user对象数据，如果没有则返回null值
     */
    User findByUid(Integer uid);

    /**
     * 更新用户信息
     * @param user 用户的数据
     * @return  返回受影响的行数
     */
    Integer updateInfoByUid(User user);

    /**
     * 更新头像
     * @param uid
     * @param avatar
     * @param modifiedUser
     * @param modifiedTime
     * @return 返回影响行数
     */
    Integer updateAvatarByUid(Integer uid,String avatar,String modifiedUser,Date modifiedTime);
}
