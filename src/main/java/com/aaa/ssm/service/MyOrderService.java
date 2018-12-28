package com.aaa.ssm.service;

import java.util.List;
import java.util.Map;

/**
 * className:MyOrderService
 * discription:
 * author:yb
 * createTime:2018-12-26 16:04
 */
public interface MyOrderService {
    /**
     * 根据用户信息得到订单
     * @param username
     * @return
     */
    List<Map>getOrderByInfo(String username);
}
