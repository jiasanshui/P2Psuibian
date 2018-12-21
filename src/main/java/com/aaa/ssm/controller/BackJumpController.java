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
     * 跳转到后台用户信息审核列表
     * @return
     */
    @RequestMapping("/userList")
    public String userList(){
        return "houtai/user/list";
    }

    /**
     * 跳转后台借款标的审核页面
     * @return
     */
    @RequestMapping("/biaode")
    public String biaode(){
        return "houtai/user/biaode";
    }
    /**
     * 跳转后台满标页面
     * @return
     */
    @RequestMapping("/fscheck")
    public String fscheck(){
        return "houtai/user/fscheck";
    }
    /**
     * 跳转后台登录页面
     * @return
     */
    @RequestMapping("/backlogin")
    public String backLogin(){
        return "houtai/backLogin";
    }
    /**
     * 跳转权限菜单更新和删除页面（power）
     * @return
     */
    @RequestMapping("/powermenu")
    public String powermenu(){
        return "houtai/power/powermenu";
    }
    /**
     * 跳转后台权限菜单添加页面(power)
     * @return
     */
    @RequestMapping("/menuadd")
    public String powermenuAdd(){
        return "houtai/power/add";
    }

    /**
     * 跳转后台角色列表页面
     * @return
     */
    @RequestMapping("/role")
    public String rolelist(){
        return "houtai/role/list";
    }

    /**
     * 跳转后台权限菜单管理页面(easyui)
     * @return
     */
    @RequestMapping("/permission")
    public String permission(){
        return "houtai/permission/power";
    }

    /**
     * 跳转后台权限菜单管理页面(vue+element ui)
     * @return
     */
    @RequestMapping("/powerList")
    public String powerList(){
        return "houtai/power/powerList";
    }
}
