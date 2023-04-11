package com.whl.store.controller;

import com.whl.store.controller.ex.*;
import com.whl.store.entity.User;
import com.whl.store.services.ex.*;
import com.whl.store.util.JsonResult;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;


public class BaseController {
    /**
     * author：weihailong
     * 方法拦截错误，统一返回
     */
    public static final int ok = 200;
    @ExceptionHandler({ServicesException.class,FileUploadException.class})
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result = new JsonResult<>(e);
        if (e instanceof UsernameDuplicatedException){
            result.setState(4000);
            result.setMessage(e.getMessage());
        } else if (e instanceof UserNotFoundException){
            result.setState(5001);
            result.setMessage(e.getMessage());
        } else if (e instanceof PasswordNotMatchException){
            result.setState(5002);
            result.setMessage(e.getMessage());
            System.out.println(result);
        }else if (e instanceof InsertException){
            result.setState(5000);
            result.setMessage(e.getMessage());
            System.out.println(result);
        }else if (e instanceof UpdateException){
            result.setState(5003);
            result.setMessage(e.getMessage());
        }else if (e instanceof FileEmptyException){
            result.setState(6000);
            result.setMessage(e.getMessage());
        }else if (e instanceof FileTypeException){
            result.setState(6001);
            result.setMessage(e.getMessage());
        }else if (e instanceof FileUploadIOException){
            result.setState(6002);
            result.setMessage(e.getMessage());
        }else if (e instanceof FileStateException){
            result.setState(6003);
            result.setMessage(e.getMessage());
        }else if (e instanceof FileSizeException){
            result.setState(6004);
            result.setMessage(e.getMessage());
        }else if (e instanceof LeakSelectException) {
            result.setState(7001);
            result.setMessage(e.getMessage());
        }else if (e instanceof LeakInsertException){
            result.setState(7002);
            result.setMessage(e.getMessage());
        }else if (e instanceof LeakUpdateException){
            result.setState(7003);
            result.setMessage(e.getMessage());
        }else if (e instanceof LeakDeleteException){
            result.setState(7004);
            result.setMessage(e.getMessage());
        }else if (e instanceof LeakImportExeclException){
            result.setState(7005);
            result.setMessage(e.getMessage());
            System.out.println(result);
        }else if (e instanceof LeakSearchException){
            result.setState(7006);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    /**
     * 父类中定义的获取session数据
     * 获取用户uid的值
     * @param session
     * @return
     */
    protected final Integer getuidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 获取用户username的值
     * @param session
     * @return
     */
    protected final String getusernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }
}
