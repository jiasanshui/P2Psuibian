package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.BiaodeDao;
import com.aaa.ssm.dao.BohuiDao;
import com.aaa.ssm.dao.RepayDao;
import com.aaa.ssm.service.BiaodeService;
import com.aaa.ssm.util.DEBXUtil;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * className:BiaodeServiceImpl
 * discription:
 * author:fhm
 * createTime:2018-12-15 10:09
 */
@Service
public class BiaodeServiceImpl implements BiaodeService{
    //依赖注入
    @Autowired
    private BiaodeDao biaodeDao;

    @Autowired
    private RepayDao repayDao;

    @Autowired
    private BohuiDao bohuiDao;

    @Override
    public List<Map> getList(Map map) {
        return biaodeDao.getList(map);
    }

    /**
     * 审核通过
     * 1、更新用户借款表审核状态，招标开始时间
     * 2、向还款表中插入数据(计算利息)
     * {"PAYMENT":"分期","APR":0.15,"BORROWMONEY":300000,"QUANTITY":"1",
     * "APPLICANT":"大志","ROW_ID":1,"COST":400000,"DES":"结婚买房",
     * "TIMELIMIT":"1","USERNAME":"test123","PURPOSE":"买车","TEL":"1666666666","
     * DANBAOSTYLE":"房屋","BORROWNUM":"BD0000002","BORROWID":3}
     * @param map
     * @return
     */
    @Override
    public int update(Map map) {
        //1.更新用户借款表审核状态，招标开始时间
        int n = biaodeDao.update(map);
        //本金
        double benjin = Double.parseDouble(map.get("BORROWMONEY").toString());
        //年利率
        double apr = Double.parseDouble(map.get("APR").toString());
        //还款总月数
        int totalMonth=Integer.valueOf(map.get("TIMELIMIT")+"");
        //调用等额本息工具类，算出总利息
        double lixi = DEBXUtil.getInterestCount(benjin, apr, totalMonth);
        map.put("LIXI",lixi);
        boolean isAdd=true;
        Integer limit = Integer.valueOf(map.get("TIMELIMIT").toString());
        for (int i = 1; i <= limit; i++) {
            //获取当前系统时间
            Calendar calendar = Calendar.getInstance();
            //当前月份加i
            calendar.add(Calendar.MONTH,i);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String format = df.format(calendar.getTime());
            map.put("TIMELIMIT",i);
            map.put("REPAYLIMIT",format);
            //2.向还款表中插入数据
            System.out.println("当前期数："+i);
            int m = repayDao.add(map);
            if (m<1){
                isAdd=false;
            }
        }
        if (isAdd==true && n!=0)
            return 1;
        else
            return 0;
    }

    /**
     * 审核驳回
     * 1.更新用户借款表审核状态
     * 2.往借款驳回表中插入数据
     * @param map
     * @return
     */
    @Override
    public int edit(Map map) {
        //1.更新用户借款表审核状态
        int m = biaodeDao.edit(map);
        int n = bohuiDao.addBohui(map);
        if (m!=0 && n!=0){
            return 1;
        }
        return 0;
    }
}
