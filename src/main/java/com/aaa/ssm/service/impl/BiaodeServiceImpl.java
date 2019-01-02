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
    private AduitBidDao aduitBidDao;

    @Autowired
    private TenderDao tenderDao;

    /**
     * 用户借款标的列表查询 (信用贷款 待审核)
     * @param map
     * @return
     */
    @Override
    public List<Map> getList(Map map) {
        return biaodeDao.getList(map);
    }

    /**
     * 用户借款标的列表查询 (抵押贷款 待审核)
     * @param map
     * @return
     */
    @Override
    public List<Map> getListOne(Map map) {
        return biaodeDao.getListOne(map);
    }

    /**
     * 审核通过
     * 更新用户借款表审核状态，招标开始时间
     * @param map
     * @return
     */
    @Override
    public int update(Map map) {
        //1.更新用户借款表审核状态，招标开始时间
        return biaodeDao.update(map);

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
        //1.更新用户借款标审核状态（判断）
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
