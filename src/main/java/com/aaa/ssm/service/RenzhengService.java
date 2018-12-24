package com.aaa.ssm.service;

import java.util.Map;

/**
 * className:RenzhengService
 * discription:
 * author:jiasanshui
 * createTime:2018-12-10 21:42
 */
public interface RenzhengService {

    /**
     * 个人信息认证
     * @param map
     * @return
     */
    int update(Map map);

    /**
     * 判断是否已经实名认证
     * @param userName
     * @return
     */
    Map isRenZheng(String userName);
}
