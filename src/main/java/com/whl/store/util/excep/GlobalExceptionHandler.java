package com.whl.store.util.excep;

import com.whl.store.util.JsonResult;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * 捕捉所有Shiro异常
     * @param e
     * @return
     */
    @ExceptionHandler(ShiroException.class)
    public JsonResult<Void> handle401(ShiroException e){
        JsonResult<Void> result = new JsonResult<>(e);
        result.setMessage("无权访问:"+e.getMessage());
        result.setState(401);
        return result;
    }

    /**
     * 单独捕捉Shiro(UnauthorizedException)异常 该异常为访问有权限管控的请求而该用户没有所需权限所抛出的异常
     * @param e
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    public JsonResult<Void> handle401(UnauthorizedException e){
        JsonResult<Void> result = new JsonResult<>(e);
        result.setMessage("无权访问(Unauthorized):当前Subject没有此请求所需权限"+e.getMessage());
        result.setState(401);
        return result;
    }

    /**
     * 单独捕捉Shiro(UnauthenticatedException)异常
     * @param e
     * @return
     */
    @ExceptionHandler(UnauthenticatedException.class)
    public JsonResult<Void> handle401(UnauthenticatedException e){
        JsonResult<Void> result = new JsonResult<>(e);
        result.setState(401);
        result.setMessage("无权访问(Unauthorized):当前Subject是匿名Subject，请先登录");
        return result;
    }

    /**
     * 捕捉404异常
     * @param e
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public JsonResult<Void> handle404(NoHandlerFoundException e){
        JsonResult<Void> result = new JsonResult<>(e);
        result.setState(404);
        result.setMessage("无此资源");
        return result;
    }

    /**
     * 捕获所有错误异常
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public JsonResult<Void> globalException(HttpServletRequest request,Throwable ex){
        JsonResult<Void> result = new JsonResult<>(ex);
        result.setState(500);
        result.setMessage(ex.toString()+":"+ex.getMessage());
        return result;
    }

    /**
     * 获取状态码
     * @param request
     * @return
     */
    private HttpStatus getStatus(HttpServletRequest request){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
