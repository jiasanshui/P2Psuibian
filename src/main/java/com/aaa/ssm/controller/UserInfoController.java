package com.aaa.ssm.controller;

import com.aaa.ssm.service.UserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.ResourceLoader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:UserInfoController
 * discription:
 * author:fhm
 * createTime:2018-12-10 17:15
 */
@Controller
@RequestMapping("/userInfo")
public class UserInfoController {
    //依赖注入service层
    @Autowired
    private UserInfoService userInfoService;

    private final ResourceLoader resourceLoader;

    @Autowired
    public UserInfoController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    //取出配置文件中upload.path的值  赋给uploadPath类变量
    @Value(value = "${upload.path}")
    private String uploadPath;
    /**
     * 用户分页列表数据
     * @param map
     * @return
     */
    @ResponseBody //返回json
    @RequestMapping("/page")
    public Object page(@RequestBody Map map){
        //设置当前第几页和每页显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        //用pageInfo对结果进行封装
        PageInfo<Map> pageInfo=new PageInfo<Map>(userInfoService.getList(map));
        Map resultMap=new HashMap();
        //获取当前页数据
        resultMap.put("pageData",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total",pageInfo.getTotal());
        return resultMap;
    }

    /**
     * 显示图片
     * @param fileName
     * @return
     */
    @RequestMapping("show")
    public ResponseEntity show(String fileName){
        try {
            //System.out.println("1111");
            // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
            //uploadPath = D:/files/   fileName=332854a6-e3a1-4b90-973a-4fca79068017.jpg
            return ResponseEntity.ok(resourceLoader.getResource("file:" + uploadPath + fileName));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    /**
     * 审核通过，更新用户信息表审核状态
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/update/{userId}")
    public Object update(@PathVariable("userId") Integer userId){
        return userInfoService.update(userId);
    }

    /**
     * 审核驳回，更新用户信息表审核状态并让驳回表中插入数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/edit")
    public Object edit(@RequestBody Map map){
        int edit = userInfoService.edit(map);
        int bohui = userInfoService.addBohui(map);
        if(edit!=0 && bohui!=0){
            return 1;
        }
        return 0;
    }

    @ResponseBody
    @RequestMapping("/list")
    public Object getAllList(Integer userId){
        List<Map> allList = userInfoService.getAllList(userId);
        return allList.get(0);

    }
}
