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
     * 审核通过,向还款表中插入数据（等额本息 前n期）
     * {PAYMENT=按月付息，到期还本, DAYS=4, APR=0.05, BORROWMONEY=99995,
     * APPLICANT=贾源浩, TIMELIMIT=3, USERID=3, DANBAOSTYLE=车辆之押贷款,
     * WINBIDMONEY=100000, BORROWNUM=BD0000004, BORROWID=26, ADUITRESULT=1,
     * OPERATORID=44, ADUITREMARK=rtyyh, submitArr=[4, 4, 4, 4, 4]}
     * rid,borrownum,username,realname,benjin,lixi,timelimit,
     starttime,hunkuantime,stateid,currentmoney,
     nextmoney,repaylimit（还款期限）
     overtimelixi
     * @param map
     * @return
     */
    @Insert("insert into repayinfo(rid,borrownum,username,realname,benjin,lixi,timelimit,starttime,stateid,repaylimit,currentmoney,nextmoney) " +
            "values(seq_repayinfo_rid.nextval,#{BORROWNUM},#{USERNAME},#{APPLICANT},#{BORROWMONEY}," +
            "#{LIXI},#{TIMELIMIT},to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd'),8,to_date(#{REPAYLIMIT},'yyyy-mm-dd')," +
            "#{moneyPer},#{moneyPer})")
    int add(Map map);

    /**
     * 审核通过,向还款表中插入数据，等额本息 （最后一期）
     * @param map
     * @return
     */
    @Insert("insert into repayinfo(rid,borrownum,username,realname,benjin,lixi,timelimit,starttime,stateid,repaylimit,currentmoney,nextmoney) " +
            "values(seq_repayinfo_rid.nextval,#{BORROWNUM},#{USERNAME},#{APPLICANT},#{BORROWMONEY}," +
            "#{LIXI},#{TIMELIMIT},to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd'),8,to_date(#{REPAYLIMIT},'yyyy-mm-dd')," +
            "#{moneyPer},0)")
    int adds(Map map);

    /**
     * 审核通过,向还款表中插入数据（按月付息，到期还本 前n期）
     * @param map
     * @return
     */
    @Insert("insert into repayinfo(rid,borrownum,username,realname,benjin,lixi,timelimit,starttime,stateid,repaylimit,currentmoney,nextmoney) " +
            "values(seq_repayinfo_rid.nextval,#{BORROWNUM},#{USERNAME},#{APPLICANT},#{BORROWMONEY}," +
            "#{LIXI},#{TIMELIMIT},to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd'),8,to_date(#{REPAYLIMIT},'yyyy-mm-dd')," +
            "#{interstPer},#{interstPer})")
    int addOne(Map map);

    /**
     * 审核通过,向还款表中插入数据（按月付息，到期还本 倒数第二期）
     * @param map
     * @return
     */
    @Insert("insert into repayinfo(rid,borrownum,username,realname,benjin,lixi,timelimit,starttime,stateid,repaylimit,currentmoney,nextmoney) " +
            "values(seq_repayinfo_rid.nextval,#{BORROWNUM},#{USERNAME},#{APPLICANT},#{BORROWMONEY}," +
            "#{LIXI},#{TIMELIMIT},to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd'),8,to_date(#{REPAYLIMIT},'yyyy-mm-dd')," +
            "#{interstPer},#{BORROWNUM}+#{interstPer})")
    int addTwo(Map map);

    /**
     * 审核通过,向还款表中插入数据（按月付息，到期还本 最后一期）
     * @param map
     * @return
     */
    @Insert("insert into repayinfo(rid,borrownum,username,realname,benjin,lixi,timelimit,starttime,stateid,repaylimit,currentmoney,nextmoney) " +
            "values(seq_repayinfo_rid.nextval,#{BORROWNUM},#{USERNAME},#{APPLICANT},#{BORROWMONEY}," +
            "#{LIXI},#{TIMELIMIT},to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd'),8,to_date(#{REPAYLIMIT},'yyyy-mm-dd')," +
            "#{BORROWNUM}+{interstPer},0)")
    int addTwos(Map map);

    /**
     * 审核通过,向还款表中插入数据（到期付本付息）
     * @param map
     * @return
     */
    @Insert("insert into repayinfo(rid,borrownum,username,realname,benjin,lixi,timelimit,starttime,stateid,repaylimit,currentmoney,nextmoney) " +
            "values(seq_repayinfo_rid.nextval,#{BORROWNUM},#{USERNAME},#{APPLICANT},#{BORROWMONEY}," +
            "#{LIXI},#{TIMELIMIT},to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd'),8,to_date(#{REPAYLIMIT},'yyyy-mm-dd')," +
            "#{BORROWNUM}+{LIXI},0)")
    int addLast(Map map);

}
