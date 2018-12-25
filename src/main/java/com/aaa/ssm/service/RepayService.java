package com.aaa.ssm.service;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * className:RepayService
 * discription:
 * author:fhm
 * createTime:2018-12-22 09:58
 */
@Component
public interface RepayService {
    /**
     * 还款计划表
     * @param map
     * @return
     */
    int repayPlan(Map map);
}
