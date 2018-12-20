package com.aaa.ssm.controller;

import com.aaa.ssm.service.ProjectService;
import com.aaa.ssm.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
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

    /**
     * 投资列表分页数据
     * @param map
     * @return
     */
    @ResponseBody //返回json
    @RequestMapping("/page")
    public Object page(@RequestBody Map map){
        //设置当前第几页和每页显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        //用pageInfo对结果进行封装
        PageInfo<Map> pageInfo=new PageInfo<Map>(projectService.getHouseProAll());
        //System.out.println(map);
        Map resultMap=new HashMap();
        //获取当前页数据
        resultMap.put("pageData",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total",pageInfo.getTotal());
        return resultMap;
    }

}
