package com.aaa.ssm.service;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface AccountFlowService {
    /**
     * 根据userid查询用户流水标
     * @param userid
     * @return
     */
    List<Map> getPageByparams(String userid);

    /**
     * 添加用户流水
     * @param map
     * @return
     */
    int add(Map map);


    /**
     * 更改用户流水
     * @param map
     * @return
     */
    int update(Map map);

    /**
     * 查询流水类型
     * @return
     */
    List<Map> getFlowtype();

    /**
     * 获取分页总数量
     * @param map
     * @return
     */
    int getPageCount(Map map);

    /**
     * 获取资金记录流水分页
     * @param map
     * @return
     */
    List<Map> getAccountFlow(Map map);
}
