package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.HuankuanDao;
import com.aaa.ssm.service.HuankuanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        int moneyAll = 0;
        for (String s : limitArr) {
            Integer limit = Integer.valueOf(s);
            int money = huankuanDao.getMoneyAll(borrownum, limit);
            moneyAll += money;
        }
        return moneyAll;
    }

    @Override
    public Boolean balancePwd(String pwd,String username) {
        //结算时判断是否密码正确
        System.out.println(pwd+" "+username);
        String password = huankuanDao.balancePwd(username);
        System.out.println(password);
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
        for (String s : limitArr) {
            Integer timelimit = Integer.valueOf(s);
            int i = huankuanDao.balanceUpdateLimit(borrownum,timelimit);
            if(i>0){
                isUpdate = true;
            }else {
                isUpdate = false;
            }
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
}
