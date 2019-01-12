package com.aaa.ssm.dao;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * className:PageDao
 * discription:
 * author:hulu
 * createTime:2018-12-21 14:28
 */
@Component
public interface PageDao {
    /**
     * 获取分页数据
     * @param start		开始值
     * @param rows		显示每页数据
     * @param map	其他参数
     * @return
     */
    List<Map<String,Object>> getPage(int start,int rows,Map map);
    /**
     * 获取分页数据的总数量
     * @param map
     * @return
     */
    List<Map<String,Object>> getPageCount(Map map);
}
