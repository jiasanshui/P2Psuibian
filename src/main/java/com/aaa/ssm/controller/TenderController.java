package com.aaa.ssm.controller;

import com.aaa.ssm.service.TenderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * className:TenderController
 * discription:
 * author:xiefuzhi
 * createTime:2018-12-11 19:26
 */
@Controller
@RequestMapping("/tender")
public class TenderController {
    //依赖注入
    private TenderService tenderService;

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
    @ResponseBody
    @RequestMapping("/add")
    public Object add(@RequestBody Map map, Model model){
        int result = tenderService.add(map);
        if (result==1){
            model.addAttribute("showInfo","投标成功");
            return tenderService.add(map);
        }else {
            model.addAttribute("showInfo","投标失败");
            return 0;
        }
    }

}
