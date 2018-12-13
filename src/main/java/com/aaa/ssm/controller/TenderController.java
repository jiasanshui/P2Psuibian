package com.aaa.ssm.controller;

import com.aaa.ssm.service.TenderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * className:TenderController
 * discription:
 * author:hulu
 * createTime:2018-12-12 14:52
 */
@Controller
@RequestMapping("/tenderCheck")
public class TenderController {
    @Autowired
    private TenderService tenderService;

    private final ResourceLoader resourceLoader;

    @Autowired
    public TenderController(ResourceLoader resourceLoader) {
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
        PageInfo<Map> pageInfo=new PageInfo<Map>(tenderService.getList(map));
                //System.out.println(map);
                Map resultMap = new HashMap();
                //获取当前页数据
                resultMap.put("pageData", pageInfo.getList());
                //获取分页总数量
                resultMap.put("total", pageInfo.getTotal());
                return resultMap;
            }

            /**
             * 显示图片
             *
             * @param fileName
             * @return
             */
            @RequestMapping("show")
            public ResponseEntity show(String fileName) {
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
             *
             * @param userId
             * @return
             */
            @ResponseBody
            @RequestMapping("/update/{userId}")
            public Object update(@PathVariable("userId") Integer userId) {
                return tenderService.update(userId);
            }

            /**
             * 审核驳回，更新用户信息表审核状态并让驳回表中插入数据
             *
             * @return
             */
            @ResponseBody
            @RequestMapping("/edit")
            public Object edit(@RequestBody Map map) {
                int edit = tenderService.edit(map);
                int bohui = tenderService.addBohui(map);
                if (edit != 0 && bohui != 0) {
                    return 1;
                }
                return 0;
            }
    /*@ResponseBody
    @RequestMapping("/list")
    public Object getAllList(@RequestBody Map map){
        List<Map> allList = userInfoService.getAllList(Integer.valueOf(map.get("userId")+""));
        return allList;
    }*/

            /**
             * 后台显示投标页面
             *
             * @param map
             * @return
             */
            @ResponseBody
            @RequestMapping("/page2")
            public Object getPageByParams(@RequestBody Map map) {
                //设置当前第几页和每页显示数量
                PageHelper.startPage(Integer.valueOf(map.get("pageNo") + ""), Integer.valueOf(map.get("pageSize") + ""));
                //用pageInfo对结果进行封装
                PageInfo<Map> pageInfo = new PageInfo<Map>(tenderService.getPageByParams(map));
                //System.out.println(map);
                Map resultMap = new HashMap();
                //获取当前页数据
                resultMap.put("pageData", pageInfo.getList());
                //获取分页总数量
                resultMap.put("total", pageInfo.getTotal());
                return resultMap;
            }


            /**
             * 添加投标
             *
             * @param map
             * @param model
             * @return
             */
            @ResponseBody
            @RequestMapping("/add")
            public Object add(@RequestBody Map map, Model model) {
                int result = tenderService.add(map);
                if (result == 1) {
                    model.addAttribute("showInfo", "投标成功");
                    return tenderService.add(map);
                } else {
                    model.addAttribute("showInfo", "投标失败");
                    return 0;
                }
            }
}