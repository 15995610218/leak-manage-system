package com.whl.store.filter;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whl.store.shiro.JwtToken;
import com.whl.store.util.JsonResult;
import com.whl.store.util.jwt.JwtUtils;
import com.whl.store.util.redis.RedisUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends BasicHttpAuthenticationFilter {

    /**
     * 判断是否允许通过
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        System.out.println("isAccessAllowed方法");
        try{
            return executeLogin(request,response);
        }catch (Exception e){
            System.out.println("isAccessAllowed执行的错误,并返回false；");
            responseError(response,"shiro authentication fail");
            return false;
        }
    }

    /**
     * 判断是否需要登陆
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        System.out.println("isLoginAttempt方法");
        String token = ((HttpServletRequest)request).getHeader("token");
        if (token != null){
            return true;
        }
        return false;
    }

    /**
     * 创建shiro token
     * @param request
     * @param response
     * @return
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        System.out.println("createToken方法");
        String jwtToken = ((HttpServletRequest) request).getHeader("token");
        if (jwtToken != null){
            return new JwtToken(jwtToken);
        }
        return null;
    }

    /**
     * isAccessAllowed为false时调用，验证失败,返回统一错误
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("onAccessDenied方法");
        this.sendChallenge(request,response);
        responseError(response,"token verify fail");
        return false;
    }

    /**
     * shiro验证成功调用
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("onLoginSuccess方法");
        String jwttoken = (String) token.getPrincipal();
        if (jwttoken != null){
            try{
                if (JwtUtils.verify(jwttoken)){
                    String account = JwtUtils.getAccount(jwttoken);
                    Long currentTime = JwtUtils.getCurrentTime(jwttoken);
                    if (RedisUtils.hasKey(account)){
                        Long currentTimeMillisRedis = (Long) RedisUtils.get(account);
                        if (currentTimeMillisRedis.equals(currentTime)){
                            return true;
                        }
                    }
                }
                return false;
            }catch (Exception e){
                Throwable throwable = e.getCause();
                System.out.println("token验证:"+e.getClass());
                if (e instanceof TokenExpiredException){
                    System.out.println("TokenExpiredException");
                    if (refreshToken(request,response)){
                        return true;
                    }else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 拦截器的前置方法，此处进行跨域处理
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-Control-Allow-Origin",httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods","GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers",httpServletRequest.getHeader("Access-Control-Resquest-Headers"));
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())){
            httpServletResponse.setStatus(HttpStatus.OK.value());
        }
        if (!isLoginAttempt(request,response)){
            responseError(httpServletResponse,"no token");
            return false;
        }
        return super.preHandle(request,response);
    }

    /**
     * 刷新AccessToken，进行判断RefreshToken是否过期，未过期就返回新的AccessToken且继续正常访问
     * @param request
     * @param response
     * @return
     */
    private boolean refreshToken(ServletRequest request,ServletResponse response){
        String token = ((HttpServletRequest)request).getHeader("token");
        String account = JwtUtils.getAccount(token);
        Long currentTime = JwtUtils.getCurrentTime(token);
        //判断redis中RefreshToken是否存在
        if (RedisUtils.hasKey(account)){
            // Redis中RefreshToken还存在，获取RefreshToken的时间戳
            Long currentTimeMillisRedis = (Long) RedisUtils.get(account);
            // 获取当前AccessToken中的时间戳，与RefreshToken的时间戳对比，如果当前时间戳一致，进行AccessToken刷新
            if (currentTimeMillisRedis.equals(currentTime)){
                // 获取当前最新时间戳
                Long currentTimeMillis = System.currentTimeMillis();
                RedisUtils.set(account,currentTimeMillis,JwtUtils.REFRESH_EXPIRE_TIME);
                // 刷新AccessToken，设置时间戳为当前最新时间戳
                token = JwtUtils.sign(account,currentTimeMillis);
                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                httpServletResponse.setHeader("Authorization",token);
                httpServletResponse.setHeader("Access-Control-Expose-Headers","Authorization");
                return true;
            }
        }
        return false;
    }

    /**
     * 返回统一错误
     * @param response
     * @param msg
     */
    private void responseError(ServletResponse response,String msg){
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setStatus(401);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        try{
            String errj = new ObjectMapper().writeValueAsString(new JsonResult<>(401,msg));
            httpServletResponse.getWriter().append(errj);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
