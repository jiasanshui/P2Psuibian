package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * className:AduitUserinfo
 * discription:
 * author:fhm
 * createTime:2018-12-26 23:31
 */
@Component
public interface AduitUserinfoDao {

    @Insert("insert into userinfoaduit(id,userid,operatorid,aduitresult,aduitremark,aduittime) " +
            "values(seq_userinfoaduit_id.nextval,#{USERID},#{OPERATORID},#{ADUITRESULT},#{ADUITREMARK},sysdate)")
    int add(Map map);
}
