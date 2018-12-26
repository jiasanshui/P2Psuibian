package com.aaa.ssm.controller;

import com.aaa.ssm.service.NoticeService;
import com.aaa.ssm.util.FtpConfiguration;
import com.aaa.ssm.util.FtpUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * className:NoticeController
 * discription:
 * author:hulu
 * createTime:2018-12-17 09:07
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private FtpUtil ftpUtil;

    @Autowired
    private FtpConfiguration ftpConfig;

    @Autowired
    public NoticeController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    //取出配置文件中upload.path的值  赋给uploadPath类变量
    @Value(value = "${upload.path}")
    private String uploadPath;

    /**
     * 显示图片
     * @param fileName
     * @return
     */
/*    @RequestMapping("show")
    public ResponseEntity show(String fileName){
        try {
            //System.out.println("1111");
            // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
            //uploadPath = D:/files/   fileName=332854a6-e3a1-4b90-973a-4fca79068017.jpg
            return ResponseEntity.ok(resourceLoader.getResource("file:" + uploadPath + fileName));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }*/
    /**
     * 上传方法
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping("/upLoadPic")
    public Object upLoadPic(@RequestParam MultipartFile file){
        String s = ftpUtil.upLoad(file);//调用上传方法
        return s;
    }

    /**
     * 下载方法
     * @param fileName
     * @param response
     */
    @RequestMapping("/downloadPic")
    public void downloadPic(String fileName, HttpServletResponse response){
        ftpUtil.downLoad(fileName,response);
    }

    /**
     * 显示Ftp图片
     * @param fileName
     * @return
     */
    @RequestMapping("/show")
    public ResponseEntity show(String fileName){
        try {
            //  ftp://192.168.1.14/98f20a5d-7304-41c7-ac5a-db07d2aaffd3.png
            return ResponseEntity.ok(resourceLoader.getResource("ftp://"+ftpConfig.getFtpUsername()+":"+ftpConfig.getFtpPassWord()+"@"+ ftpConfig.getRemoteIp()+ftpConfig.getRemotePath() + fileName));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }
    /**
     * 跳转分页列表
     * @return
     */
    @RequestMapping("/toNotice")
    public String to(){
        return "houtai/aboutweb/noticelist";
    }

    @ResponseBody //返回json
    @RequestMapping("/page")
    public Object page(@RequestBody Map map){
        //设置当前第几页和每页显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        //用PageInfo对结果进行包装
        PageInfo<Map> pageInfo =new PageInfo<Map>(noticeService.getPageByParam(map));
        Map resultMap =new HashMap();
        //获取当前页数据
        resultMap.put("pageData",pageInfo.getList()) ;
        //获取分页总数量
        resultMap.put("total", pageInfo.getTotal());
        return resultMap;
    }
    @ResponseBody
    @RequestMapping("/noticeType")
    public Object getTypeList(){
        return noticeService.getTypeList();
    }
    /**
     * 添加
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public Object add(@RequestBody Map map){
        return noticeService.add(map);
    }
    /**
     * 更新
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody Map map){
        try {
            return  noticeService.update(map);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    /**
     * 删除
     * @param noticeId
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete/{noticeid}")
    public Object delete(@PathVariable("noticeid") Integer noticeId){
        return noticeService.delete(noticeId);
    }

    /**
     * 批量删除
     * @param noticeIds
     * @return
     */
    @RequestMapping("/batchDel/{noticeIds}")
    @ResponseBody
    public Object batchDel(@PathVariable String noticeIds){
        return  noticeService.batchDelete(noticeIds);
    }
}
