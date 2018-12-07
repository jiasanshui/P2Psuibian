package com.aaa.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * className:helloController
 * discription:
 * author:jiasanshui
 * createTime:2018-11-29 19:18
 */
@Controller
public class helloController {

    @RequestMapping("hello")
    public Object demo(){
        return "houtai/index";
    }
}
