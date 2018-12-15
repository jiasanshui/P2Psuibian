package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Insert;

import java.util.Map;

/**
 * className:RepayDao
 * discription:
 * author:fhm
 * createTime:2018-12-15 14:34
 */
public interface RepayDao {
    /**
     * 审核通过,向还款表中插入数据
     * @param map
     * @return
     */
    @Insert("insert into repayinfo(rid,borrownum,username,realname,benjin,lixi,timelimit) " +
            "values(seq_repayinfo_rid.nextval,#{BORROWNUM},#{USERNAME},#{APPLICANT},#{BORROWMONEY},6700,#{TIMELIMIT})")
    int add(Map map);
}
