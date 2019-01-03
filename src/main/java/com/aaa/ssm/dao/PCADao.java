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
    @Select("select c.*,p.province as province from province p join city c on p.provinceid=c.father where p.province=#{pname}")
    List<Map> getCity(String pname);

    /**
     * 根据城市Id查询县区
     */
    @Select("select a.*,c.city as city from area a join city c on c.cityid=a.father where c.city=#{cname}")
    List<Map> getArea(String cname);

    /**
     * 通过pid找对应的市
     * @return
     */
    @Select("select * from city where father=#{pId}")
    List<Map> getCityByProvinceId(String pId);

    /**
     * 通过cid找对应的区
     * @param cId
     * @return
     */
    @Select("select * from area where father=#{cId}")
    List<Map>getAreaByCityId(String cId);
}
