package com.aaa.ssm.controller;

import com.aaa.ssm.entity.TbRole;
import com.aaa.ssm.service.RoleService;
import com.fasterxml.jackson.databind.ser.std.IterableSerializer;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:RoleController
 * discription:
 * author:fhm
 * createTime:2018-12-19 09:08
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    //依赖注入
    @Autowired
    private RoleService roleService;

    /**
     * 员工下拉框
     * @return
     */
    @ResponseBody
    @RequestMapping("/lists")
    public  Object getList(){
       return roleService.getList();
    }

    /**
     * 展示所有角色信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public Object getRoles(@RequestBody Map map) throws ParseException {
        Map mp = new HashMap<>();
        PageHelper.startPage(Integer.valueOf(map.get("start")+""),Integer.valueOf(map.get("end")+""));
        List<TbRole> roles = roleService.getRoles();
        PageInfo<TbRole> info = new PageInfo<>(roles);
        mp.put("page",info);
        List<Map> nameList=roleService.getNames();
        mp.put("nameData",nameList);
        return mp;
    }

    /**
     * 获取所有正常角色
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/all")
    public Object getAllRoles(){
        return roleService.getRolesByState();
    }

    /**
     * 角色添加
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public Object add(@RequestBody TbRole role){
        Map map=new HashMap();
        Map roleByName = roleService.getRoleByName(role.getName());
        if (roleByName != null && roleByName.size() > 0) {
            map.put("msg", "该角色名称已存在！！");
            return -2;
        }
        int add = roleService.add(role);
        return  add;
    }

    /**
     * 角色更新
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody TbRole role){
        return roleService.update(role);
    }

    /**
     * 角色删除（注销）
     * @param roleid
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(Integer roleid){
        return roleService.delete(roleid);
    }

}
