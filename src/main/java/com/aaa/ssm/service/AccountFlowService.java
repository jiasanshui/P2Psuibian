package com.aaa.ssm.service;

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
}
