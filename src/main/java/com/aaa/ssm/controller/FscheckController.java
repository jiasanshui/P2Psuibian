package com.aaa.ssm.controller;

import com.aaa.ssm.service.FscheckService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:FscheckController
 * discription:
 * author:yb
 * createTime:2018-12-14 17:25
 */
@Controller
@RequestMapping("/fscheck")
public class FscheckController {
    @Autowired
    private FscheckService fscheckService;

    /**
     * 用户分页列表数据
     *
     * @param map
     * @return
     */
    @ResponseBody //返回json
    @RequestMapping("/page")
    public Object page(@RequestBody Map map) {

        //设置当前第几页和每页显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo") + ""), Integer.valueOf(map.get("pageSize") + ""));
        //用pageInfo对结果进行封装
        PageInfo<Map> pageInfo = new PageInfo<Map>(fscheckService.getList(map));
        //System.out.println(map);
        Map resultMap = new HashMap();
        //获取当前页数据
        resultMap.put("pageData", pageInfo.getList());
        //获取分页总数量
        resultMap.put("total", pageInfo.getTotal());
        return resultMap;
    }

    /**
     * 添加满标
     *
     * @param map
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public Object add(@RequestBody Map map, Model model) {
        int result = fscheckService.add(map);
        if (result == 1) {
            model.addAttribute("showInfo", "投标成功");
            return fscheckService.add(map);
        } else {
            model.addAttribute("showInfo", "投标失败");
            return 0;
        }
    }

    /**
     * 审核驳回，更新用户信息表审核状态并让驳回表中插入数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/edit")
    public Object edit(@RequestBody Map map) {
        int edit = fscheckService.edit(map);
        int bohui = fscheckService.addBohui(map);
        if (edit != 0 && bohui != 0) {
            return 1;
        }
        return 0;
    }
}
