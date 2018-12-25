package com.aaa.ssm.service;

import java.util.List;
import java.util.Map;

public interface ZlRenzhengService {
    /**
     * 根据用户id查询用户资料
     * @param userid
     * @return
     */
    List<Map> getZlList(Integer userid);

    /**
     * 添加用户资料认证
     * @param map
     * @return
     */
    int add(Map map);
}
