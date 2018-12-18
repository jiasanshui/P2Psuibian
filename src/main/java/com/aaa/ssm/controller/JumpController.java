package com.aaa.ssm.controller;

import com.aaa.ssm.service.BorrowService;
import com.aaa.ssm.service.ProjectService;
import com.aaa.ssm.service.TenderService;
import com.aaa.ssm.util.RandomUtil;
import com.aaa.ssm.util.StringUtil;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import com.aaa.ssm.service.UserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * className:jumpController
 * discription:前台跳转
 * author:yb
 * createTime:2018-12-07 17:24
 */
@Controller
@RequestMapping("/jump")
public class JumpController {
    @Autowired
    private ProjectService projectService;

    //依赖注入service层
    @Autowired
    private UserInfoService userInfoService;

    //依赖注入
    @Autowired
    private BorrowService borrowService;

    //依赖注入
    @Autowired
    private TenderService tenderService;

    /**
     * 跳转到前台首页
     * @return
     */
    @RequestMapping("/index")
    public String jumpIndex(Model model){
        //显示房屋抵押招标
        List<Map> housePro = projectService.getHousePro();
        model.addAttribute("houseProList",housePro);
        return "qiantai/index";
    }

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping("/login")
    public String login(HttpSession session){
        session.setAttribute("userName",null);
        session.setAttribute("user",null);
        return "qiantai/login";
    }

    /**
     * 跳转到注册页面
     * @return
     */
    @RequestMapping("/register")
    public String register(){
        return "qiantai/register";
    }

    /**
     * 跳转到认证页面
     * @return
     */
    @RequestMapping("renzheng")
    public Object renzheng(){
        return "qiantai/renzheng";
    }

    /**
     * 跳转到注册成功页面
     * @return
     */

    @RequestMapping("/register1")
    public String register1(){
        return "qiantai/register1";
    }
    /**
     * 跳转到我要投资页面
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model){
        List<Map> houseProAll = projectService.getHouseProAll();
        model.addAttribute("proList",houseProAll);
        System.out.println(houseProAll);
        return "qiantai/list";
    }
    /**
     * 跳转到我要借款页面
     * @return
     */
    @RequestMapping("/borrow")
    public String borrow(HttpSession session, Model model){
        String username=(String) session.getAttribute("userName");
        //根据用户名去获取用户信息
        Map map= userInfoService.getUser(username);
        Object msg = map.get("msg");
        if (StringUtil.isEmpty(msg)){
            //审核通过
            //调用生成随机数工具类生成随机数
            String num = RandomUtil.getBorrowNumByTime();
            model.addAttribute("num",num);
            model.addAttribute("userName",username);
            model.addAttribute("realName",map.get("REALNAME"));
            return "qiantai/borrow";

        }
        return "qiantai/renzheng";
    }
    /**
     * 跳转到安全保障页面
     * @return
     */
    @RequestMapping("/help")
    public String help(){
        return "qiantai/help";
    }
    /**
     * 跳转到我的账户页面
     * @return
     */
    @RequestMapping("/personal")
    public String personal(){
        return "qiantai/personal";
    }
    /**
     * 跳转到关于我们页面
     * @return
     */
    @RequestMapping("/about")
    public String about(){
        return "qiantai/about";
    }
    /**
     * 跳转到账户设置页面
     * @return
     */
    @RequestMapping("/account_setting")
    public String account_setting(){
        return "qiantai/account_setting";
    }
    /**
     * 跳转到兑换历史页面
     * @return
     */
    @RequestMapping("/change_history")
    public String change_history(){
        return "qiantai/change_history";
    }
    /**
     * 跳转到公司公告详细页面
     * @return
     */
    @RequestMapping("/company_announce")
    public String company_announce(){
        return "qiantai/company_announce";
    }
    /**
     * 跳转到公司联系我们页面
     * @return
     */
    @RequestMapping("/contact_us")
    public String contact_us(){
        return "qiantai/contact_us";
    }
    /**
     * 跳转到公司投资记录页面
     * @return
     */
    @RequestMapping("/deposits_record")
    public String deposits_record(){
        return "qiantai/deposits_record";
    }
    /**
     * 跳转到借款详细页面
     * @return
     */
    @RequestMapping("/infor")
    public String infor(HttpSession session,Model model){
        String userName=(String) session.getAttribute("userName");
        List<Map> list = userInfoService.getUserList(userName);
        Integer userid = Integer.valueOf(list.get(0).get("USERID")+"");
        List<Map> listByUsername = borrowService.getListByUsername(userName);
        List<Map> pageList = tenderService.getPage(userid);
        model.addAttribute("uList",listByUsername);
        model.addAttribute("pList",pageList);
        //根据用户名去获取用户信息
        return "qiantai/infor";
    }
    /**
     *
     * 跳转到招贤纳士页面
     * @return
     */
    @RequestMapping("/join_us")
    public String join_us(){
        return "qiantai/join_us";
    }
    /**
     * 跳转到法律声明页面
     * @return
     */
    @RequestMapping("/legal_notice")
    public String legal_notice(){
        return "qiantai/legal_notice";
    }
    /**
     * 跳转到法律政策页面
     * @return
     */
    @RequestMapping("/low_policy")
    public String low_policy(){
        return "qiantai/low_policy";
    }
    /**
     * 跳转到管理团队页面
     * @return
     */
    @RequestMapping("/manage_team")
    public String manage_team(){
        return "qiantai/manage_team";
    }
    /**
     * 跳转到媒体报道页面
     * @return
     */
    @RequestMapping("/media_report")
    public String media_report(){
        return "qiantai/media_report";
    }
    /**
     * 跳转到回款计划页面
     * @return
     */
    @RequestMapping("/money_plan")
    public String money_plan(){
        return "qiantai/money_plan";
    }
    /**
     * 跳转到资金记录页面
     * @return
     */
    @RequestMapping("/money_record")
    public String money_record(){
        return "qiantai/money_record";
    }
    /**
     * 跳转到资费说明页面
     * @return
     */
    @RequestMapping("/money_speak")
    public String money_speak(){
        return "qiantai/money_speak";
    }
    /**
     * 跳转到我的红包页面
     * @return
     */
    @RequestMapping("/myred_packets")
    public String myred_packets(){
        return "qiantai/myred_packets";
    }

