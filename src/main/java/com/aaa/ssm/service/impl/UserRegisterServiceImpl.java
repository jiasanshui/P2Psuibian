package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.UserRegisterDao;
import com.aaa.ssm.entity.UserRegister;
import com.aaa.ssm.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:UserRegisterServiceImpl
 * discription:
 * author:fhm
 * createTime:2018-12-07 22:27
 */
@Service
public class UserRegisterServiceImpl implements UserRegisterService{
    //依赖注入dao层
    @Autowired
    private UserRegisterDao userRegisterDao;

    @Override
    public UserRegister findUserByuserName(String username) {
        List<UserRegister> userList = userRegisterDao.findUserByuserName(username);
        if(userList!=null&&userList.size()>0){
            return userList.get(0);
        }
        return null;
    }

    @Override
    public UserRegister findUserByPhone(String telephone) {
        List<UserRegister> userList = userRegisterDao.findUserByPhone(telephone);
        if(userList!=null&&userList.size()>0){
            return userList.get(0);
        }
        return null;
    }

    @Override
    public UserRegister findUserByEmail(String email) {
        List<UserRegister> userList = userRegisterDao.findUserByEmail(email);
        if(userList!=null&&userList.size()>0){
            return userList.get(0);
        }
        return null;
    }

    @Override
    public int addUser(Map map) {
        return userRegisterDao.addUser(map);
    }
}
