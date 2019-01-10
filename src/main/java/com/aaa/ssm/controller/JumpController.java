package com.aaa.ssm.controller;

import com.aaa.ssm.entity.UserRegister;
import com.aaa.ssm.service.*;
import com.aaa.ssm.util.PageUtil;
import com.aaa.ssm.util.RandomUtil;
import com.aaa.ssm.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
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

    @Autowired
    private HuankuanService huankuanService;

    //依赖注入
    @Autowired
    private BorrowService borrowService;

    //依赖注入
    @Autowired
    private TenderService tenderService;

    //依赖注入
    @Autowired
    private WebService webService;

    @Autowired
    private  DepositsRecordService depositsRecordService;

    //依赖注入
    @Autowired
    private AccountFlowService accountFlowService;

    @Autowired
    private  RepayRecordService repayRecordService;

    /**
     * 跳转到前台首页
     * @return
     */
    @RequestMapping("/index")
    public String jumpIndex(Model model,HttpSession session) {
        String userName=(String) session.getAttribute("userName");
        if(userName!=null){
            List<Map> userList = userInfoService.getUserList(userName);
            //model.addAttribute("userList",userList);
            model.addAttribute("userstate",userList.get(0).get("STATEID"));
        }
        //显示房屋抵押招标
            List<Map> housePro = projectService.getHousePro();
            model.addAttribute("houseProList", housePro);
            Map map1 = new HashMap();
            map1.put("parama", "车辆");
            List<Map> listCar = projectService.getList(map1);
            Map map2 = new HashMap();
            map2.put("parama", "房屋");
            List<Map> listHouse = projectService.getList(map2);
            Map map3 = new HashMap();
            map3.put("parama", "信用");
            List<Map> listCredit = projectService.getList(map3);
            List<Map> webList = webService.getWebList();
            List<Map> mediaList = webService.getMediaList();
            model.addAttribute("webList", webList);
            model.addAttribute("mediaList", mediaList);

            model.addAttribute("listCar", listCar);
            model.addAttribute("listHouse", listHouse);
            model.addAttribute("listCredit", listCredit);
        System.out.println(model);
            return "qiantai/index";
        }

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping("/login")
    public String login(HttpSession session) {
        session.setAttribute("userName", null);
        session.setAttribute("user", null);
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
     * 跳转到我的订单页面
     * @return
     */
    @Autowired
    private  MyOrderService myOrderService;
    @RequestMapping("myorder")
    public Object myorder(HttpSession session,Model model){
        String username=(String) session.getAttribute("userName");
        List<Map> orderByInfo = myOrderService.getOrderByInfo(username);
        model.addAttribute("orderByInfo",orderByInfo);
        return "qiantai/myorder";
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
    public String list(Integer pageNo, Model model, HttpServletRequest request,HttpSession session,@RequestParam Map map){
        String userName=(String) session.getAttribute("userName");
        List<Map> userList = userInfoService.getUserList(userName);
        //分页总数量
        int pageSize=5;
        int tPageNo = pageNo==null?1:pageNo;
        map.put("pageNo",tPageNo);
        map.put("pageSize",pageSize);

        //查询分页，模糊查询
        //项目类型
        if("car".equals(map.get("pt"))){
            map.put("danbao","车辆抵押贷款");
        }if ("house".equals(map.get("pt"))){
            map.put("danbao","房屋抵押贷款");
        }if ("xinyong".equals(map.get("pt"))){
            map.put("danbao","信用贷款");
        }
        //年利率
        if("1".equals(map.get("bi"))){
            double startapr = 0;
            double endapr = 0.12;
            map.put("startapr",startapr);
            map.put("endapr",endapr);
        }if("2".equals(map.get("bi"))){
            double startapr = 0.12;
            double endapr = 0.14;
            map.put("startapr",startapr);
            map.put("endapr",endapr);
        }if("3".equals(map.get("bi"))){
            double startapr = 0.14;
            double endapr = 0.16;
            map.put("startapr",startapr);
            map.put("endapr",endapr);
        }if("4".equals(map.get("bi"))){
            double startapr = 0.16;
            double endapr = 1;
            map.put("startapr",startapr);
            map.put("endapr",endapr);
        }
        //期数
        if("1".equals(map.get("sm"))){
            int startlimit = 0;
            int endlimit = 1;
            map.put("startlimit",startlimit);
            map.put("endlimit",endlimit);
        }if("2".equals(map.get("sm"))){
            int startlimit = 1;
            int endlimit = 3;
            map.put("startlimit",startlimit);
            map.put("endlimit",endlimit);
        }if("3".equals(map.get("sm"))){
            int startlimit = 3;
            int endlimit = 6;
            map.put("startlimit",startlimit);
            map.put("endlimit",endlimit);
        }if("4".equals(map.get("sm"))){
            int startlimit = 6;
            int endlimit = 12;
            map.put("startlimit",startlimit);
            map.put("endlimit",endlimit);
        }
        //还款方式
        if("end".equals(map.get("rs"))){
            map.put("repayment","到期付本付息");
        }if("endmonth".equals(map.get("rs"))){
            map.put("repayment","按月付息，到期还本");
        }if("month".equals(map.get("rs"))){
            map.put("repayment","等额本息");
        }
        //if (StringUtil.isEmpty(msg)){
        String pageString = new PageUtil(tPageNo, pageSize, projectService.getPageCount(map), request).getPageString();
        List<Map> houseProAll = projectService.getHouseProAll(map);
        //pageUtil分页
        model.addAttribute("pageString",pageString);
        model.addAttribute("proList",houseProAll);
        model.addAttribute("map",userList);
        model.addAttribute("pt",map.get("pt"));
        model.addAttribute("bi",map.get("bi"));
        model.addAttribute("sm",map.get("sm"));
        model.addAttribute("rs",map.get("rs"));
        return "qiantai/list";
    }
    /**
     * 前台跳转到我要借款页面
     * 首先判断个人信息是否完善
     * 是否进行实名认证
     * 是否通过实名认证
     * 跳转借款页面
     * @return
     */
    @RequestMapping("/borrow")
    public String borrow(HttpSession session, Model model) {
        String username = (String) session.getAttribute("userName");
        if(StringUtil.isEmpty(username)){
            return "redirect:/jump/login";
        }else {
            Map map = userInfoService.getUser(username);
            try {
                Object msg = map.get("msg");
                if (StringUtil.isEmpty(msg)) { //审核通过
                    //调用生成随机数工具类生成随机数
                    String num = RandomUtil.getBorrowNumByTime();
                    model.addAttribute("num", num);
                    model.addAttribute("userName", username);
                    model.addAttribute("userid", map.get("USERID"));
                    model.addAttribute("realName", map.get("REALNAME"));
                    model.addAttribute("creditedu", map.get("CREDITEDU"));
                    return "qiantai/borrow";
                } else {
                    return "qiantai/renzheng";
                }
            } catch (Exception e) {

            }
        }
        return "";
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
    public String personal(HttpSession session, Model model,Integer pageNo,@RequestParam Map map,HttpServletRequest request){
        String userName = (String)session.getAttribute("userName");
        if(StringUtil.isEmpty(userName)){
            return "redirect:/jump/login";
        }else{
            UserRegister user=(UserRegister) session.getAttribute("user");
            Integer userId = user.getUserId();
            map.put("userId",userId);
            Map accountInfo=userInfoService.getUserAccount(userId);
            model.addAttribute("account",accountInfo);
            //分页总数量
            int pageSize=3;
            int tPageNo = pageNo==null?1:pageNo;
            map.put("pageNo",tPageNo);
            map.put("pageSize",pageSize);

            model.addAttribute("recordByDeposits", depositsRecordService.getTender(map));
            String pageString = new PageUtil(tPageNo, pageSize, depositsRecordService.getPageCount(map), request).getPageString();
            //pageUtil分页
            model.addAttribute("pageString",pageString);
            //查询账户余额
            model.addAttribute("amount",userInfoService.getAmountByUName(userName));
            return "qiantai/personal";
        }
    }
    /**
     * 跳转到关于我们页面
     * @return
     */
    @RequestMapping("/about")
    public String about(Model model) {
        List<Map> companyList = webService.getCompanyList();
        model.addAttribute("companyList", companyList);
        return "qiantai/about";
    }
    /**
     * 跳转到账户设置页面
     * @return
     */
    @RequestMapping("/account_setting")
    public String account_setting() {
        return "qiantai/account_setting";
    }

    /**
     * 跳转到兑换历史页面
     * @return
     */
    @RequestMapping("/change_history")
    public String change_history() {
        return "qiantai/change_history";
    }
    /**
     * 跳转到公司公告详细页面
     * @return
     */
    @RequestMapping("/company_announce")
    public String company_announce(Model model, Integer noticeid) {
        List<Map> lists = webService.getList(noticeid);
        model.addAttribute("lists", lists);
        return "qiantai/company_announce";
    }

    /**
     * 跳转到公司联系我们页面
     * @return
     */
    @RequestMapping("/contact_us")
    public String contact_us() {
        return "qiantai/contact_us";
    }

    /**
     * 跳转到公司投资记录页面
     * @return
     */
    @RequestMapping("/deposits_record")
    public Object deposits_record( Model model,HttpSession session,@RequestParam Map map,HttpServletRequest request) {
        UserRegister user=(UserRegister) session.getAttribute("user");
        if (user==null){
            return "qiantai/login";
        }else{
            Integer userId = user.getUserId();
            map.put("userId",userId);
            Map accountInfo=userInfoService.getUserAccount(userId);
            model.addAttribute("account",accountInfo);
            //累计投资金额
            double money=userInfoService.getTouderMoney(userId);
            model.addAttribute("touderMoney",money);
            //获取分页总数量
            int pageCount = depositsRecordService.getPageCount(map);
            int pageSize=8;
            int pageNo=0;
            Object tempPageNo=map.get("pageNo");
            if (StringUtil.isEmpty(tempPageNo)){
                pageNo=1;
            }else {
                pageNo=Integer.valueOf(tempPageNo+"");
            }
            map.put("pageSize",pageSize);
            map.put("pageNo",pageNo);
            //分页工具使用
            String pageString = new PageUtil(pageNo, pageSize, pageCount, request).getPageString();
            //List<Map> recordByDeposits = depositsRecordService.getTender(map);
            model.addAttribute("recordByDeposits",depositsRecordService.getTender(map));
            model.addAttribute("pageString", pageString);
            return "qiantai/deposits_record";
        }
    }
    /**
     * 跳转到投资页面
     * @return
     */
    @RequestMapping("/infor")
    public String infor(HttpSession session, Model model, String BORROWNUM) {
        String userName = (String) session.getAttribute("userName");
        if (userName == null) {
            return "qiantai/login";
        } else {
            List<Map> infoList = userInfoService.getUserList(userName);
            List<Map> listByUsername = borrowService.getListByBorrowNum(BORROWNUM);
            List<Map> pageList = tenderService.getPage(BORROWNUM);
            List<Map> borrowList = projectService.getBorrowList(BORROWNUM);
            model.addAttribute("uList", listByUsername);
            model.addAttribute("pList", pageList);
            model.addAttribute("borrowList", borrowList);
            model.addAttribute("infoList", infoList);
            //根据用户名去获取用户信息
            return "qiantai/infor";
        }

    }

    /**
     * 跳转到招贤纳士页面
     * @return
     */
    @RequestMapping("/join_us")
    public String join_us() {
        return "qiantai/join_us";
    }

    /**
     * 跳转到法律声明页面
     * @return
     */
    @RequestMapping("/legal_notice")
    public String legal_notice() {
        return "qiantai/legal_notice";
    }
    /**
     * 跳转到法律政策页面
     * @return
     */
    @RequestMapping("/low_policy")
    public String low_policy() {
        return "qiantai/low_policy";
    }

    /**
     * 跳转到管理团队页面
     * @return
     */
    @RequestMapping("/manage_team")
    public String manage_team(Model model) {
        List<Map> teamList = webService.getTeamList();
        model.addAttribute("teamList", teamList);
        return "qiantai/manage_team";
    }

    /**
     * 跳转到媒体报道页面
     * @return
     */
    @RequestMapping("/media_report")
    public String media_report(Model model){
       List<Map> mediaList=webService.getMediaList();
        model.addAttribute("mediaList",mediaList);
        return "qiantai/media_report";
    }

    /**
     * 跳转到回款计划页面
     * @return
     */
    @RequestMapping("/money_plan")
    public String money_plan() {
        return "qiantai/money_plan";
    }
    /**
     * 跳转到资金记录页面
     * @return
     */
    @RequestMapping("/money_record")
    public String money_record(Model model,HttpSession session,@RequestParam Map map,HttpServletRequest request){
        UserRegister user=(UserRegister) session.getAttribute("user");
        if (user==null){
            return "qiantai/login";
        }else{
            Integer userId = user.getUserId();
            map.put("userId",userId);
            //获取分页总数量
            int pageCount =accountFlowService.getPageCount(map);
            int pageSize=8;
            int pageNo=0;
            Object tempPageNo=map.get("pageNo");
            if (StringUtil.isEmpty(tempPageNo)){
                pageNo=1;
            }else {
                pageNo=Integer.valueOf(tempPageNo+"");
            }
            map.put("pageSize",pageSize);
            map.put("pageNo",pageNo);
            //分页工具使用
            String pageString = new PageUtil(pageNo, pageSize, pageCount, request).getPageString();
            //List<Map> recordByDeposits = accountFlowService.getAccountFlow(map);
            //model.addAttribute("accountflow",accountFlowService.getAccountFlow(map));
            model.addAttribute("pageString", pageString);
            if(StringUtil.isEmpty(map.get("selecttime"))){
                System.out.println("138920405-");
                map.put("selecttime","");
            }
            if(StringUtil.isEmpty(map.get("flowtypeid"))){
                System.out.println("138920405---------------");
                map.put("flowtypeid","");
            }
            model.addAttribute("map",map);
            return "qiantai/money_record";
        }
    }

    /**
     * 跳转到资费说明页面
     * @return
     */
    @RequestMapping("/money_speak")
    public String money_speak() {
        return "qiantai/money_speak";
    }

    /**
     * 跳转到我的红包页面
     * @return
     */
    @RequestMapping("/myred_packets")
    public String myred_packets() {
        return "qiantai/myred_packets";
    }

    /**
     * 跳转到合作伙伴页面
     * @return
     */
    @RequestMapping("/partner")
    public String partner(Model model) {
        List<Map> partnerList = webService.getPartnerList();
        model.addAttribute("partnerList", partnerList);
        return "qiantai/partner";
    }

    /**
     * 跳转到网站公告页面
     * @return
     */
    @RequestMapping("/site_notice")
    public String site_notice(Model model) {
        List<Map> webList = webService.getWebList();
        model.addAttribute("webList", webList);
        return "qiantai/site_notice";
    }

    /**
     * 跳转到系统消息页面
     * @return
     */
    @RequestMapping("/system_message")
    public String system_message() {
        return "qiantai/system_message";
    }

    /**
     * 跳转到团队风采页面
     * @return
     */
    @RequestMapping("/team_style")
    public String team_style(Model model) {
        List<Map> teamSList = webService.getTeamSList();
        model.addAttribute("teamSList", teamSList);
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
    public String withdraw1() {
        return "qiantai/withdraw1";
    }

    /**
     * 跳转到还款页面
     * @return
     */
    @RequestMapping("/huankuan")
    public String huankuan() {
        return "qiantai/huankuan";
    }

    /**
     * 跳转到我的还款页面
     * @return
     */
    @RequestMapping("/yihuankuan")
    public Object yihuankuan(Model model,HttpSession session,@RequestParam Map map,HttpServletRequest request) {
        UserRegister user=(UserRegister) session.getAttribute("user");
        if (user==null){
            return "qiantai/login";
        }else{
            String uname = user.getUname();
            map.put("userName",uname);
            Map userAccount = userInfoService.getUser(uname);
            model.addAttribute("account",userAccount);
            //获取分页总数量
           int pageCount = repayRecordService.getPageCount(map);
            int pageSize=10;
            int pageNo=0;
            Object tempPageNo=map.get("pageNo");
            if (StringUtil.isEmpty(tempPageNo)){
                pageNo=1;
            }else {
                pageNo=Integer.valueOf(tempPageNo+"");
            }
            map.put("pageSize",pageSize);
            map.put("pageNo",pageNo);
            //分页工具使用
            String pageString = new PageUtil(pageNo, pageSize, pageCount, request).getPageString();
            model.addAttribute("recordByRepay",repayRecordService.getRepayPage(map));
            model.addAttribute("pageString", pageString);
            return "qiantai/reimbursement";
        }

    }

    /**
     * 跳转到提现页面
     * @return
     */
    @RequestMapping("/pay1")
    public String pay1() {
        return "qiantai/pay1";
    }

    /**
     * 跳转到投标页面
     * @return
     */
    @RequestMapping("/toubiao")
    public String toubiao(HttpSession session, Model model, String BORROWNUM) {
        String username = (String) session.getAttribute("userName");
        //根据用户名去获取用户信息
        String num = RandomUtil.getBorrowNumByTime();
        List<Map> list = userInfoService.getUserList(username);
        List<Map> listByUsername = borrowService.getListByBorrowNum(BORROWNUM);
        model.addAttribute("realName", list.get(0).get("REALNAME"));
        model.addAttribute("uid", list.get(0).get("USERID"));
        model.addAttribute("bankNum", list.get(0).get("BANKNUM"));
        model.addAttribute("amount", list.get(0).get("AMOUNT"));
        model.addAttribute("freezamount", list.get(0).get("FREEZAMOUNT"));
        model.addAttribute("BORROWNUM", BORROWNUM);
        model.addAttribute("bList", listByUsername);
        model.addAttribute("num", num);
        return "qiantai/toubiao";
    }

    /**
     * 跳转到付款页面
     *
     * @return
     */
    @RequestMapping("/fukuan")
    public String fukuan(String borrownum,String limits,Model model){
        double moneyAll = huankuanService.getMoneyAll(borrownum,limits);
        model.addAttribute("allMoney",moneyAll);
        model.addAttribute("limits",limits);
        model.addAttribute("borrownum",borrownum);
        return "qiantai/fukuan/fukuan";
    }

    /**
     * 跳转到付款成功页面
     * @return
     */
    @RequestMapping("/fkcg")
    public String fkcg() {
        return "qiantai/fukuan/fkcg";
    }

    @RequestMapping("bankcard")
    public Object bankcard(){
        return "qiantai/bankcard";
    }
}
