package com.aaa.ssm.service;

import com.aaa.ssm.entity.UserRegister;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:UserRegisterService
 * discription:
 * author:fhm
 * createTime:2018-12-07 22:25
 */
public interface UserRegisterService {
    /**
     * 根据用户名找用户
     * @param username
     * @return
     */
    UserRegister findUserByuserName(String username);
    /**
     * 根据手机号找用户
     * @param telephone
     * @return
     */
    UserRegister findUserByPhone(String telephone);
    /**
     * 根据手机号找用户
     * @param email
     * @return
     */
    UserRegister findUserByEmail(String email);

    /**
     * 注册成功，添加用户
     * @param map
     * @return
     */
    int addUser(Map map);
}
