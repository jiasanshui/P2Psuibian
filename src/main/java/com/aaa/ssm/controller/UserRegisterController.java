package com.aaa.ssm.controller;

import com.aaa.ssm.entity.UserRegister;
import com.aaa.ssm.service.UserRegisterService;
import com.aaa.ssm.util.SendMsgUtil;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * className:UserRegisterController
 * discription:用户注册
 * author:fhm
 * createTime:2018-12-07 22:34
 */
@Controller
@RequestMapping("/register")
public class UserRegisterController {
    //依赖注入业务层
    @Autowired
    private UserRegisterService userRegisterService;

    /**
     * 校验用户是否存在
     * @param username
     * @return
     */
    @ResponseBody
    @RequestMapping("/findUsername")
    public Object checkUser(String username){
        Map map=new HashMap();
        UserRegister user = userRegisterService.findUserByuserName(username);
        if(user!=null){
            map.put("msg","1");
        }
        return map;
    }

    /**
     * 发送验证码，并校验手机号是否被注册
     * @return
     */
    @ResponseBody
    @RequestMapping("/sendPhoneCode")
    public Object sendPhoneCode(String telephone, HttpSession session){
        Map map=new HashMap();
        UserRegister user = userRegisterService.findUserByPhone(telephone);
        if(user!=null){
            map.put("msg","1");
            return map;
        }
        SendMsgUtil sendMsgUtil = new SendMsgUtil();
        String phoneVerify = sendMsgUtil.sendMsg(telephone);
        //phoneVerify 为验证码，可以加在session，用来判断输入的验证码是否正确
        session.setAttribute("phoneVerify",phoneVerify);
        return map;
    }
    /**
     * 校验手机验证码是否正确
     * @return
     */
    @ResponseBody
    @RequestMapping("/vfPhoneCode")
    public Object vfPhoneCode(String code, HttpServletRequest request){
        Map map=new HashMap();
        //从session里面取出验证码
        Object phoneVerify = request.getSession().getAttribute("phoneVerify");
        if(code.equals(phoneVerify)){//验证码正确
            map.put("msg","1");
        }
        return map;
    }
    /**
     * 校验邮箱是否被注册
     * @return
     */
    @ResponseBody
    @RequestMapping("/findUserByEmail")
    public Object checkEmail(String email){
        Map map=new HashMap();
        UserRegister user = userRegisterService.findUserByEmail(email);
        if(user!=null){
            map.put("msg","1");
        }
        return map;
    }

    /**
     * 用户注册提交
     * @return
     */
    @ResponseBody
    @RequestMapping("/userRegister")
    public Object userRegister(@RequestParam Map paramMap){
        System.out.println(paramMap);
        Map map=new HashMap();
        int i = userRegisterService.addUser(paramMap);
        if (i==0){
            return map;
        }
        map.put("msg","1");
        return map;
    }


}
