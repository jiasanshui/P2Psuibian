package com.aaa.ssm.service;

import com.aaa.ssm.entity.Admin;
import com.aaa.ssm.entity.User;
import org.springframework.stereotype.Component;

/**
 * className:UserService
 * discription:
 * author:jiasanshui
 * createTime:2018-12-06 20:39
 */
@Component
public interface UserService {
    /**
     * 根据用户名找用户
     * @param username
     * @return
     */
    Admin getUserByuserName(String username);

    /**
     * 通过id找用户
     * @return
     */
    Admin getUserById(Integer userid);
}
