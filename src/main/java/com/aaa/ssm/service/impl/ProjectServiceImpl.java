package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.ProjectDao;
import com.aaa.ssm.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:ProjectServiceImpl
 * discription:
 * author:jiasanshui
 * createTime:2018-12-12 16:39
 */
@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectDao projectDao;

    @Override
    public List<Map> getHousePro() {
        return projectDao.getHousePro();
    }

    @Override
    public List<Map> getHouseProAll() {
        return projectDao.getHouseProAll();
    }
    @Override
    public List<Map> getJinduByNum(String borrowNum) {
        return projectDao.getJinduByNum(borrowNum);
    }
}
