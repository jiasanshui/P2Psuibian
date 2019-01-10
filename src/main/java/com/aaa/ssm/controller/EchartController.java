package com.aaa.ssm.controller;

import com.aaa.ssm.service.EchartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * className:EchartController
 * discription:
 * author:hulu
 * createTime:2018-12-28 13:19
 */
@Controller
@RequestMapping("/echart")
public class EchartController {

    @Autowired
    private EchartService echartService;
    @RequestMapping("/systemflow")
    @ResponseBody
    public Object getSystemFlow(Integer year){
        List<Map> systemFlowList = echartService.getSystemFlowList(year);
        System.out.println(systemFlowList);
        return  systemFlowList;
    }
}
