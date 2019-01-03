package com.aaa.ssm.service;

import java.util.Map;

/**
 * className:ChongzhiService
 * discription:
 * author:jiasanshui
 * createTime:2018-12-29 15:37
 */
public interface ChongzhiService {

    /**
     * 通过银行卡判断出银行
     * @param BCIDSix
     * @return
     */
    Map getBankByCard(String BCIDSix);
}
