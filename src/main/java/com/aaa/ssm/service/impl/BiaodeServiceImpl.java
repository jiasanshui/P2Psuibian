package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.*;
import com.aaa.ssm.service.BiaodeService;
import com.aaa.ssm.util.DEBXUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    private AduitBidDao aduitBidDao;

    @Autowired
    private TenderDao tenderDao;

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
     * 审核操作（初审）
     * 1.更新用户借款表审核状态
     * 2.往标的审核表中插入数据
     * @param map
     * @return
     */
    @Override
    public int edit(Map map) {
        //1.更新用户借款表审核状态（判断）
        Integer aduitresult = Integer.valueOf(map.get("ADUITRESULT")+"");
        int m=0;
        int n=0;
        if(aduitresult==1){//初审通过,开始招标
           m=biaodeDao.update(map);
        }else {//初审失败
            n=biaodeDao.edit(map);
        }
        //往标的审核表中插入数据
        int s = aduitBidDao.add(map);
        if (s!=0 &&( n!=0 || m!=0)){
            return 1;
        }
        return 0;
    }

    /**
     * 查询投标详情
     * @param borrownum
     * @return
     */
    @Override
    public List<Map> getTenderinfo(String borrownum) {

        return tenderDao.getTenderinfoByParam(borrownum);
    }

    /**
     * 招标中的标的分页列表
     * @param map
     * @return
     */
    @Override
    public List<Map> getPage(Map map) {
        return biaodeDao.getPage(map);
    }

    /**
     * 满标审核,
     * 1、更改标的状态
     * 2、往标的审核表中插入数据
     * @param map
     * @return
     */
    @Override
    public int updateBidState(Map map) {
        return biaodeDao.updateBidState(map);
    }

    /**
     * 获取待放款的投标信息
     * @param map
     * @return
     */
    @Override
    public List<Map> getPageByLoan(Map map) {
        return biaodeDao.getPageByLoan(map);
    }

    /**
     * 根据标的编号查询历史审核信息
     * @param borrownum
     * @return
     */
    @Override
    public Map getAduitBid(String borrownum) {
        List<Map> aduitBid = biaodeDao.getAduitBid(borrownum);
        if (aduitBid!=null&&aduitBid.size()>0){
           return aduitBid.get(0);
        }
        return null;
    }

    /**
     * 满标一审操作
     * 1.更新标的状态
     * 2.往标的审核表中插入数据
     * @param map
     * @return
     */
    @Override
    public int fkCheck(Map map) {
        //1.更新标的状态（判断）
        Integer aduitresult = Integer.valueOf(map.get("ADUITRESULT")+"");
        int m=0;
        int n=0;
        if(aduitresult==1){//满标一审通过
            m=biaodeDao.fkCheckOne(map);
        }else {//满标一审失败
            n=biaodeDao.fkCheck(map);
        }
        //往标的审核表中插入数据
        int s = aduitBidDao.add(map);
        if (s!=0 &&( n!=0 || m!=0)){
            return 1;
        }
        return 0;
    }
}
