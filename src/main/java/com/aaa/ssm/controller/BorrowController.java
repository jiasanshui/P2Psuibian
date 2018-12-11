package com.aaa.ssm.controller;

import com.aaa.ssm.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:BorrowController
 * discription:
 * author:hulu
 * createTime:2018-12-11 15:19
 */
@Controller
@RequestMapping("/borrow")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @ResponseBody
    @RequestMapping("getPayList")
    public Object getPayList(){
        List<Map> payList = borrowService.getPayList();
        return payList;
    }
    @ResponseBody
    @RequestMapping("getConditionList")
    public Object getConditionList(){
        List<Map> conditionList = borrowService.getConditionList();
        return conditionList;
    }
}