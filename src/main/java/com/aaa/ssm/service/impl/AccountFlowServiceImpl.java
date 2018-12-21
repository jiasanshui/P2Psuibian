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
}
