package com.aaa.ssm.controller;

import com.aaa.ssm.entity.UserRegister;
import com.aaa.ssm.service.AccountFlowService;
import com.aaa.ssm.service.UserInfoService;
import com.aaa.ssm.util.FtpUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
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

    @Autowired
    private AccountFlowService accountFlowService;

    @Autowired
    private HttpSession session;
    //private final ResourceLoader resourceLoader;

    /*@Autowired
    public UserInfoController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }*/
    //取出配置文件中upload.path的值  赋给uploadPath类变量
    @Value(value = "${upload.path}")
    private String uploadPath;

    @Autowired
    private FtpUtil ftpUtil;
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
    /*@RequestMapping("show")
    public ResponseEntity show(String fileName){
        try {
            // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
            //uploadPath = D:/files/   fileName=332854a6-e3a1-4b90-973a-4fca79068017.jpg
            return ResponseEntity.ok(resourceLoader.getResource("file:" + uploadPath + fileName));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }*/

    /**
     * 个人信息认证审核
     * @return
     */
    @ResponseBody
    @RequestMapping("/edit")
    public Object edit(@RequestBody Map map){

        return userInfoService.edit(map);
    }

    /**
     * 根据用户ID查询用户信息
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public Object getAllList(Integer userId){
        List<Map> allList = userInfoService.getAllList(userId);
        return allList.get(0);

    }

    /**
     * 根据用户ID查询历史
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/history")
    private Object getHistory(@RequestBody Map map){
        return userInfoService.getHistory(map);
    }

    /**
     * 上传头像
     * @param headPhoto
     * @return
     */
    @RequestMapping("uploadPhoto")
    public Object uploadPhoto(@RequestParam Map map,@RequestParam MultipartFile headPhoto){
        String headPhotoName = ftpUtil.upLoad(headPhoto);
        map.put("headphoto",headPhotoName);
        //修改个人信息头像
        int updateHeadPhoto = userInfoService.updateHeadPhoto(map);
        if(updateHeadPhoto>0){
            return "forward:/jump/personal";
        }
        return "forward:/jump/index";
    }

    /**
     * 判断用户是否上传了头像
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("isBindHP")
    public Object isBindHP(){
        String userName = (String)session.getAttribute("userName");
        String hFileName = userInfoService.getHPByUNname(userName);
        if(hFileName!=null&&hFileName!=""){
            return hFileName;
        }
        return 0;
    }

    /**
     * 提现
     * @param map
     * @return
     */
    @RequestMapping("withdraw")
    public Object withdraw(@RequestParam Map map){
        UserRegister user = (UserRegister)session.getAttribute("user");
        Integer userId = user.getUserId();
        map.put("userId",userId);
        Boolean isTrue = userInfoService.withdraw(map);
        if(isTrue){
            return "redirect:/jump/withdraw1";
        }
        return "redirect:/jump/withdraw1";
    }


    @RequestMapping("chongzhi")
    public Object chongzhi(@RequestParam Map map){
        UserRegister user = (UserRegister)session.getAttribute("user");
        Integer userId = user.getUserId();
        map.put("userId",userId);
        Boolean isTrue = userInfoService.chongzhi(map);
        if(isTrue){
            return "redirect:/jump/pay1";
        }
        return "";
    }
}
