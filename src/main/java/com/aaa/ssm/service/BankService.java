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
}
