package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.TenderDao;
import com.aaa.ssm.service.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:TenderServiceImpl
 * discription:
 * author:hulu
 * createTime:2018-12-12 15:30
 */
@Service
public class TenderServiceImpl implements TenderService {
    @Autowired
    private TenderDao tenderDao;

    @Override
    public List<Map> getList(Map map) {
        return null;
    }

    @Override
    public int update(Integer userId) {
        return tenderDao.update(userId);
    }

    @Override
    public int edit(Map map) {
        return tenderDao.edit(map);
    }

    @Override
    public int addBohui(Map map) {
        return tenderDao.addBohui(map);}

    @Override
    public List<Map> getTenderPage(Map map) {
        return tenderDao.getTenderPage(map);
    }

    @Override
    public List<Map> getPage(String BORROWNUM) {
        return tenderDao.getPage(BORROWNUM);
    }

    @Override
    public List<Map> getPageByParams(Map map) {
        return tenderDao.getPageByParams(map);
    }

    @Override
    public int add(Map map) {
        return tenderDao.add(map);
    }

    @Override
    public int updateState(String BORROWNUM) {
        return tenderDao.updateState(BORROWNUM);
    }
}