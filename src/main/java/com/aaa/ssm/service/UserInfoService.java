package com.aaa.ssm.service;


import java.util.List;
import java.util.Map;

/**
 * className:UserInfoService
 * discription:
 * author:fhm
 * createTime:2018-12-10 16:50
 */
public interface UserInfoService {

    /**
     * 用户列表查询
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
     *审核驳回,向驳回表中插入数据
     * @param map
     * @return
     */
    int addBohui(Map map);

    /**
     * 查询当前ID所有信息
     * @param userId
     * @return
     */
    List<Map> getAllList(Integer userId);
     /** 根据用户名获取用户信息
     * @param username
     * @return
     */
    List<Map> getUserList(String username);
}
