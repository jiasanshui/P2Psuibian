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
    @RequestMapping("/index")
    public String jumpIndex(){
        return "qiantai/index";
    }
    @RequestMapping("/login")
    public String login(){
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
