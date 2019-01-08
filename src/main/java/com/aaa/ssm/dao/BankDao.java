package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * className:BankDao
 * discription:
 * author:jiasanshui
 * createTime:2019-01-07 17:28
 */
@Component
public interface BankDao {

    /**
     * 判断是否绑定银行卡
     * @param userName
     * @return
     */
    @Select("select * from bankcard where uname=#{userName}")
    List<Map> getBankCardByUName(String userName);
}
