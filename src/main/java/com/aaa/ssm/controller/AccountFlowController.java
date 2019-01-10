package com.aaa.ssm.controller;

import com.aaa.ssm.entity.UserRegister;
import com.aaa.ssm.service.AccountFlowService;
import com.aaa.ssm.util.PageUtil;
import com.aaa.ssm.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:AccountFlowController
 * discription:
 * author:xiefuzhi
 * createTime:2018-12-21 10:26
 */
@Controller
@RequestMapping("/accountflow")
public class AccountFlowController {
    //依赖注入
    @Autowired
    private AccountFlowService accountFlowService;

    /**
     * 查询流水类型
     * @return
     */
    @ResponseBody
    @RequestMapping("/flowtype")
    public Object getFlowtype(){
        List<Map> flowtypeList=accountFlowService.getFlowtype();
        return flowtypeList;
    }

}
