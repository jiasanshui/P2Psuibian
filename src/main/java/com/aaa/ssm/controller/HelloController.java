package com.aaa.ssm.controller;

import com.aaa.ssm.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * className:helloController
 * discription:
 * author:jiasanshui
 * createTime:2018-11-29 19:18
 */
@Controller
public class HelloController {

    @RequestMapping("hello")
    public Object demo(){
        return "houtai/index";
    }


}
