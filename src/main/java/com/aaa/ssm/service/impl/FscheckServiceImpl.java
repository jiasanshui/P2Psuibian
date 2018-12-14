package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.FscheckDao;
import com.aaa.ssm.service.FscheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:FscheckServiceImpl
 * discription:
 * author:yb
 * createTime:2018-12-14 17:23
 */
@Service
public class FscheckServiceImpl implements FscheckService {
    @Autowired
    private FscheckDao fscheckDao;

    @Override
    public List<Map> getList(Map map) {
        return fscheckDao.getList(map);
    }

    @Override
    public int add(Map map) {
        return fscheckDao.add(map);
    }
}
