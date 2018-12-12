package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:BorrowDao
 * discription:
 * author:hulu
 * createTime:2018-12-11 15:05
 */
public interface BorrowDao {
    /**
     * 查询还款方式列表
     * @param
     * @return
     */
    @Select("select id,des from payment")
    List<Map> getPayList();
    /**
     * 查询借款方式列表
     * @param
     * @return
     */
    @Select("select id,des from condition")
    List<Map> getConditionList();

}
