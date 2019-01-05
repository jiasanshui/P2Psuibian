package com.aaa.ssm.controller;

import com.aaa.ssm.service.DepositsRecordService;
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
 * className:DepositsRecordController
 * discription:
 * author:yb
 * createTime:2019-01-04 17:35
 */
@Controller
@RequestMapping("/touziRecord")
public class DepositsRecordController {
//    /**
//     * 前台显示投标信息
//     * @param map
//     * @return
//     */
//    @Autowired
//    private  DepositsRecordService depositsRecordService;
//    @ResponseBody
//    @RequestMapping("/page1")
//    public Object getPage(@RequestBody Map map){
//        //设置当前第几页和每页显示数量
//        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
//        //用pageInfo对结果进行封装
//        PageInfo<Map> pageInfo=new PageInfo<Map>(depositsRecordService.getRecordByDeposits(map));
//        Map resultMap=new HashMap();
//        //获取当前页数据
//        resultMap.put("pageData",pageInfo.getList());
//        //获取分页总数量
//        resultMap.put("total",pageInfo.getTotal());
//        return resultMap;
//    }
//
}
