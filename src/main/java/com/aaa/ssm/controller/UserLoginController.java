package com.aaa.ssm.controller;

import com.aaa.ssm.entity.UserRegister;
import com.aaa.ssm.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * className:UserLoginController
 * discription:用户登录
 * author:fhm
 * createTime:2018-12-08 19:32
 */
@Controller
@RequestMapping("/user")
public class UserLoginController {

    //依赖注入service层
    @Autowired
    private UserLoginService userLoginService;

    /***
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public Object userLogin(String userName, String password, Model model, HttpSession session){
        String emailPattern="^([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$";
        String phonePattern="^1[3|5|7|8][0-9]{9}$";
        Map map = new HashMap();
        if(userName.matches(emailPattern)){
            //邮箱登录
            map.put("email",userName);
            UserRegister user = userLoginService.getUser(map);
            if(user==null){//邮箱不存在
                model.addAttribute("msg","该邮箱不存在！");
                return "qiantai/login";
            }
            //邮箱存在，判断密码
            if(!password.equals(user.getUpwd())){
                //密码错误
                model.addAttribute("msg","密码错误！");
                return "qiantai/login";
            }else {
                //密码正确
                session.setAttribute("userName",user.getUname());
                session.setAttribute("email",user.getEmail());
                session.setAttribute("phone",user.getPhone());
                session.setAttribute("user",user);
                return "redirect:jump/index";
            }
        }else if(userName.matches(phonePattern)){
            //手机号登录
            map.put("phone",userName);
            UserRegister user=userLoginService.getUser(map);
            if(user==null){//手机号不存在
                model.addAttribute("msg","该手机号不存在！");
                return "qiantai/login";
            }
            //手机号存在，判断密码
            if(!password.equals(user.getUpwd())){
                //密码错误
                model.addAttribute("msg","密码错误！");
                return "qiantai/login";
            }else {
                //密码正确
                session.setAttribute("userName",user.getUname());
                session.setAttribute("phone",user.getPhone());
                session.setAttribute("email",user.getEmail());
                session.setAttribute("user",user);
                return "qiantai/index";
            }
        }else{
           //用户名登录
            map.put("uname",userName);
            UserRegister user=userLoginService.getUser(map);
            if(user==null){//用户名不存在
                model.addAttribute("msg","该用户名不存在！");
                return "qiantai/login";
            }
            //用户名存在，判断密码
            if(!password.equals(user.getUpwd())){
                //密码错误
                model.addAttribute("msg","密码错误！");
                return "qiantai/login";
            }else {
                //密码正确
                session.setAttribute("userName",user.getUname());
                session.setAttribute("phone",user.getPhone());
                session.setAttribute("email",user.getEmail());
                session.setAttribute("user",user);
                return "redirect:/jump/index";
            }
        }
    }
}
