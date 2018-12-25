package com.aaa.ssm.service;

import java.util.List;
import java.util.Map;

/**
 * className:PageService
 * discription:
 * author:hulu
 * createTime:2018-12-21 14:30
 */
public interface PageService {
    /**
     * 获取分页数据
     * @param map		其他参数
     * @return
     */
    List<Map<String,Object>> getPage(Map map);
    /**
     * 获取分页数据的总数量
     * @param map
     * @return
     */
    Integer getPageCount(Map map);
}
