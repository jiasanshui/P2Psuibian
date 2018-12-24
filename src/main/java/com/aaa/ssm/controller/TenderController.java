package com.aaa.ssm.controller;

import com.aaa.ssm.service.AccountFlowService;
import com.aaa.ssm.service.BorrowService;
import com.aaa.ssm.service.TenderService;
import com.aaa.ssm.service.UserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:TenderController
 * discription:招标投资
 * author:xiefuzhi
 * createTime:2018-12-11 19:26
 */
@Controller
@Transactional//事务
@RequestMapping("/tender")
public class    TenderController {
    //依赖注入
    @Autowired
    private TenderService tenderService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private BorrowService borrowService;

    @Autowired
    private AccountFlowService accountFlowService;
    /**
     * 跳转分页页面
     * @return
     */
    @RequestMapping("/toPage")
    public String toPage(){
        return "tender/page1";
    }

    /**
     * 前台显示投标信息
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/page1")
    public Object getPage(@RequestBody Map map){
        //设置当前第几页和每页显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        //用pageInfo对结果进行封装
        PageInfo<Map> pageInfo=new PageInfo<Map>(tenderService.getTenderPage(map));
        Map resultMap=new HashMap();
        //获取当前页数据
        resultMap.put("pageData",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total",pageInfo.getTotal());
        return resultMap;
    }


    /**
     * 后台显示投标页面
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/page2")
    public Object getPageByParams(@RequestBody Map map,Model model,HttpSession session){
        //设置当前第几页和每页显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        //用pageInfo对结果进行封装
        PageInfo<Map> pageInfo=new PageInfo<Map>(tenderService.getPageByParams(map));
        Map resultMap=new HashMap();
        //获取当前页数据
        resultMap.put("pageData",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total",pageInfo.getTotal());
        String username=(String) session.getAttribute("userName");
        //根据用户名去获取用户信息
        List<Map> list = userInfoService.getUserList(username);
        model.addAttribute("uid",list.get(0).get("USERID"));
        return resultMap;
    }


    /**
     * 添加投标
     * @param map
     * @param map
     * @return
     */
    @RequestMapping("/add")
    public Object add(@RequestParam Map map){
        Double amount = Double.parseDouble(map.get("amount")+"");
        Double tamount = Double.parseDouble(map.get("tamount")+"");
        amount = amount -tamount;
        map.put("amount",amount);
        System.out.println(map);
        int result1 = tenderService.add(map);
        int result2 = borrowService.update(map);
        int result3 = accountFlowService.add(map);
        int result4 = userInfoService.updateAmount(map);
        int result5 = userInfoService.updateFreezAmount(map);
        if (result1==1&&result2==1&&result3==1&&result4==1){
            return "redirect:/jump/list";
        }else {
            return "redirect:/jump/toubiao";
        }
    }

    /**
     * 校验支付密码
     * @param userid
     * @return
     */

    @ResponseBody
    @RequestMapping("/checkPayPwd")
    public Object checkPayPwd(Integer userid,Integer payPwd){
        List<Map> list = userInfoService.getUserListById(userid);
        Map map=new HashMap();
        if (list!=null&&list.size()>0){
            if(Integer.valueOf(list.get(0).get("PAYPWD").toString()).equals(payPwd)){
                map.put("suc","true");
            }
        }
        return map;
    }

}
