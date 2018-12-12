package com.aaa.ssm.dao;

import com.aaa.ssm.entity.UserRegister;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:UserLoginDao
 * discription:
 * author:fhm
 * createTime:2018-12-08 19:46
 */
public interface UserLoginDao {

    /**
     * 根据参数（用户名、手机号或邮箱）找用户
     * @param map
     * @return
     */
    @Select("<script>select userid,uname,upwd,phone，email from userinfo where "+
            "<choose><when test=\"email!=null and email!=''\">email=#{email}</when>"+
            "<when test=\"phone!=null and phone!=''\">phone=#{phone}</when>"+
            "<when test=\"uname!=null and uname!=''\">uname=#{uname}</when>"+
            "</choose></script>")
    List<UserRegister> getUser(Map map);

}
