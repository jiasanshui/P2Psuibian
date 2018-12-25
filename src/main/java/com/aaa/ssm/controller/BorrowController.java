package com.aaa.ssm.controller;

import com.aaa.ssm.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

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
     * 借款人提交数据到后台(信用贷款)
     * @param map
     * @return
     */
    @RequestMapping("/addBorrow")
    public Object addBorrow(@RequestParam Map map){
        System.out.println(map);
        int add = borrowService.add(map);
        if(add==0){
            return "redirect:/jump/borrow";
        }
        return "redirect:/jump/list";
    }

    /**
     * 借款人提交数据到后台(房屋抵押贷款)
     * @param map
     * @return
     */
    @RequestMapping("/addBorrowOne")
    public Object addBorrowOne(@RequestParam Map map){
        System.out.println(map);
       /* int add = borrowService.addOne(map);
        if(add==0){
            return "redirect:/jump/borrow";
        }
        return "redirect:/jump/list";*/
        return null;
    }

    /**
     * 借款人提交数据到后台(房屋抵押贷款)
     * @param map
     * @return
     */
    @RequestMapping("/addBorrowTwo")
    public Object addBorrowTwo(@RequestParam Map map){
        System.out.println(map);
        /*int add = borrowService.addTwo(map);
        if(add==0){
            return "redirect:/jump/borrow";
        }
        return "redirect:/jump/list";*/
        return null;
    }

}