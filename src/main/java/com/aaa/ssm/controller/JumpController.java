package com.aaa.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * className:jumpController
 * discription:
 * author:yb
 * createTime:2018-12-07 17:24
 */
@Controller
@RequestMapping("/jump")
public class JumpController {
    /**
     * 跳转到前台首页
     * @return
     */
    @RequestMapping("/index")
    public String jumpIndex(){
        return "qiantai/index";
    }

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return "qiantai/login";
    }

    /**
     * 跳转到注册页面
     * @return
     */
    @RequestMapping("/register")
    public String register(){
        return "qiantai/register";
    }

    /**
     * 跳转到注册成功页面
     * @return
     */
    @RequestMapping("/register1")
    public String register1(){
        return "qiantai/register1";
    }
    /**
     * 跳转到我要投资页面
     * @return
     */
    @RequestMapping("/list")
    public String list(){
        return "qiantai/list";
    }
    /**
     * 跳转到我要借款页面
     * @return
     */
    @RequestMapping("/borrow")
    public String borrow(){
        return "qiantai/borrow";
    }



}
