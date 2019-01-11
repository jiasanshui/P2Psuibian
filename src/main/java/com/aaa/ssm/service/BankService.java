package com.aaa.ssm.service;

import java.util.List;
import java.util.Map;

/**
 * className:BankService
 * discription:
 * author:jiasanshui
 * createTime:2019-01-07 17:28
 */
public interface BankService {

    /**
     * 判断是否绑定银行卡
     * @param userName
     * @return
     */
    List<Map> getBankCardByUName(String userName);

    /**
     * 绑定银行时获取真实姓名
     * @param map
     * @return
     */
    String getRealName(Map map);

    /**
     * 通过银行卡查询银行
     * @param sixBC
     */
    String getBankName(String sixBC);

    /**
     * 绑定银行卡
     * @param map
     * @return
     */
    int bindBankCard(Map map);

    /**
     * 解除绑定银行卡
     * @param BCID
     * @return
     */
    Boolean removeBind(String BCID);
}
