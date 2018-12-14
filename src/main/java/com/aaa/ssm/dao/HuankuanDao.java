package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * className:HuankuanDao
 * discription:
 * author:jiasanshui
 * createTime:2018-12-14 11:58
 */
@Component
public interface HuankuanDao {

    @Select("<script>select borrownum,purpose,applicant,borrowmoney,danbaostyle,payment,timelimit,yettime from borrow where username=#{userName} \n "+
            "<if test=\"borrownum!=null and borrownum!=''\">  and borrownum=#{borrowNum}</if>" +
            "<if test=\"danbaostyle!=null and danbaostyle!=''\">  and danbaostyle =#{danbaoStyle}</if>" +
            "</script>")
    List<Map> getListByUName(Map map);
}
