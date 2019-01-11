package com.aaa.ssm.controller;/**
 * className:TestController
 * discription:
 * author:jiasanshui
 * createTime:2018-12-06 19:04
 */

import com.aaa.ssm.entity.Admin;
import com.aaa.ssm.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/usershiro")
public class UserShiroController {

    @Autowired
    private UserService userService;
    /**
     * 登录逻辑处理
     * @return
     */
    @RequestMapping("login")
    public String login(String name, String password, Model model, HttpSession session){
        System.out.println("用户名："+name+"，密码："+password);

        /**
         * 使用shiro认证操作
         */
        //1、获取subject
        Subject subject = SecurityUtils.getSubject();
        //2、封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);
        //3、执行登录方法
        try {
            subject.login(token);
            //没有异常，则登录成功，跳转到主页
            session.setAttribute("username",token.getUsername());
            Admin user = userService.getUserByuserName(name);
            session.setAttribute("admin",user);
            return "redirect:/backjump/index";
        } catch (UnknownAccountException e) {
            //e.printStackTrace();
            //登录失败：用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "forward:/backjump/backlogin";
        } catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "forward:/backjump/backlogin";
        }
    }
}
