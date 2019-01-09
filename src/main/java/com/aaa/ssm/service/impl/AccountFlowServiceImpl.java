package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.AccountFlowDao;
import com.aaa.ssm.service.AccountFlowService;
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
        List<Map> pageCount = accountFlowDao.getPageCount(map);
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
        return accountFlowDao.getAccountFlow(map);
    }
}
