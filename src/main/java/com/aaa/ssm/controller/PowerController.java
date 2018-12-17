package com.aaa.ssm.controller;

import com.aaa.ssm.entity.TreeNode;
import com.aaa.ssm.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
        System.out.println(list);
        return list;
    }
}
