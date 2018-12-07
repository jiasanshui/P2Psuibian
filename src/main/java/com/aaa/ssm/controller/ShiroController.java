package com.aaa.ssm.controller;/**
 * className:TestController
 * discription:
 * author:jiasanshui
 * createTime:2018-12-06 19:04
 */

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 *className:TestController
 *discription:
 *author:jiasanshui
 *createTime:2018-12-06 19:04
 */
@Controller
public class ShiroController {

    /**
     * 登录逻辑处理
     * @return
     */
    @RequestMapping("login")
    public String login(String username, String password, Model model, HttpSession session){
        /**
         * 使用shiro认证操作
         */
        //1、获取subject
        Subject subject = SecurityUtils.getSubject();
        //2、封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        //3、执行登录方法
        try {
            subject.login(token);
            //没有异常，则登录成功
            //跳转到test.html
            session.setAttribute("username",token.getUsername());
            return "forward:/personal";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            //登录失败：用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "forward:/toLogin";
        } catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "forward:/toLogin";
        }
    }
}
