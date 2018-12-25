package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * className:AduitBidDao
 * discription:
 * author:fhm
 * createTime:2018-12-21 10:12
 */
@Component
public interface AduitBidDao {

    /**
     * 往标的审核表中插入数据
     * @param map
     * @return
     */
    @Insert("insert into aduitbid(id,borrownum,operatorid,aduittime,aduitresult,aduitremark) " +
            "values(seq_aduitbid_id.nextval,#{BORROWNUM},#{OPERATORID},sysdate,#{ADUITRESULT},#{ADUITREMARK})")
    int add(Map map);
}
