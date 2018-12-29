package com.aaa.ssm.dao;

import com.aaa.ssm.entity.UserRegister;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:UserRegisterDao
 * discription:
 * author:fhm
 * createTime:2018-12-07 22:17
 */
public interface UserRegisterDao {
    /**
     * 根据用户名找用户
     * @param username
     * @return
     */
    @Select("select userid,uname,upwd,phone,email from userinfo where uname=#{username}")
    List<UserRegister> findUserByuserName(String username);

    /**
     * 根据手机号找用户
     * @param telephone
     * @return
     */
    @Select("select userid,uname,upwd,phone，email from userinfo where phone=#{telephone}")
    List<UserRegister> findUserByPhone(String telephone);

    /**
     * 根据邮箱找用户
     * @param email
     * @return
     */
    @Select("select userid,uname,upwd,phone,email from userinfo where email=#{email}")
    List<UserRegister> findUserByEmail(String email);

    /**
     * 注册成功添加用户
     * @param map
     * @return
     */
    @Insert("insert into userinfo(userid,uname,upwd,phone,email,stateid,amount,collectinterest,collectprincipal,replaceamount,freezamount) " +
            "values(seq_userinfo_userid.nextval,#{userName},#{password},#{phone},#{email},4,0,0,0,0,0)")
    int addUser(Map map);
}
