package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
    @Select("select * from bankcard where uname=#{userName} and stateid=1")
    List<Map> getBankCardByUName(String userName);

    /**
     * 绑定银行时获取真实姓名
     * @param map
     * @return
     */
    @Select("select realname from userinfo where uname=#{userName}")
    String getRealName(Map map);

    /**
     * 通过银行卡查询银行
     * @param sixBC
     * @return
     */
    @Select("select cashname from cashcard where code=#{sixBC}")
    String getBankName(@Param("sixBC") String sixBC);

    /**
     * 绑定银行卡
     * @param map
     * @return
     */
    @Insert("insert into bankcard values(seq_bankcard_id.nextval,#{userName},#{bankcard},#{bankName},1)")
    int bindBankCard(Map map);

    /**
     * 接触绑定银行卡
     * @param BCID
     * @return
     */
    @Update("update bankcard set stateid=2 where BCID=#{bcid}")
    int removeBind(@Param("bcid") String BCID);

    @Select("select * from bankcard where uname=#{userName} and stateid=1")
    List<Map> getBankCards(Map map);
}
