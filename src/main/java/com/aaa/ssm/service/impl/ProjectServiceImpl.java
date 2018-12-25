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
    public List<Map> getHouseProAll(Map map) {
        int pageNo = map.get("pageNo")==null?1:Integer.valueOf(map.get("pageNo")+"");
        int pageSize = map.get("pageSize")==null?5:Integer.valueOf(map.get("pageSize")+"");
        map.put("start",(pageNo-1)*pageSize);
        map.put("end",pageNo*pageSize+1);
        return projectDao.getHouseProAll(map);
    }
    @Override
    public List<Map> getJinduByNum(String borrowNum) {
        return projectDao.getJinduByNum(borrowNum);
    }

    @Override
    public int getPageCount(Map map) {
        return projectDao.getPageCount(map);
    }

    @Override
    public List<Map> getList(Map map) {
        return projectDao.getList(map);
    }
    @Override
    public List<Map> getBorrowList(String borrownum) {
        return projectDao.getBorrowList(borrownum);
    }

}
