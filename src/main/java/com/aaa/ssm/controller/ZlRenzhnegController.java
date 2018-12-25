package com.aaa.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * className:ZlRenzhnegController
 * discription:
 * author:xiefuzhi
 * createTime:2018-12-24 14:32
 */
@Controller
@RequestMapping("zlrenzheng")
public class ZlRenzhnegController {
    public Object grtZlList(){
        return "redirect:/jump/zlrenzheng";
    }
}
