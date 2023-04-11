//package com.whl.store.shiro;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
//import org.apache.shiro.mgt.DefaultSubjectDAO;
//import org.apache.shiro.spring.LifecycleBeanPostProcessor;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//
//import javax.servlet.Filter;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//@Configuration
//@Slf4j
//public class ShiroConfig {
//    /**
//     * 先经过token过滤器，如果检测到请求头存在 token，则用 token 去 login，接着走 Realm 去验证
//     */
//
//    @Bean
//    public ShiroFilterFactoryBean factory(@Qualifier("securityManager")DefaultWebSecurityManager securityManager) {
//        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
//        factoryBean.setSecurityManager(securityManager);
//        Map<String, Filter> filterMap = new LinkedHashMap<>();
//        // 添加自己的过滤器并且取名为jwt
//        filterMap.put("jwt", new CostemFilter());
//        factoryBean.setFilters(filterMap);
//        // 设置无权限时跳转的 url;
//        //factoryBean.setUnauthorizedUrl("/unauthorized/relogin");
//        Map<String, String> filterRuleMap = new HashMap<>();
//        //添加不需要拦截的url
//        filterRuleMap.put("/bocs/noauth","anon");
//        //登录不需要拦截
//        filterRuleMap.put("/bocs/login","anon");
//        //处理swagger不能访问问题
////        filterRuleMap.put("/swagger-ui.html", "anon");
////        filterRuleMap.put("/swagger**/**", "anon");
////        filterRuleMap.put("/webjars/**", "anon");
////        filterRuleMap.put("/v2/**", "anon");
//        //这个需要放到最下面
//        // 所有请求通过我们自己的JWT Filter
//        filterRuleMap.put("/**", "jwt");
//        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
//        return factoryBean;
//    }
//    /**
//     * 注入 securityManager
//     */
//    @Bean(name = "securityManager")
//    public DefaultWebSecurityManager securityManager(ShiroRealm shiroRealm) {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        //设置自定义realm.
//        securityManager.setRealm(shiroRealm);
//        //关闭shiro自带的session
//        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
//        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
//        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
//        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
//        securityManager.setSubjectDAO(subjectDAO);
//        return securityManager;
//    }
//    /**
//     * 自定义的realm
//     * @return
//     */
//    @Bean
//    public ShiroRealm shiroRealm(){
//        return new ShiroRealm();
//    }
//    /**
//     * 添加注解支持
//     */
//    @Bean
//    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
//        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
//        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
//        return defaultAdvisorAutoProxyCreator;
//    }
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
//        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
//        advisor.setSecurityManager(securityManager);
//        return advisor;
//    }
//    @Bean
//    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }
//
//}