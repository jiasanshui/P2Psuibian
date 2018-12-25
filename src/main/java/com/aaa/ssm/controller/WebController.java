package com.aaa.ssm.controller;

import com.aaa.ssm.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * className:WebController
 * discription:
 * author:hulu
 * createTime:2018-12-18 11:17
 */
@Controller
@RequestMapping("/web")
public class WebController {

    @Autowired
    private WebService webService;

    @RequestMapping("list")
    public Object getWebList(){
        return webService.getWebList();
    }
    @RequestMapping("mediaList")
    public Object getMediaList(){
        return webService.getWebList();
    }
    @RequestMapping("companyList")
    public Object getCompanyList(){
        return webService.getWebList();
    }
    @RequestMapping("teamList")
    public Object teamList(){
        return webService.getTeamList();
    }
    @RequestMapping("partnerList")
    public Object partnerList(){
        return webService.getPartnerList();
    }
    @RequestMapping("teamSList")
    public Object teamSList(){
        return webService.getTeamSList();
    }
}
