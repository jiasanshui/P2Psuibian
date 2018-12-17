package com.aaa.ssm.dao;

import com.aaa.ssm.entity.Admin;
import com.aaa.ssm.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * className:UserDao
 * discription:
 * author:fhm
 * createTime:2018-12-16 23:10
 */
@Component
public interface UserDao {
    /**
     * 根据用户名找用户
     * @param username
     * @return
     */
    @Select("select * from admin where aname=#{username}")
    List<Admin> getUserByuserName(String username);

    /**
     * 通过id找用户
     * @return
     */
    @Select("select * from admin where id=#{userid}")
    List<Admin> getUserById(Integer userid);
}
