package com.aaa.ssm.controller;

import com.aaa.ssm.service.BiaodeService;
import com.aaa.ssm.service.RepayService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * className:LoanController
 * discription:
 * author:fhm
 * createTime:2018-12-21 19:00
 */
@Controller
@RequestMapping("/loan")
public class LoanController {

    //依赖注入
    @Autowired
    private BiaodeService biaodeService;

    @Autowired
    private RepayService repayService;

    /**
     * 获取待放款的投标信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/page")
    public Object page(@RequestBody Map map){
        //设置当前第几页和每页显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        //用pageInfo对结果进行封装
        PageInfo<Map> pageInfo=new PageInfo<Map>(biaodeService.getPageByLoan(map));
        Map resultMap=new HashMap();
        //获取当前页数据
        resultMap.put("pageData",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total",pageInfo.getTotal());
        return resultMap;
    }

    /**
     * 满标二审通过操作
     * 1、更改标的状态
     * 2、往标的审核表中插入数据
     * 3、生成还款计划表
     * 4、用户账户表（借款人账户余额：+借款金额；待还金额：本息和
     *                投资人账户：冻结金额=原冻结金额-投资金额 、代收本金=投资金额、代收利息=总利息  ）
     * 5、账户流水表（变动）
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/check")
    public Object loanRepay(@RequestBody Map map){
        return repayService.repayPlan(map);
    }
}
