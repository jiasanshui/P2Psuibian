package com.aaa.ssm.service;

import java.util.List;
import java.util.Map;

public interface TenderService {
    /**
     * 根据借款人查询投标信息
     * @param map
     * @return
     */
    List<Map> getPage(Map map);


    /**
     * 带参查询投标信息
     * @param map
     * @return
     */
    List<Map> getPageByParams(Map map);


    /**
     * 添加投标信息
     * @param map
     * @return
     */
    int add(Map map);
}
