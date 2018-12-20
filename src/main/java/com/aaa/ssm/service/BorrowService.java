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
     * 借款人提交数据到后台
     * @param map
     * @return
     */
    int add(Map map);

    /**
     * 根据用户名查询投标中的标的
     * @param userName
     * @return
     */
    List<Map> getListByUsername(String userName);
}
