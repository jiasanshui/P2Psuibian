package com.aaa.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * className:BackJumpController
 * discription:后台跳转
 * author:fhm
 * createTime:2018-12-10 17:19
 */
@Controller
@RequestMapping("/backjump")
public class BackJumpController {

    @RequestMapping("index")
    public String toIndex(){
        return "houtai/index";
    }

    /**
     * 跳转到后台用户列表
     * @return
     */
    @RequestMapping("/userList")
    public String userList(){
        return "houtai/user/list";
    }

    /**
     * 跳转后台招标页面
     * @return
     */
    @RequestMapping("/tender")
    public String tender(){
        return "houtai/user/tender";
    }
}
