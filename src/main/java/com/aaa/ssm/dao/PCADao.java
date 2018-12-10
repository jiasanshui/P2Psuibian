package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * className:PCADao
 * discription:省市县的接口类
 * author:jiasanshui
 * createTime:2018-12-08 15:37
 */
@Component
public interface PCADao {

    /**
     * 查询省
     */
    @Select("select * from province")
    List<Map> getProvince();

    /**
     * 根据省份id查询城市
     */
    @Select("select * from city where father = #{Pid}")
    List<Map> getCity(Integer Pid);

    /**
     * 根据城市Id查询县区
     */
    @Select("select * from area where father=#{Cid}")
    List<Map> getArea(Integer Cid);
}
