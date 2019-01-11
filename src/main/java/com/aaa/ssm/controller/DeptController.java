package com.aaa.ssm.controller;

import com.aaa.ssm.service.DeptService;
import com.aaa.ssm.service.EmpService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.deploy.security.MSCryptoProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:DeptController
 * discription:
 * author:yb
 * createTime:2018-12-16 20:39
 */
@Controller
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;

    /**
     * 部门列表方法(员工下拉框)
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public Object list() {
        return deptService.getList();
    }

    @RequestMapping("/toState")
    public String toState(){
        return "houtai/dept/list";
    }

    /**
     * 部门分页列表
     *
     * @param map
     * @return
     */
    @ResponseBody //返回json
    @RequestMapping("/page")
    public Object page(@RequestBody Map map) {
        //设置当前第几页和每页显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo") + ""), Integer.valueOf(map.get("pageSize") + ""));
        //用PageInfo对结果进行包装
        PageInfo<Map> pageInfo = new PageInfo<Map>(deptService.getDeptList(map));
        Map resultMap = new HashMap();
        //获取当前页数据
        resultMap.put("pageData", pageInfo.getList());
        //获取分页总数量
        resultMap.put("total", pageInfo.getTotal());
        return resultMap;
    }

    @ResponseBody
    @RequestMapping("/add")
    public Object add(@RequestBody Map map) {
        Map map1=new HashMap();
        try {
            String dname = (String) map.get("DNAME");
            List<Map> deptList=deptService.getDeptByDname(dname);
            if (deptList!=null&&deptList.size()>0){
                map1.put("msg","已有该部门");
                return map1;
            }
            int add = deptService.add(map);
            return add;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 更新
     *
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody Map map) {
        System.out.println(map);
        return deptService.update(map);
    }

    /**
     * 删除
     *
     * @param deptid
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(Integer deptid) {
        return deptService.delete(deptid);
    }

    /**
     * 部门状态下拉框
     * @return
     */
    @ResponseBody
    @RequestMapping("/getDeptInfo")
    public  Object getDeptInfo(){
       return deptService.getDeptInfo();
    }

}

