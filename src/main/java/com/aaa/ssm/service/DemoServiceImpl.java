package com.aaa.ssm.service;

import com.aaa.ssm.dao.DemoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:DemoServiceImpl
 * discription:
 * author:jiasanshui
 * createTime:2018-11-29 19:45
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoDao demoDao;

    @Override
    public List<Map> getList() {
        return demoDao.getList();
    }
}
