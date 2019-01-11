package com.aaa.ssm.controller;

import com.aaa.ssm.service.BorrowService;
import com.aaa.ssm.util.FtpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
            System.out.println("借款失败");
            return "redirect:/jump/borrow";
        }
        System.out.println("借款成功");
        return "redirect:/jump/index";
    }

    /**
     * 借款页面提示
     * @return
     */
    @ResponseBody
    @RequestMapping("/isBorrow")
    public Object isBorrow(Integer userid){
        Map borrowMap=borrowService.isBorrow(userid);
        Integer stateid=Integer.valueOf(borrowMap.get("STATEID")+"");
        if(stateid==0 || stateid==10){//可以借款（新人） || 上次还款结束
            return 0;
        }else if(stateid==1 || stateid==6){//借款申请已提交，请耐心等候
            return 2;
        }else if(stateid==2){//申请通过，开始招标
            return 3;
        }else if(stateid==3 || stateid==4 || stateid==5 || stateid==11){//申请失败
            return 4;
        }else if(stateid==7){//待放款（放款中）
            return 1;
        }else {//有贷款未还清，不能申请
            return 5;
        }
    }

    /**
     * 重新申请，提交借款材料
     * @param userid
     * @return
     */
    @ResponseBody
    @RequestMapping("/reapply")
    public Object reapply(Integer userid){
        //更新stateid
        int update=borrowService.updatebidstate(userid);
        return update;
    }
}