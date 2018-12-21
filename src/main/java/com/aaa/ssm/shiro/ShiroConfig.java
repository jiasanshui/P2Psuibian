package com.aaa.ssm.shiro;/**
 * className:ShiroConfig
 * discription:
 * author:jiasanshui
 * createTime:2018-12-06 18:44
 */

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *className:ShiroConfig
 *discription:
 *author:jiasanshui
 *createTime:2018-12-06 18:44
 */
@Configuration
public class ShiroConfig {


    /**
     * 创建ShiroFilterFactoryBean
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //添加shiro内置过滤器
        /**
         * anon:无需认证
         * authc：必须认证
         * user：如果使用remeberMe可以直接访问
         * perms：该资源必须资源权限才能访问
         * role：该资源必须角色权限才能访问
         */
        Map<String,String> filterMap = new LinkedHashMap<String,String>();
        /*filterMap.put("/add","authc");
        filterMap.put("/update","authc");*/
        /*filterMap.put("/test","anon");
        filterMap.put("/login","anon");*/
        //filterMap.put("/**","anon");
        //授权过滤器
        //当授权拦截后，自动跳转到授权逻辑
        //filterMap.put("/add","perms[user:add]");
        //filterMap.put("/update","perms[user:update]");

        //拦截
        filterMap.put("/backjump/*","authc");

        //修改调整的登录页面
        shiroFilterFactoryBean.setLoginUrl("/backjump/backlogin");
        //设置未授权提示页面
        //shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联Realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建Realm
     * @return
     */
    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }

    /**
     * 配置ShiroDialect,用于thymleaf和shiro的标签配置
     */
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