    /**
     * 跳转到合作伙伴页面
     * @return
     */
    @RequestMapping("/partner")
    public String partner(){
        return "qiantai/partner";
    }
    /**
     * 跳转到网站公告页面
     * @return
     */
    @RequestMapping("/site_notice")
    public String site_notice(){
        return "qiantai/site_notice";
    }
    /**
     * 跳转到系统消息页面
     * @return
     */
    @RequestMapping("/system_message")
    public String system_message(){
        return "qiantai/system_message";
    }
    /**
     * 跳转到团队风采页面
     * @return
     */
    @RequestMapping("/team_style")
    public String team_style(){
        return "qiantai/team_style";
    }
    /**
     * 跳转到充值页面
     * @return
     */
    @RequestMapping("/withdraw")
    public String withdraw(){
        return "qiantai/withdraw";
    }
    /**
     * 跳转到充值页面
     * @return
     */
    @RequestMapping("/withdraw1")
    public String withdraw1(){
        return "qiantai/withdraw1";
    }

    /**
     * 跳转到还款页面
     * @return
     */
    @RequestMapping("/huankuan")
    public String huankuan(){
        return "qiantai/huankuan";
    }
    /**
     * 跳转到我的还款页面
     * @return
     */
    @RequestMapping("/yihuankuan")
    public String yihuankuan(){
        return "qiantai/reimbursement";
    }
    /**
     * 跳转到提现页面
     * @return
     */
    @RequestMapping("/pay1")
    public String pay1(){
        return "qiantai/pay1";
    }

    /**
     * 跳转到投标页面
     * @return
     */
    @RequestMapping("/toubiao")
    public String toubiao(HttpSession session,Model model){
        String username=(String) session.getAttribute("userName");
        //根据用户名去获取用户信息
        List<Map> list = userInfoService.getUserList(username);
        model.addAttribute("realName",list.get(0).get("REALNAME"));
        model.addAttribute("uid",list.get(0).get("USERID"));
        return "qiantai/toubiao";
    }


}
