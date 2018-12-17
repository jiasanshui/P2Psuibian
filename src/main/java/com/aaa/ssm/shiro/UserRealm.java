package com.aaa.ssm.shiro;/**
 * className:UserRealm
 * discription:
 * author:jiasanshui
 * createTime:2018-12-06 18:45
 */

import com.aaa.ssm.entity.Admin;
import com.aaa.ssm.entity.User;
import com.aaa.ssm.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *className:UserRealm
 *discription:
 *author:jiasanshui
 *createTime:2018-12-06 18:45
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    
    //授权逻辑
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权逻辑");
        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //添加资源的授权字符串
        //info.addStringPermission("user:add");
        //到数据库当前用户的授权字符串
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        Admin admin = userService.getUserById(user.getUserId());
        //info.addStringPermission(admin.getPerms());
        return info;
    }

    //登录逻辑
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证逻辑");
        //假设数据库用户名和密码
        /*String username = "scott";
        String password = "admin";*/

        //shiro判断逻辑，判断用户名和密码
        //1、判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        Admin user = userService.getUserByuserName(token.getUsername());
        if(user==null){
            //用户名不存在
            return null; //shiro底层会抛出UnknownAccountException
        }
        //2、判断密码
        return new SimpleAuthenticationInfo(user,user.getApassword(),"");
    }
}
