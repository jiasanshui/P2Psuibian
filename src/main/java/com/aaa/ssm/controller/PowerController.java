package com.aaa.ssm.controller;

import com.aaa.ssm.entity.TbRole;
import com.aaa.ssm.entity.TreeNode;
import com.aaa.ssm.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * className:PowerController
 * discription:
 * author:fhm
 * createTime:2018-12-16 20:41
 */
@Controller
@RequestMapping("/power")
public class PowerController {
    //依赖注入
    @Autowired
    private PowerService powerService;

    /**
     * 权限树json数据(根据登陆不同，展示权限菜单不同)
     * @return
     */
    @ResponseBody
    @RequestMapping("/powerTree")
    public Object powerListTree(){
        List<TreeNode> list=powerService.getPowerList();
        return list;
    }

    /**
     * 根据登陆角色不同，获取不同的权限
     * @return
     */
    @ResponseBody //返回json格式数据
    @RequestMapping("/checktree")
    public Object getCheckTree(Integer roleid){
        return powerService.getCheckList(roleid);
    }

    /**
     *  给角色添加权限并保存到角色权限关联表
     * @param role
     * @return
     */
    @RequestMapping("/saverolepower")
    @ResponseBody
    public Object saverolepower(@RequestBody TbRole role){
        // System.out.println(role.getPowersid());
        return powerService.saveRolePower(role);
    }

    /**
     * 权限菜单添加方法(easyui)
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public Object add(@RequestParam Map map){
        int add = powerService.add(map);
        if (add>0)
            return add;
        else
            return 0 ;
    }

    /**
     * 权限菜单修改(easyui)
     * @param map
     */
    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestParam Map map){
        int update = powerService.update(map);
        if (update>0)
            return update;
        else
            return 0 ;
    }
    /**
     * 权限菜单删除(easyui)
     * @param id
     */
    @ResponseBody
    @RequestMapping("/del")
    public Object del(Integer id){
        int del = powerService.delete(id);
        if (del>0)
            return del;
        else
            return 0;
    }

    /**
     * 树菜单更新回显数据(不用)
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public Object getlist(@RequestBody Map map){
        Integer powerId=Integer.valueOf(map.get("powerId")+"");
        return powerService.getById(powerId);
    }

    /**
     * 跳转权限添加界面（easyui）
     * @return
     */
    @RequestMapping("/toAdd")
    public Object toadd(){
        return "houtai/permission/add";
    }

    /**
     * 去更新（easyui）
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/toUpdate")
    public Object toUpdate(Integer id,Model model){
        TreeNode power = powerService.getPowerById(id);
        model.addAttribute("power",power);
        return "houtai/permission/update";
    }

    /**
     * 获取所有的的权限（easyui）
     * @return
     */
    @ResponseBody
    @RequestMapping("/treeList")
    public Object gettreeList(){
        return powerService.gettreeList();
    }

    /**
     * 权限菜单添加方法(vue+element)
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/addMenu")
    public Object addMenu(@RequestBody Map map){
        int add = powerService.addMenu(map);
        if (add>0)
            return add;
        else
            return 0 ;
    }

    /**
     * 权限菜单修改(vue+element)
     * @param map
     */
    @ResponseBody
    @RequestMapping("/updateMenu")
    public Object updateMenu(@RequestBody Map map){
        int update = powerService.updateMenu(map);
        if (update>0)
            return update;
        else
            return 0 ;
    }
    /**
     * 权限菜单删除(vue+element)
     * @param id
     */
    @ResponseBody
    @RequestMapping("/deleteMenu")
    public Object deleteMenu(Integer id){
        int del = powerService.delete(id);
        if (del>0)
            return del;
        else
            return 0;
    }

    /**
     * 表单唯一性验证
     * @param label
     * @return
     */
    @ResponseBody
    @RequestMapping("/label")
    public Object getLabel(String label){
        List<Map> powerList=powerService.getLabel(label);
        if (powerList!=null&&powerList.size()>0){
            return 1;
        }
        return 0;
    }
}
