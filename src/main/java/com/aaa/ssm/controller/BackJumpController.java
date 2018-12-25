package com.aaa.ssm.controller;

import com.aaa.ssm.entity.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

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
    public String toIndex(HttpSession session, Model model){
        String username=(String) session.getAttribute("username");
        Admin admin=(Admin) session.getAttribute("user");
        model.addAttribute("username",username);
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
    @RequestMapping("/borrow")
    public String biaode(){
        return "houtai/biaodi/borrowSubmit";
    }
    /**
     * 跳转后台招标详情页面
     * @return
     */
    @RequestMapping("/loan")
    public String loan(){
        return "houtai/biaodi/loan";
    }
    /**
     * 跳转后台满标页面
     * @return
     */
    @RequestMapping("/fscheck")
    public String fscheck(){
        return "houtai/biaodi/floodmark";
    }
    /**
     * 跳转后台登录页面
     * @return
     */
    @RequestMapping("/backlogin")
    public String backLogin(HttpSession session){
        session.setAttribute("username",null);
        session.setAttribute("user",null);
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
    /**
     * 跳转后台部门列表页面
     * @return
     */
    @RequestMapping("/dept")
    public String dept(){
        return "houtai/dept/list";
    }
    /**
     * 跳转后台员工列表页面
     * @return
     */
    @RequestMapping("/emp")
    public String emp(){
        return "houtai/emp/list";
    }

}
