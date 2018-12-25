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
    //依赖注入dao
    @Autowired
    private BorrowDao borrowDao;

    @Override
    public int add(Map map) {
        return borrowDao.add(map);
    }

    @Override
    public int update(Map map) {
        return borrowDao.update(map);
    }

    @Override
    public List<Map> getListByUsername(String BORROWNUM) {
        return borrowDao.getListByUsername(BORROWNUM);
    }
}
