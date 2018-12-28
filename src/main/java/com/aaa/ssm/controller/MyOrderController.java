package com.aaa.ssm.controller;

import com.aaa.ssm.service.MyOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * className:MyOrderController
 * discription:
 * author:yb
 * createTime:2018-12-26 16:08
 */
@Controller
@RequestMapping("/myorder")
public class MyOrderController {
   /* @Autowired
    private  MyOrderService myOrderService;
    @ResponseBody
    @RequestMapping("/page")

    public  Object page(@RequestBody Map map){ PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        PageInfo<Map> pageInfo = new PageInfo<Map>(myOrderService.getOrderByInfo(map));
        Map resultMap = new HashMap();
        resultMap.put("pageData",pageInfo.getList());
        resultMap.put("total",pageInfo.getTotal());
        return resultMap;
    }*/
}
