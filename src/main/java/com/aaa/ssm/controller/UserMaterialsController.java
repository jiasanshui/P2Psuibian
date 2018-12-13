package com.aaa.ssm.controller;

import com.aaa.ssm.service.UserMaterialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * className:UserMaterialscontroller
 * discription:
 * author:yb
 * createTime:2018-12-11 15:18
 */
@Controller
@RequestMapping("/usermaterials")
public class UserMaterialsController {
    @Autowired
    private UserMaterialsService userMaterialsService;

    /**
     * 添加用户材料
     */
    @RequestMapping("/addMaterials")
    public  Object addUserMaterials(@RequestParam Map map){
        System.out.println(map);
        int i = userMaterialsService.addUserMaterials(map);

        return i;
        }

    }

