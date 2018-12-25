package com.aaa.ssm.controller;

import com.aaa.ssm.service.BiaodeService;
import com.aaa.ssm.service.UserInfoService;
import com.aaa.ssm.service.UserLoginService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 用户借款标的审核分页列表数据（待审核）
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
     * 审核操作
     * 1.更新用户借款表审核状态
     * 2.往标的审核表中插入数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/edit")
    public Object edit(@RequestBody Map map){
        return biaodeService.edit(map);
    }

    /**
     * 审核人（下拉框）
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin")
    public Object admin(){
        return userLoginService.getEmpByAdminid();
    }

    /**
     * 查询投标记录
     * @return
     */
    @ResponseBody
    @RequestMapping("/tender")
    public Object tender(String borrownum){
        return biaodeService.getTenderinfo(borrownum);
    }

    /**
     * 查询用户实名信息、账户等信息
     * @param paramMap
     * @return
     */
    @ResponseBody
    @RequestMapping("/userinfo")
    public Object getAllUserByUserid(@RequestBody Map paramMap){
        //System.out.println(paramMap);
        Map map=userInfoService.getAllUserByuserid(Integer.valueOf(paramMap.get("userid")+"")).get(0);
        Map aduitBid = biaodeService.getAduitBid((String) paramMap.get("borrownum"));
        map.put("ENAME",aduitBid.get("ENAME"));
        map.put("ADUITTIME",aduitBid.get("ADUITTIME"));
        map.put("ADUITRESULT",aduitBid.get("ADUITRESULT"));
        map.put("ADUITREMARK",aduitBid.get("ADUITREMARK"));
        return map;
    }

}
