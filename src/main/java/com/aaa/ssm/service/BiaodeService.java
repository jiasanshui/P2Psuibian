package com.aaa.ssm.service;

import java.util.List;
import java.util.Map;

/**
 * className:BiaodeService
 * discription:
 * author:fhm
 * createTime:2018-12-15 10:01
 */
public interface BiaodeService {
    /**
     * 用户借款标的列表查询
     * @return
     */
    List<Map> getList(Map map);

    /**
     * 审核通过，更新用户信息表审核状态,招标开始时间
     * @param map
     * @return
     */
    int update(Map map);

    /**
     * 审核驳回，更新用户借款表审核状态
     * @param map
     * @return
     */
    int edit(Map map);

}
