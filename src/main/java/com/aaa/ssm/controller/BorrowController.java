package com.aaa.ssm.controller;

import com.aaa.ssm.service.BorrowService;
import com.aaa.ssm.util.FtpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

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

    @Autowired
    private FtpUtil ftpUtil;

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
    public Object addBorrowOne(@RequestParam Map map, @RequestParam MultipartFile documentpic, @RequestParam MultipartFile physicapic){
        System.out.println(map);
        System.out.println(documentpic+","+physicapic);
        //上传图片
        String newFileNameA = ftpUtil.upLoad(documentpic);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String newFileNameB = ftpUtil.upLoad(physicapic);
        map.put("documentpic",newFileNameA);
        map.put("physicapic",newFileNameB);
        int add = borrowService.addOne(map);
        if (add==0) {
            return "redirect:/jump/borrow";
        }
        return "redirect:/jump/index";
    }


}