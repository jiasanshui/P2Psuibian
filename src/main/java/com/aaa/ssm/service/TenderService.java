package com.aaa.ssm.service;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:TenderService
 * discription:
 * author:hulu
 * createTime:2018-12-12 15:29
 */
public interface TenderService {
    /**
     * 用户投标审核信息列表查询
     * @param map
     * @return
     */
    List<Map> getList(Map map);


    /**
     * 审核通过，更新用户信息表审核状态
     * @param userId
     * @return
     */
    int update(Integer userId);

    /**
     * 审核驳回，更新用户信息表审核状态
     * @param map
     * @return
     */
    int edit(Map map);

    /**
     * 审核驳回,向驳回表中插入数据
     * @param map
     * @return
     */
    int addBohui(Map map);
}
