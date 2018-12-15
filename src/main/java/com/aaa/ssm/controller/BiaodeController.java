package com.aaa.ssm.controller;

import com.aaa.ssm.service.BiaodeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * className:BiaodeController
 * discription:
 * author:fhm
 * createTime:2018-12-15 10:11
 */
@Controller
@RequestMapping("/biaode")
public class BiaodeController {
    //依赖注入
    @Autowired
    private BiaodeService biaodeService;

    /**
     * 用户借款标的审核分页列表数据
     * @param map
     * @return
     */
    @ResponseBody //返回json
    @RequestMapping("/page")
    public Object page(@RequestBody Map map){
        //设置当前第几页和每页显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        //用pageInfo对结果进行封装
        PageInfo<Map> pageInfo=new PageInfo<Map>(biaodeService.getList(map));
        //System.out.println(map);
        Map resultMap=new HashMap();
        //获取当前页数据
        resultMap.put("pageData",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total",pageInfo.getTotal());
        return resultMap;
    }
    /**
     * 审核通过
     * 1、更新用户借款表审核状态，招标开始时间
     * 2、向还款表中插入数据(计算利息)
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody Map map){
        return biaodeService.update(map);
    }

    /**
     * 审核驳回
     * 1.更新用户借款表审核状态
     * 2.往借款驳回表中插入数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/edit")
    public Object edit(@RequestBody Map map){
        return biaodeService.edit(map);
    }

}
