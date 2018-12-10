package com.aaa.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * className:jumpController
 * discription:前台跳转
 * author:yb
 * createTime:2018-12-07 17:24
 */
@Controller
@RequestMapping("/jump")
public class JumpController {
    @RequestMapping("/index")
    public String jumpIndex(){
        return "qiantai/index";
    }
    @RequestMapping("/login")
    public String login(HttpSession session){
        session.setAttribute("userName",null);
        session.setAttribute("user",null);
        return "qiantai/login";
    }
    @RequestMapping("/register")
    public String register(){
        return "qiantai/register";
    }
    @RequestMapping("/register1")
    public String register1(){
        return "qiantai/register1";
    }
}
