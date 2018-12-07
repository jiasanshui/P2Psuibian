package com.aaa.ssm.service;

import com.aaa.ssm.entity.User;

/**
 * className:UserService
 * discription:
 * author:jiasanshui
 * createTime:2018-12-06 20:39
 */
public interface UserService {

    User findByUserName(String username);

    User findById(Integer userid);
}
