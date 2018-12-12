package com.aaa.ssm.controller;

import com.aaa.ssm.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * className:ProjectController
 * discription:显示所有招标项目
 * author:jiasanshui
 * createTime:2018-12-12 16:36
 */
@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    /**
     * 显示房屋抵押招标
     */
    public Object getHousePro(){
        return "";
    }
}
