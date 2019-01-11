package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.WebDao;
import com.aaa.ssm.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:WebServiceImpl
 * discription:
 * author:hulu
 * createTime:2018-12-18 11:18
 */
@Service
public class WebServiceImpl implements WebService {

    @Autowired
    private WebDao webDao;
    @Override
        public List<Map> getWebList( ) {
            return webDao.getWebList();
    }

    @Override
    public List<Map> getMediaList() {
        return webDao.getMediaList();
    }

    @Override
    public List<Map> getCompanyList() {
        return webDao.getCompanyList();
    }

    @Override
    public List<Map> getTeamList() {
        return webDao.getTeamList();
    }

    @Override
    public List<Map> getPartnerList() {
        return webDao.getPartnerList();
    }
    @Override
    public List<Map> getTeamSList() {
        return webDao.getTeamSList();
    }
    @Override
    public List<Map> getList(Integer noticeid) {
        return webDao.getList(noticeid);
    }
    @Override
    public int getPageCount(Map map) {
        return webDao.getPageCount(map);
    }
    @Override
    public int getPageCountM(Map map) {
        return webDao.getPageCountM(map);
    }
}
