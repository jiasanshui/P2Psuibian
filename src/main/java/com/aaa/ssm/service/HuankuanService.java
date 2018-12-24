package com.aaa.ssm.service;

import java.util.List;
import java.util.Map;

/**
 * className:HuankuanService
 * discription:
 * author:jiasanshui
 * createTime:2018-12-14 11:57
 */
public interface HuankuanService {

    /**
     * 查询个人贷款
     * @param map
     * @return
     */
    List<Map> getListByUName(Map map);


    /**
     * 根据订单查询还款信息
     * @param map
     * @return
     */
    List<Map> getReturnCurrent(Map map);

    /**
     *根据不同的查询条件查询还款信息（提前还款，未还款，已还款）
     * @param map
     * @return
     */
    List<Map> getReturnInfo(Map map);

    /**
     * 重新加载当前还款期数
     * @param map
     * @return
     */
    List<Map> reGetReturnCurrent(Map map);

    /**
     * 查询未还款的信息
     * @param map
     * @return
     */
    List<Map> noReturnInfo(Map map);

    /**
     *  还款记录
     * @param map
     * @return
     */
    List<Map> haveReturnInfo(Map map);

    /**
     * 查询还款的总金额
     * @param borrownum
     * @param limits
     * @return
     */
    double getMoneyAll(String borrownum,String limits);

    /**
     * 结算时判断密码是否正确
     * @param pwd
     * @param username
     * @return
     */
    Boolean balancePwd(String pwd,String username);

    /**
     * 结算修改订单状态期数
     * @param limits
     * @return
     */
    int balanceUpdateLimit(String limits,String borrownum);

    /**
     * 查询还款信息
     * @param limits
     * @param borrownum
     * @return
     */
    String gethuankuanTime(String limits,String borrownum);
}
