package com.aaa.ssm.controller;

import com.aaa.ssm.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * className:ProjectController
 * discription:显示所有招标项目
 * author:jiasanshui
 * createTime:2018-12-12 16:36
 */
@Controller
@RequestMapping("project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @ResponseBody
    @RequestMapping("jindu")
    public Object getJindu(String borrowNum){
        List<Map> housePro = projectService.getJinduByNum(borrowNum);
        return housePro;
    }
}
