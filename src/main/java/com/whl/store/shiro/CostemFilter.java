//package com.whl.store.shiro;
//import com.alibaba.fastjson.JSON;
//import com.whl.store.util.JsonResult;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shiro.authz.UnauthorizedException;
//import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.RequestMethod;
//import javax.security.sasl.AuthenticationException;
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//
//@Slf4j
//public class CostemFilter extends BasicHttpAuthenticationFilter {
//    private boolean allowOrigin = true;
//    //无惨构造方法
//    public CostemFilter(){}
//    //有惨构造
//    public CostemFilter(boolean allowOrigin){
//        this.allowOrigin = allowOrigin;
//    }
//    /**
//     * 如果有token,则进行token进行检查，无token直接放过
//     */
//    @SneakyThrows
//    @Override
//    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws UnauthorizedException {
//        try {
//            executeLogin(request,response);
//        }catch (Exception e){
//            responseError(response);
//        }
//        return true;
//    }
//    /**
//     * 判断用户是否想登陆
//     * 检测header头中是否包含token字段
//     */
//    @Override
//    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
//        //System.out.println("===isLoginAttempt===");
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        String token = httpServletRequest.getHeader("token");
//        return token != null;
//    }
//    /**
//     * 执行登陆操作
//     */
//    @Override
//    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
//        //System.out.println("===executeLogin===");
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        String token = httpServletRequest.getHeader("token");
//        JwtToken jwtToken = new JwtToken(token);
//        getSubject(request,response).login(jwtToken);
//        return true;
//    }
//    /**
//     * 对跨域提供帮助
//     */
//    @Override
//    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
//        //System.out.println("===preHandle===");
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//        httpServletResponse.setHeader("Access-control-Allow-Origin",httpServletRequest.getHeader("Origin"));
//        httpServletResponse.setHeader("Access-Control-Allow-Methods","GET,POST,OPTIONS,PUT,DELETE");
//        httpServletResponse.setHeader("Access-Control-Allow-Headers",httpServletRequest.getHeader("Access-Control-Request-Headers"));
//        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())){
//            httpServletResponse.setStatus(HttpStatus.OK.value());
//            return false;
//        }
//        return super.preHandle(request, response);
//    }
//    /**
//     * 非法请求返回401
//     */
//    private void responseError(ServletResponse response) throws AuthenticationException {
//        //System.out.println("===responseError===");
//        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
//        httpServletResponse.setCharacterEncoding("UTF-8");
//        httpServletResponse.setContentType("application/json; charset=utf-8");
//        try (ServletOutputStream out = httpServletResponse.getOutputStream()){
//            out.write(JSON.toJSONString(new JsonResult<String>(401,"认证失败，请重新登陆！")).getBytes("utf-8"));
//        } catch (IOException e) {
//            throw new AuthenticationException("直接返回Response信息出现IOException异常:" + e.getMessage());
//        }
//    }
//}
