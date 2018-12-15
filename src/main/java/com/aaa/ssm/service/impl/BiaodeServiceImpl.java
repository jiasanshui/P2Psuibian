package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.BiaodeDao;
import com.aaa.ssm.dao.BohuiDao;
import com.aaa.ssm.dao.RepayDao;
import com.aaa.ssm.service.BiaodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        /*Integer limit = Integer.valueOf(map.get("limit").toString());
        for (int i = 1; i <= limit; i++) {
            Date date = new Date();
            map.put("limit1",i);
            repayDao.add(map);
        }*/
        //1.更新用户借款表审核状态，招标开始时间
        int m = biaodeDao.update(map);
        //2.向还款表中插入数据(计算利息)
        int n = repayDao.add(map);
        if (m!=0 && n!=0){
            return 1;
        }
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
