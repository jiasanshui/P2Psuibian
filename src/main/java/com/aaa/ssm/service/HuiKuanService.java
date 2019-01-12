package com.aaa.ssm.service;

import java.util.List;
import java.util.Map;

public interface HuiKuanService {
    /**
     * 账户总揽中回款计划(按月付息，到期还本、等额本息)
     * @param map
     * @return
     */
    List<Map> gethuaikuanList(Map map);
}
