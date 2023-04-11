package com.whl.store.util;

import com.whl.store.services.ex.*;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class AllException {
    public static final int ok = 200;
    @ExceptionHandler
    public JsonResult<Void> exceptiona(Exception ex) {
        JsonResult<Void> result = new JsonResult<>(ex);
        if(ex instanceof AuthenticationException) {
            result.setState(401);
            result.setMessage("您好，认证失败，请重新登陆！");
        }
        else if(ex instanceof UnauthorizedException) {
            result.setMessage("您好，现在的级别还不够，无法删除；继续加油吧！");
            result.setState(402);
        }
        else if (ex instanceof LeakDeleteException){
            result.setState(501);
            result.setMessage("数据删除失败!");
        }
        else if (ex instanceof UserNotPermissionsException){
            result.setState(502);
            result.setMessage("用户名或密码错误!");
        }else if (ex instanceof UserRoleDeleteException){
            result.setState(503);
            result.setMessage(ex.getMessage());
        }else if (ex instanceof UserDeleteExeception){
            result.setState(504);
            result.setMessage(ex.getMessage());
        }else if (ex instanceof UserUpdateExeception){
            result.setState(505);
            result.setMessage(ex.getMessage());
        }
        else if(ex instanceof Exception){
            result.setState(5000);
            result.setMessage("服务操作失败!");
        }
        return result;
    }
}
