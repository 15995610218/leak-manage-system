package com.whl.store.util;

import org.springframework.util.DigestUtils;

public class PasswordResult {

    private PasswordResult(){}

    public static String getMD5Password(String password,String salt){
        for (int i = 0; i<3;i++){
            password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes());
        }
        return password;
    }
}
