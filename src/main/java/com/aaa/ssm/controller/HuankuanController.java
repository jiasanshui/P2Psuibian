package com.aaa.ssm.controller;

import com.aaa.ssm.service.HuankuanService;
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
 * className:HuankuanController
 * discription:还款页面
 * author:jiasanshui
 * createTime:2018-12-14 11:56
 */
@Controller
@RequestMapping("huankuan")
public class HuankuanController {

    @Autowired
    private HuankuanService huankuanService;

    /**
     * 分页查询个人贷款信息
     * @return
     */
    @ResponseBody
    @RequestMapping("page")
    public Object page(@RequestBody Map map){
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        PageInfo<Map> pageInfo = new PageInfo<Map>(huankuanService.getListByUName(map));
        Map resultMap = new HashMap();
        resultMap.put("pageData",pageInfo.getList());
        resultMap.put("total",pageInfo.getTotal());
        return resultMap;
    }
}
