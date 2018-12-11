package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.BorrowDao;
import com.aaa.ssm.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:BorrowServiceImpl
 * discription:
 * author:hulu
 * createTime:2018-12-11 15:15
 */
@Service
public class BorrowServiceImpl implements BorrowService {


    @Autowired
    private BorrowDao borrowDao;
    @Override
    public List<Map> getPayList() {
        System.out.println(borrowDao.getPayList()+"hjhgggg");
        return borrowDao.getPayList();
    }
    @Override
    public List<Map> getConditionList() {
        return borrowDao.getConditionList();
    }

}
