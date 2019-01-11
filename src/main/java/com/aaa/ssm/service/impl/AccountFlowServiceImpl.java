package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.AccountFlowDao;
import com.aaa.ssm.service.AccountFlowService;
import com.aaa.ssm.util.DEBXUtil;
import com.aaa.ssm.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:AccountFlowServiceImpl
 * discription:
 * author:xiefuzhi
 * createTime:2018-12-21 10:23
 */
@Service
public class AccountFlowServiceImpl implements AccountFlowService {
    //依赖注入
    @Autowired
    private AccountFlowDao accountFlowDao;

    @Override
    public List<Map> getPageByparams(String userid) {
        return accountFlowDao.getPageByparams(userid);
    }

    @Override
    public int add(Map map) {
        return accountFlowDao.add(map);
    }

    @Override
    public int update(Map map) {
        return accountFlowDao.update(map);
    }

    /**
     * 查询流水类型
     * @return
     */
    @Override
    public List<Map> getFlowtype() {
        return accountFlowDao.getFlowtype();
    }

    /**
     * 获取分页总数量
     * @param map
     * @return
     */
    @Override
    public int getPageCount(Map map) {
        List<Map> pageCount=null;
        if(StringUtil.isEmpty(map.get("selecttime"))){
            pageCount = accountFlowDao.getPageCount(map);
        }else if (Integer.valueOf(map.get("selecttime")+"")==0){//今天
            map.put("selecttoday",2);
            pageCount = accountFlowDao.getPageCount(map);
        }else if (Integer.valueOf(map.get("selecttime")+"")==1){//近一个月
            map.put("selectmonth",1);
            pageCount = accountFlowDao.getPageCount(map);
        }else if (Integer.valueOf(map.get("selecttime")+"")==6){//近6个月
            map.put("selectsix",6);
            pageCount = accountFlowDao.getPageCount(map);
        }else {//最近一周
            map.put("selectseven",7);
            pageCount = accountFlowDao.getPageCount(map);
        }
        //判断集合，如果不为空，返回总数量
        if(pageCount!=null&&pageCount.size()>0){
            return Integer.valueOf(pageCount.get(0).get("CNT")+"");
        }
        return 0;
    }

    /**
     * 获取资金记录流水分页
     * @param map
     * @return
     */
    @Override
    public List<Map> getAccountFlow(Map map) {
        int pageNo=map.get("pageNo")==null?1:Integer.valueOf(map.get("pageNo")+"");
        int pageSize = map.get("pageSize")==null?10:Integer.valueOf(map.get("pageSize")+"");
        map.put("start",(pageNo-1)*pageSize);
        map.put("end",pageNo*pageSize+1);
        List<Map> accountFlow=null;
        if(StringUtil.isEmpty(map.get("selecttime"))){
            accountFlow = accountFlowDao.getAccountFlow(map);
        }else if (Integer.valueOf(map.get("selecttime")+"")==0){//今天
            map.put("selecttoday",2);
            accountFlow = accountFlowDao.getAccountFlow(map);
        }else if (Integer.valueOf(map.get("selecttime")+"")==1){//近一个月
            map.put("selectmonth",1);
            accountFlow = accountFlowDao.getAccountFlow(map);
        }else if (Integer.valueOf(map.get("selecttime")+"")==6){//近6个月
            map.put("selectsix",6);
            accountFlow = accountFlowDao.getAccountFlow(map);
        }else {//最近一周
            map.put("selectseven",7);
            accountFlow = accountFlowDao.getAccountFlow(map);
        }
        return accountFlow;
    }

    /**
     * 账户总揽中资金记录3条流水
     * @param map
     * @return
     */
    @Override
    public List<Map> getThreeFlow(Map map) {
        return accountFlowDao.getThreeFlow(map);
    }

    /**
     * 账户总揽中回款计划
     * @param map
     * @return
     */
    @Override
    public List<Map> getBackMoney(Map map) {
        List<Map> backMoneyList=accountFlowDao.getBackMoney(map);
        System.out.println(backMoneyList);
        if(backMoneyList!=null && backMoneyList.size()>0){
            for (Map map1 : backMoneyList) {
                Double tamount= Double.parseDouble(map1.get("TAMOUNT")+"");
                int totolmonth=Integer.valueOf(map1.get("TIMELIMIT")+"");
                Double tapr= Double.parseDouble(map1.get("TAPR")+"");
                double backMoney = DEBXUtil.getPrincipalInterestCount(tamount, tapr, totolmonth);
                map1.put("backMoney",backMoney);
            }
            return backMoneyList;
        }
        return null;
    }

}
