package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.AccountFlowDao;
import com.aaa.ssm.dao.HuankuanDao;
import com.aaa.ssm.entity.UserRegister;
import com.aaa.ssm.service.HuankuanService;
import com.aaa.ssm.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.net.Socket;
import java.util.List;
import java.util.Map;

/**
 * className:HuankuanServiceImpl
 * discription:
 * author:jiasanshui
 * createTime:2018-12-14 11:57
 */
@Service
public class HuankuanServiceImpl implements HuankuanService {

    @Autowired
    private HuankuanDao huankuanDao;

    @Autowired
    private AccountFlowDao accountFlowDao;

    @Autowired
    private HttpSession session;

    @Autowired
    private UserInfoService userInfoService;

    @Override
    public List<Map> getListByUName(Map map) {
        return huankuanDao.getListByUName(map);
    }

    @Override
    public List<Map> getReturnCurrent(Map map) {
        return huankuanDao.getReturnCurrent(map);
    }

    @Override
    public List<Map> getReturnInfo(Map map) {
        return huankuanDao.getReturnInfo(map);
    }

    @Override
    public List<Map> reGetReturnCurrent(Map map) {
        return huankuanDao.reGetReturnCurrent(map);
    }

    @Override
    public List<Map> noReturnInfo(Map map) {
        return huankuanDao.noReturnInfo(map);
    }

    @Override
    public List<Map> haveReturnInfo(Map map) {
        return huankuanDao.haveReturnInfo(map);
    }

    @Override
    public double getMoneyAll(String borrownum,String limits) {
        System.out.println(borrownum);
        String[] limitArr = limits.split(",");
        double moneyAll = 0;
        for (String s : limitArr) {
            Integer limit = Integer.valueOf(s);
            System.out.println("____________"+borrownum+","+limit);
            double money = huankuanDao.getMoneyAll(borrownum,limit);
            System.out.println(money);
            moneyAll += money;
        }
        System.out.println(moneyAll);
        return moneyAll;
    }

    @Override
    public Boolean balancePwd(String pwd,String username) {
        //结算时判断是否密码正确
        String password = huankuanDao.balancePwd(username);
        if (pwd.equals(password)){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public int balanceUpdateLimit(String limits,String borrownum) {
        String[] limitArr = limits.split(",");
        Boolean isUpdate = false;
        //还的总期数
        Integer sumTime=0;
        for (String s : limitArr) {
            Integer timelimit = Integer.valueOf(s);
            //修改还款记录表
            int balanceUpdateLimit = huankuanDao.balanceUpdateLimit(borrownum,timelimit);
            //修改订单表的已还期数
            if(balanceUpdateLimit>0){
                isUpdate = true;
            }else {
                isUpdate = false;
            }
            //判断当前还款期数是否是最后一期
            Map getlimits = huankuanDao.getlimits(borrownum);
            if("到期付本付息".equals(getlimits.get("PAYMENT"))){
                huankuanDao.updateStatus(borrownum);
            }else{
                if(timelimit==Integer.valueOf(getlimits.get("TIMELIMIT")+"")){
                    huankuanDao.updateStatus(borrownum);
                }
            }
            sumTime++;
        }
        int updateBorrowYetLimit = huankuanDao.updateBorrowYetLimit(borrownum, sumTime);
        if(updateBorrowYetLimit>0&&isUpdate==true){
            isUpdate=true;
        }else{
            isUpdate=false;
        }
        if(isUpdate){
            return 1;
        }else {
            return 0;
        }
    }


    @Override
    public String gethuankuanTime(String limits, String borrownum) {
        String[] limitArr = limits.split(",");
        String s = limitArr[0];
        Integer limit = Integer.valueOf(s);
        return huankuanDao.gethuankuanTime(limit,borrownum);
    }

    @Override
    public Boolean updateAmount(Map map) {
        String userName = (String)session.getAttribute("userName");
        UserRegister user = (UserRegister) session.getAttribute("user");
        Integer userId = user.getUserId();
        map.put("userId",userId);
        map.put("userName",userName);
        //修改用户余额
        int updateAmount = huankuanDao.updateAmount(map);
        //获取账户余额
        map.put("amount",userInfoService.getAmountByUName(userName));
        //添加资金流水
        int accountFlow = accountFlowDao.huankuanFlow(map);
        if(updateAmount>0&&accountFlow>0){
            return true;
        }
        return false;
    }
}
