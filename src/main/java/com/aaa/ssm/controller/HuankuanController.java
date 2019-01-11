package com.aaa.ssm.controller;

import com.aaa.ssm.service.HuankuanService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:HuankuanController
 * discription:还款页面
 * author:jiasanshui
 * createTime:2018-12-14 11:56
 */
@Controller
@RequestMapping("huankuan")
public class HuankuanController {

    @Autowired
    private HuankuanService huankuanService;

    /**
     * 分页查询个人贷款信息
     * @return
     */
    @ResponseBody
    @RequestMapping("page")
    public Object page(@RequestBody Map map){
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        PageInfo<Map> pageInfo = new PageInfo<Map>(huankuanService.getListByUName(map));
        Map resultMap = new HashMap();
        resultMap.put("pageData",pageInfo.getList());
        resultMap.put("total",pageInfo.getTotal());
        return resultMap;
    }

    /**
     * 根据订单查询还款信息（当前期应还款）
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("getReturnCurrent")
    public Object getReturnCurrent(@RequestBody Map map){
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        List<Map> returnInfo = huankuanService.getReturnCurrent(map);
        System.out.println(returnInfo);
        PageInfo<Map> pageInfo =new PageInfo<>(returnInfo);
        Map resultMap = new HashMap();
        resultMap.put("huanData",pageInfo.getList());
        resultMap.put("total",pageInfo.getTotal());
        resultMap.put("sumlimit",returnInfo.get(0).get("TIMELIMIT"));
        System.out.println(returnInfo.get(0).get("TIMELIMIT"));
        return resultMap;
    }

    /**
     * 根据不同的查询条件查询还款信息（提前还款，未还款，已还款）
     */
    @ResponseBody
    @RequestMapping("getReturnInfo")
    public Object getReturnInfo(@RequestBody Map map){
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        List<Map> returnInfo = huankuanService.getReturnInfo(map);
        PageInfo<Map> pageInfo = new PageInfo<>(returnInfo);
        Map resultMap = new HashMap();
        resultMap.put("huanData",pageInfo.getList());
        resultMap.put("total",pageInfo.getTotal());
        //获取当前页
        int pageNo  = Integer.valueOf(map.get("pageNo").toString());
        //获取分页数量
        int pageSize = Integer.valueOf(map.get("pageSize").toString());
        //获取当前页的第一条数量的期数
        int limit = Integer.valueOf(returnInfo.get(0).get("TIMELIMIT").toString());
        if(pageNo!=1){      //当前页不是第一页的时候
            resultMap.put("sumlimit",limit-(pageNo-1)*pageSize);
        }else {
            resultMap.put("sumlimit",returnInfo.get(0).get("TIMELIMIT"));
        }
        return resultMap;
    }

    /**
     * 重新加载当前还款期数
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("reGetReturnCurrent")
    public Object reGetReturnCurrent(@RequestBody Map map){
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        List<Map> returnInfo = huankuanService.reGetReturnCurrent(map);
        PageInfo<Map> pageInfo = new PageInfo<>(returnInfo);
        Map resultMap = new HashMap();
        resultMap.put("huanData",pageInfo.getList());
        resultMap.put("total",pageInfo.getTotal());
        resultMap.put("sumlimit",returnInfo.get(0).get("TIMELIMIT"));
        return resultMap;
    }

    /**
     * 查询未还款信息
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("noReturnInfo")
    public Object noReturnInfo(@RequestBody Map map){
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        List<Map> returnInfo = huankuanService.noReturnInfo(map);
        PageInfo<Map> pageInfo = new PageInfo<>(returnInfo);
        Map resultMap = new HashMap();
        resultMap.put("huanData",pageInfo.getList());
        resultMap.put("total",pageInfo.getTotal());
        resultMap.put("sumlimit",null);
        return resultMap;
    }

    /**
     * 查询已还款信息
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("haveReturnInfo")
    public Object haveReturnInfo(@RequestBody Map map){
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        List<Map> returnInfo = huankuanService.haveReturnInfo(map);
        PageInfo<Map> pageInfo = new PageInfo<>(returnInfo);
        Map resultMap = new HashMap();
        resultMap.put("huanData",pageInfo.getList());
        resultMap.put("total",pageInfo.getTotal());
        resultMap.put("sumlimit",null);
        return resultMap;
    }

    /**
     * 付款输入密码，判断付款密码是否正确
     */
    @ResponseBody
    @RequestMapping("balance")
    public Object balance(String pwd,String username,String limits,String borrownum){
        //结算判断支付密码是否正确
        Boolean isTrue = huankuanService.balancePwd(pwd, username);
        if(isTrue){
            //当密码正确时，修改订单状态
            int i = huankuanService.balanceUpdateLimit(limits,borrownum);
            if(i>0){
                //修改成功后将付款数据带到付款成功页面
                return 1;
            }
        }
        return 0;
    }

    @RequestMapping("gethuankuanInfo")
    public Object gethuankuanInfo(String limits, String borrownum, String allMoney, Model model, HttpSession session){
        //获取还款时间
        String huankuantime = huankuanService.gethuankuanTime(limits, borrownum);
        Map map = new HashMap();
        double huanMoney = Double.parseDouble(allMoney);
        map.put("huanMoney",huanMoney);
        map.put("borrownum",borrownum);
        //减去账户余额
        huankuanService.updateAmount(map);
        model.addAttribute("huankuantime",huankuantime);
        model.addAttribute("allMoney",allMoney);
        return "qiantai/fukuan/fkcg";
    }

}
