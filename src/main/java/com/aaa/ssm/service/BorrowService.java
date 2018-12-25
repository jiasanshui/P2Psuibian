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
     * 借款人提交数据到后台(信用贷款)
     * @param map
     * @return
     */
    int add(Map map);

    /**
     * 借款人提交数据到后台（房屋抵押贷款）
     * @param map
     * @return
     */
    int addOne(Map map);


    /**
     * 根据用户名查询投标中的标的
     * @param BORROWNUM
     * @return
     */
    List<Map> getListByUsername(String BORROWNUM);


    /**
     * 投标成功后修改借款表里已借金额
     * @param map
     * @return
     */
    int update(Map map);
}
