package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * className:RepayDao
 * discription:
 * author:fhm
 * createTime:2018-12-15 14:34
 */
@Component
public interface RepayDao {
    /**
     * 审核通过,向还款表中插入数据
     * @param map
     * @return
     */
    @Insert("insert into repayinfo(rid,borrownum,username,realname,benjin,lixi,timelimit,starttime,stateid,repaylimit) " +
            "values(seq_repayinfo_rid.nextval,#{BORROWNUM},#{USERNAME},#{APPLICANT},#{BORROWMONEY}," +
            "#{LIXI},#{TIMELIMIT},to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd'),2,to_date(#{REPAYLIMIT},'yyyy-mm-dd'))")
    int add(Map map);
}
