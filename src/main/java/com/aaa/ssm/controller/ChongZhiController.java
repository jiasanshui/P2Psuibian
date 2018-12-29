package com.aaa.ssm.controller;

import com.aaa.ssm.service.ChongzhiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * className:ChongZhiController
 * discription:账户充值界面
 * author:jiasanshui
 * createTime:2018-12-29 15:36
 */
@Controller
@RequestMapping("chongzhi")
public class ChongZhiController {

    @Autowired
    private ChongzhiService chongzhiService;

    @ResponseBody
    @RequestMapping("getBankByCard")
    public Object getBankByCard(@RequestParam String BCIDSix){
        return chongzhiService.getBankByCard(BCIDSix);
    }
}
