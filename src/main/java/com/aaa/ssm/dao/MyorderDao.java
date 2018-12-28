package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * className:MyorderDao
 * discription:
 * author:yb
 * createTime:2018-12-26 15:40
 */
@Component
public interface MyorderDao {
    /**
     *根据用户信息得到订单
     * @param username
     * @return
     */
    @Select("select borrownum,applicant,borrowmoney,timelimit,apr,danbaostyle,payment,stateid,winbidmoney,id,name from borrow b join bidstate s on b.stateid=s.id where username=#{username}" )
    List<Map>getOrderByInfo(String username);
}
