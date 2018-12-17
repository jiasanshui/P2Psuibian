package com.aaa.ssm.service;

import java.util.List;
import java.util.Map;

/**
 * className:FscheckService
 * discription:
 * author:yb
 * createTime:2018-12-14 17:20
 */
public interface FscheckService {
    /**
     * 用户满标审核信息列表查询
     */
    List<Map>getList(Map map);
    /**
     * 添加满标信息
     */
    int add(Map map);
    /**
     * 审核驳回，更新用户信息表审核状态
     * @param map
     * @return
     */
    int edit(Map map);
    /**
     *审核驳回,向驳回表中插入数据
     * @param map
     * @return
     */
    int addBohui(Map map);
}
