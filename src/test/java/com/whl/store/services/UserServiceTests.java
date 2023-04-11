package com.whl.store.services;

import com.whl.store.entity.KsLeak;
import com.whl.store.entity.PageData;
import com.whl.store.entity.User;
import com.whl.store.mapper.KsleakMapper;
import com.whl.store.mapper.UserMapper;
import com.whl.store.services.ex.ServicesException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {

    @Autowired
    private IUserService iUserService;

    @Autowired(required = false)
    private IKsleakService iKsleakService;

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private KsleakMapper ksleakMapper;


    @Test
    public void selectksleakpagetest(){
        PageData<KsLeak> pageData = iKsleakService.selectKsleakPageService(1,5);
        System.out.println(pageData.getRows().toString());
    }

    @Test
    public void updateksleakById(){
        KsLeak ksLeak = new KsLeak();
        ksLeak.setCve("CVE-2022-1234");
        ksLeak.setThreatLevel("低");
        ksLeak.setId(2);
        iKsleakService.updateKsleakById(ksLeak);
    }

    @Test
    public void reg(){
        try {
            User users = new User();
            users.setUsername("opt123");
            users.setPassword("123456");
            iUserService.reg(users);
            System.out.println("ok");
        } catch (ServicesException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void login(){
        User user = iUserService.login("opt123","123456");
        System.out.println(user);
    }

    @Test
    public void updatePasswordByUid(){
        userMapper.updatePasswordByUid(31,"123456","admin",new Date());
    }

    @Test
    public void findByUid(){
        User user = userMapper.findByUid(32);
        System.out.println(user);
    }

    @Test
    public void changePassword(){
        iUserService.changePassword(32,"opt123","123456","654321");
    }

    @Test
    public void updateInfoByUid(){
        User user = new User();
        user.setUid(32);
        user.setPhone("15995610218");
        user.setEmail("123@qq.com");
        user.setGender(1);
        userMapper.updateInfoByUid(user);
    }

    @Test
    public void getByUid(){
        User user = iUserService.getByUid(32);
        System.out.println(user);
    }

    @Test
    public void changeInfo(){
        User user = new User();
        user.setPhone("111111");
        user.setGender(2);
        user.setEmail("qwe@qq.com");
        iUserService.changeInfo(32,"admin",user);
    }

    @Test
    public void updateAvatarByUid(){
        userMapper.updateAvatarByUid(32,"/update/opt.png","opt123",new Date());

    }

    @Test
    public void changeAvatar(){
        iUserService.changeAvatar(32,"/upload/123123.png","opt123");
    }



}
