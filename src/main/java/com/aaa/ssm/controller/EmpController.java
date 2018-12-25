package com.aaa.ssm.controller;

import com.aaa.ssm.dao.EmpDao;
import com.aaa.ssm.service.EmpService;
import com.alibaba.druid.sql.visitor.functions.If;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:EmpController
 * discription:
 * author:yb
 * createTime:2018-12-17 15:11
 */
@Controller
@RequestMapping("/emp")
public class EmpController {
    @Autowired
    private EmpService empService;
    /**
     * 跳转分页列表
     * @return
     */
    @RequestMapping("/toPage")
    public String toPage(){
        return "houtai/emp/list";
    }

    /**
     * 员工状态下拉框
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public  Object getEmpList(){
        return empService.getEmpList();
    }

    @ResponseBody //返回json
    @RequestMapping("/page")
    public Object page(@RequestBody Map map){
        //设置当前第几页和每页显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        //用PageInfo对结果进行包装
        PageInfo<Map> pageInfo =new PageInfo<Map>(empService.getList(map));
        Map resultMap =new HashMap();
        //获取当前页数据
        resultMap.put("pageData",pageInfo.getList()) ;
        //获取分页总数量
        resultMap.put("total", pageInfo.getTotal());
        return resultMap;
    }
    /**
     * 添加
     *
     * @param map
     * @return
     * @ResponseBody 该方法接收的数据为json对象
     * @RequestBody 该方法返回值为json对象
     */
    @ResponseBody
    @RequestMapping("/add")
    public Object add(@RequestBody Map map) {
            Map map1=new HashMap();
            String empno = (String) map.get("EMPNO");
            String phones= (String) map.get("PHONE");
            String idcard = (String) map.get("IDCARD");
            Map empList = empService.getEmpByEmpno(empno);
            if (empList != null && empList.size() > 0) {
                map1.put("msg", "员工编号重复");
                return map1;
            }
            Map phoneList = empService.getEmpByPhone(phones);
            if (phoneList!=null&&phoneList.size()>0) {
                map1.put("msg", "手机号码重复");
                return map1;
            }
           Map idcardList = empService.getEmpByIdcard(idcard);
            if (idcardList!=null&&idcardList.size()>0) {
                map1.put("msg", "身份证号重复");
                return map1;
            }
            int add = empService.add(map);
            return add;
    }


    /**
     * 更新
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody Map map){
        return empService.update(map);
    }
    /**
     * 删除
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(Integer id){
        return empService.delete(id);
    }
    /**
     *
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping("/batchDel/{ids}")
    @ResponseBody
    public Object batchDel(@PathVariable String ids){
        return  empService.bathDelete(ids);
    }

}
