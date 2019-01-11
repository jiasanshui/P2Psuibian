package com.aaa.ssm.service;

import java.util.List;
import java.util.Map;

public interface HuiKuanService {
    /**
     * 查询每个用户每笔回款信息
     * @param map
     * @return
     */
    List<Map> getHuiKuaiList(Map map);

    /**
     * 获取分页总数量
     * @param map
     * @return
     */
    int getPageCount(Map map);
    /**
     * 到期付本付息
     * @param map
     * @return
     */
    List<Map> getHuiKuanList(Map map);

    /**
     * 查询还款方式
     * @param map
     * @return
     */
    List<Map> getpaymentList(Map map);
}
