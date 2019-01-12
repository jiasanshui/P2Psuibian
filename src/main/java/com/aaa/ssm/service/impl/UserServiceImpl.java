package com.aaa.ssm.service.impl;/**
 * className:UserServiceImpl
 * discription:
 * author:jiasanshui
 * createTime:2018-12-06 20:40
 */

import com.aaa.ssm.dao.UserDao;
import com.aaa.ssm.entity.Admin;
import com.aaa.ssm.entity.User;
import com.aaa.ssm.mapper.UserMapper;
import com.aaa.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *className:UserServiceImpl
 *discription:
 *author:jiasanshui
 *createTime:2018-12-06 20:40
 */
@Service
public class UserServiceImpl implements UserService {

    //依赖注入
    @Autowired
    private UserDao userDao;

    @Override
    public Admin getUserByuserName(String username) {
        List<Admin> userList = userDao.getUserByuserName(username);
        if (userList!=null&&userList.size()>0){
            return userList.get(0);
        }
        return null;
    }

    @Override
    public Admin getUserById(Integer userid) {
        List<Admin> userList = userDao.getUserById(userid);
        if (userList!=null&&userList.size()>0){
            return userList.get(0);
        }
        return null;
    }
}
