package com.aaa.ssm.controller;

import com.aaa.ssm.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * className:DemoController
 * discription:
 * author:jiasanshui
 * createTime:2018-11-29 19:49
 */
@Controller
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("demo")
    public Object list(Model model){
        model.addAttribute("list",demoService.getList());
        return "list";
    }
}
