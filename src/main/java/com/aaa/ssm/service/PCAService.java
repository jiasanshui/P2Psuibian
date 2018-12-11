package com.aaa.ssm.service;

import java.util.List;
import java.util.Map;

/**
 * className:PCAServiceImpl
 * discription:
 * author:jiasanshui
 * createTime:2018-12-08 15:39
 */
public interface PCAService {
    /**
     * 查询省
     */
    List<Map> getProvince();

    /**
     * 根据省份查询市
     * @param pname
     * @return
     */
    List<Map> getCity(String pname);

    /**
     * 根据城市Id查询县区
     * @param cname
     * @return
     */
    List<Map> getArea(String cname);
}
