package com.aaa.ssm.controller;

import com.aaa.ssm.service.BorrowService;
import com.aaa.ssm.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * className:BorrowController
 * discription:借款业务
 * author:hulu
 * createTime:2018-12-11 15:19
 */
@Controller
@RequestMapping("/borrow")
public class BorrowController {

    //依赖注入
    @Autowired
    private BorrowService borrowService;

    /**
     * 借款人提交数据到后台
     * @param map
     * @return
     */
    @RequestMapping("/addBorrow")
    public Object addBorrow(@RequestParam Map map){
        System.out.println(map);
        int add = borrowService.add(map);
        if(add==0){
            return "qiantai/borrow";
        }
        return "qiantai/list";
    }

}