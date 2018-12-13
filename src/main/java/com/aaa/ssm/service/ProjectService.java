package com.aaa.ssm.service;

import java.util.List;
import java.util.Map;

/**
 * className:ProjectService
 * discription:
 * author:jiasanshui
 * createTime:2018-12-12 16:38
 */
public interface ProjectService {

    /**
     * 查询房屋抵押项目
     * @return
     */
    List<Map> getHousePro();

    /**
     * 查询全部房屋抵押项目
     * @return
     */
    List<Map> getHouseProAll();
}
