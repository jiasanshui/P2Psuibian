package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.EchartDao;
import com.aaa.ssm.service.EchartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:EchartServiceImpl
 * discription:
 * author:hulu
 * createTime:2018-12-28 13:15
 */
@Service
public class EchartServiceImpl implements EchartService {
    @Autowired
    private EchartDao echartDao;
    @Override
    public List<Map> getSystemFlowList() {
        return echartDao.getSystemFlowList();
    }
}

