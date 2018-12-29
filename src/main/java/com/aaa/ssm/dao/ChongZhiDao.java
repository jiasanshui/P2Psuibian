package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * className:ChongZhiDao
 * discription:
 * author:jiasanshui
 * createTime:2018-12-29 15:37
 */
@Component
public interface ChongZhiDao {

    /**
     * 通过银行卡判断出银行
     * @param BCIDSix
     * @return
     */
    @Select("select cashname,url from cashcard where code=#{BCIDSix}")
    Map getBankByCard(String BCIDSix);
}
