package com.whl.store.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 定义一个登录入口拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取session对象的uid
       Object obj = request.getSession().getAttribute("uid");
       //判断uid是否存在，
        if (obj == null){
            //如果不存在直接重定向login.html
            response.sendRedirect("/web/login.html");
            //结束后续的调用
            return false;
        }

        return true;
    }
}
