package com.aaa.ssm.service;

import java.util.List;
import java.util.Map;

/**
 * className:HuankuanService
 * discription:
 * author:jiasanshui
 * createTime:2018-12-14 11:57
 */
public interface HuankuanService {

    /**
     * 查询个人贷款
     * @param map
     * @return
     */
    List<Map> getListByUName(Map map);
}
