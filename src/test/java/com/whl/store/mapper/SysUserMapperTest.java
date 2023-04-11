package com.whl.store.mapper;

import com.whl.store.entity.SysPermissions;
import com.whl.store.entity.UserInfo;
import com.whl.store.entity.UserRole;
import com.whl.store.services.SysUserService;
import com.whl.store.util.JwtUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SysUserMapperTest {

    @Autowired(required = false)
    private SysUserService sysUserService;

    @Autowired(required = false)
    private SysUserMapper sysUserMapper;

    /**
     * 测试业务层的用户添加功能
     */
    @Test
    public void addUser(){
        UserInfo user = new UserInfo();
        user.setStatus("1");
        user.setPassword("Aa123456!");
        user.setUsername("admin");
        sysUserService.addUser(user);
    }

    /**
     * 添加用户和角色关联
     */
    @Test
    public void addUserRole(){
        sysUserMapper.addUserAndRole(2,1);
    }

    /**
     * 测试业务层的用户名从数据库中查询功能
     */
    @Test
    public void findUserByUsernameTest(){
        UserInfo user = sysUserService.findUserByUsername("zhangsan");
        System.out.println(user.toString());
    }

    @Test
    public void findRolePermissionsByRidTest(){
        List<Integer> ints = sysUserMapper.findRolePermissionsIdByRid(3);
        System.out.println(ints.toString());
        List<String> permissionsNames = sysUserMapper.findPermissionsNameByPids(ints);
        System.out.println(permissionsNames.toString());
    }

    @Test
    public void testtest(){
        String token = JwtUtil.createToken("zhangsan","1");
        String username = JwtUtil.getUsername(token);
        System.out.println(username);
    }
    /**
     * 添加权限
     */
    @Test
    public void addPermissionstest(){
        SysPermissions sysPermissions = new SysPermissions();
        sysPermissions.setName("auth:admin");
        sysPermissions.setTitle("超级管理员");
        sysUserMapper.addPermissions(sysPermissions);
    }

    @Test
    public void addRoleAndPermissionstest(){
        sysUserMapper.addRoleAndPermissions(1,17);
    }

    @Test
    public void findAllUserRoletext(){
        List<UserRole> list = sysUserMapper.findAllUserRole();
        System.out.println(list.toString());
    }

    @Test
    public void updateUserPasswordService(){
        sysUserService.updateUserPasswordService("admin","Aa123456!");
    }

}
