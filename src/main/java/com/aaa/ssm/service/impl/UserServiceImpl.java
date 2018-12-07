package com.aaa.ssm.service.impl;/**
 * className:UserServiceImpl
 * discription:
 * author:jiasanshui
 * createTime:2018-12-06 20:40
 */

import com.aaa.ssm.entity.User;
import com.aaa.ssm.mapper.UserMapper;
import com.aaa.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *className:UserServiceImpl
 *discription:
 *author:jiasanshui
 *createTime:2018-12-06 20:40
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public User findById(Integer userid) {
        return userMapper.findById(userid);
    }
}
