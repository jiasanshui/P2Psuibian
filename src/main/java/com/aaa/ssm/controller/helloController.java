package com.aaa.ssm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * className:helloController
 * discription:
 * author:jiasanshui
 * createTime:2018-11-29 19:18
 */
@RestController
public class helloController {

    @RequestMapping("hello")
    public Object demo(){
        return "hello world.....";
    }
}
