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

    @Autowired
    private HttpSession session;

    /**
     * 查询流水类型
     * @return
     */
    @ResponseBody
    @RequestMapping("/flowtype")
    public Object getFlowtype(){
        List<Map> flowtypeList=accountFlowService.getFlowtype();
        System.out.println(flowtypeList);
        return flowtypeList;
    }

    @ResponseBody
    @RequestMapping("/userflow")
    public Object accountFlow(Model model, @RequestParam Map map, HttpServletRequest request){
        System.out.println(map);
        UserRegister user=(UserRegister) session.getAttribute("user");
        if (user==null){
            Integer userId = user.getUserId();
            map.put("userId",userId);
            //获取分页总数量
            int pageCount =accountFlowService.getPageCount(map);
            int pageSize=8;
            int pageNo=0;
            Object tempPageNo=map.get("pageNo");
            if (StringUtil.isEmpty(tempPageNo)){
                pageNo=1;
            }else {
                pageNo=Integer.valueOf(tempPageNo+"");
            }
            map.put("pageSize",pageSize);
            map.put("pageNo",pageNo);
            //分页工具使用
            String pageString = new PageUtil(pageNo, pageSize, pageCount, request).getPageString();
            //List<Map> recordByDeposits = accountFlowService.getAccountFlow(map);
            model.addAttribute("accountflow",accountFlowService.getAccountFlow(map));
            model.addAttribute("pageString", pageString);
            return "qiantai/money_record";
        }
        return null;
    }

}
