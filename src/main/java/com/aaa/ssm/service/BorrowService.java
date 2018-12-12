package com.aaa.ssm.service;

import java.util.List;
import java.util.Map;

/**
 * className:BorrowService
 * discription:
 * author:hulu
 * createTime:2018-12-11 15:15
 */
public interface BorrowService {
    /**
     * 查询还款方式列表
     * @return
     */
    List<Map> getPayList();

    /**
     *查询借款方式列表
     * @return
     */
    List<Map> getConditionList();
}
