package com.aaa.ssm.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:EchartService
 * discription:
 * author:hulu
 * createTime:2018-12-28 13:14
 */
public interface EchartService {
    /**
     * 查询systemflow表资金流水
     * @return
     */
    List<Map> getSystemFlowList(Integer year);
}
