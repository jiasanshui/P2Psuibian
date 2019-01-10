package com.aaa.ssm.service;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:DepositsRecordService
 * discription:
 * author:yb
 * createTime:2019-01-04 17:30
 */
public interface DepositsRecordService {
    /**
     * 通过投资得到记录表
     * @param userid
     * @return
     */
    List<Map> getRecordByDeposits(Integer userid);

    /**
     * 获取分页总数量
     * @param map
     * @return
     */
    int getPageCount(Map map);

    /**
     * 投资列表分页
     * @param map
     * @return
     */
    List<Map> getTender(Map map);

    /**
     * 账户总揽3条投资记录
     * @param map
     * @return
     */
    List<Map> geThreeTender(Map map);
}
