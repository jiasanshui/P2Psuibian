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

   /* *//**
     * 判断 starttime,timelimit 是否为空
     * @param userId
     * @return
     *//*
    List<Map> panDuanStarttime(Map map);*/
}
