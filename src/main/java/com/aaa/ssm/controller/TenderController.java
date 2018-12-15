package com.aaa.ssm.controller;

import com.aaa.ssm.service.TenderService;
import com.aaa.ssm.service.UserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping("/tender")
public class TenderController {
    //依赖注入
    @Autowired
    private TenderService tenderService;

    @Autowired
    private UserInfoService userInfoService;
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
        PageInfo<Map> pageInfo=new PageInfo<Map>(tenderService.getPage(map));
        //System.out.println(map);
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
    public Object getPageByParams(@RequestBody Map map){
        //设置当前第几页和每页显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        //用pageInfo对结果进行封装
        PageInfo<Map> pageInfo=new PageInfo<Map>(tenderService.getPageByParams(map));
        //System.out.println(map);
        Map resultMap=new HashMap();
        //获取当前页数据
        resultMap.put("pageData",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total",pageInfo.getTotal());
        return resultMap;
    }


    /**
     * 添加投标
     * @param map
     * @param model
     * @return
     */
    @RequestMapping("/add")
    public Object add(@RequestParam Map map, Model model){
        System.out.println(map);
        System.out.println(map.get("userid"));
        System.out.println(map.get("realName"));
        int result = tenderService.add(map);
        if (result==1){
            model.addAttribute("showInfo","投标成功");
            return "redirect:/jump/list";
        }else {
            model.addAttribute("showInfo","投标失败");
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
