package com.aaa.ssm.service;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:RepayRecordService
 * discription:还款记录表
 * author:yb
 * createTime:2019-01-09 22:00
 */
public interface RepayRecordService {
    /**
     * 通过还款得到记录表
     * @param username
     * @return
     */
    List<Map> getRecordByRepay(String username);

    /**
     * 获取分页总数量
     * @param map
     * @return
     */
    int getPageCount(Map map);

    /**
     * 还款列表分页
     * @param map
     * @return
     */
    List<Map> getRepayPage(Map map);
}
