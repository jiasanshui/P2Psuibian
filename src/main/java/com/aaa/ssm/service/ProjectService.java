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
     * 查询全部房屋抵押项目分页数据
     * @return
     */
    List<Map> getHouseProAll(Map map);

    /**
     * 根据编号查询招标进度
     * @param borrowNum
     * @return
     */
    List<Map> getJinduByNum(String borrowNum);

    /**
     * 获取分页总数量
     * @param map
     * @return
     */
    int getPageCount(Map map);
    /**
     * 查询车辆抵押项目
     * @return
     */
    List<Map> getList(Map map);
    /**
     * 根据借款编号查询信息（进infor页面）
     * @param borrownum
     * @return
     */
    List<Map> getBorrowList(String borrownum);
}
