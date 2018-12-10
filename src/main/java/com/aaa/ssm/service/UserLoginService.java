package com.aaa.ssm.service;

import com.aaa.ssm.entity.UserRegister;

import java.util.Map;

/**
 * className:UserLoginService
 * discription:
 * author:fhm
 * createTime:2018-12-08 20:44
 */
public interface UserLoginService {
    /**
     * 根据参数（邮箱，手机号，用户名）找用户
     * @param map
     * @return
     */
    UserRegister getUser(Map map);
}
