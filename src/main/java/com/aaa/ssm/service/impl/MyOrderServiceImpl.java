package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.MyorderDao;
import com.aaa.ssm.service.MyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:MyOrderServiceImpl
 * discription:
 * author:yb
 * createTime:2018-12-26 16:06
 */
@Service
public class MyOrderServiceImpl implements MyOrderService {
    @Autowired
    private  MyorderDao myorderDao;
    @Override
    public List<Map> getOrderByInfo(String username) {
        return myorderDao.getOrderByInfo(username);
    }
}
