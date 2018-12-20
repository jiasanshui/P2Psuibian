package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * className:BohuiDao
 * discription:
 * author:fhm
 * createTime:2018-12-15 16:37
 */
@Component
public interface BohuiDao {
    /**
     * 审核驳回,向借款驳回表中插入数据
     * @param map
     * @return
     */
    @Insert("insert into borrowbohui(bid,reason,opraterid) values(seq_borrowbihui_bid.nextval,#{REASON},#{OPRATERID})")
    int addBohui(Map map);
}
