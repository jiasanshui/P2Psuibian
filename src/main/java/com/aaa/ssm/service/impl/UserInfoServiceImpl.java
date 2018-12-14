package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.UserInfoDao;
import com.aaa.ssm.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:UserInfoServiceImpl
 * discription:
 * author:fhm
 * createTime:2018-12-10 17:12
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    //依赖注入dao层
    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public List<Map> getList(Map map) {

        return userInfoDao.getList(map);
    }

    @Override
    public int update(Integer userId) {
        return userInfoDao.update(userId);
    }

    @Override
    public int edit(Map map) {
        return userInfoDao.edit(map);
    }

    @Override
    public int addBohui(Map map) {
        return userInfoDao.addBohui(map);
    }

    @Override
    public List<Map> getUserList(String username) {
        return userInfoDao.getUserList(username);
    }

    @Override
    public List<Map> getUserListById(Integer userid) {
        return userInfoDao.getUserListById(userid);
    }
}
