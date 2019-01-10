package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:EchartDao
 * discription:
 * author:hulu
 * createTime:2018-12-28 13:13
 */
public interface EchartDao {
    /**
     * 查询systemflow表资金流水
     * @return
     */
    @Select("select a.* from (\n" +
            "select nvl(sum(case flowtypeid when 1 then flowmoney end),0) income,\n" +
            "           nvl(sum(case flowtypeid when 2 then flowmoney end),0) outlay from systemflow\n" +
            "           where flowtime<to_date( #{year}||'/4/1','yyyy-mm-dd') and flowtime>to_date(#{year}||'/1/1','yyyy-mm-dd') union all\n" +
            "           \n" +
            "    select nvl(sum(case flowtypeid when 1 then flowmoney end),0) income,\n" +
            "           nvl(sum(case flowtypeid when 2 then flowmoney end),0) outlay from systemflow\n" +
            "           where flowtime<to_date( #{year}||'/7/1','yyyy-mm-dd') and flowtime>to_date( #{year}||'/4/1','yyyy-mm-dd') union all\n" +
            "         \n" +
            "select nvl(sum(case flowtypeid when 1 then flowmoney end),0) income,\n" +
            "           nvl(sum(case flowtypeid when 2 then flowmoney end),0) outlay from systemflow\n" +
            "           where flowtime<to_date( #{year}||'/10/1','yyyy-mm-dd') and flowtime>to_date( #{year}||'/7/1','yyyy-mm-dd') union all\n" +
            "   \n" +
            "    select nvl(sum(case flowtypeid when 1 then flowmoney end),0) income,\n" +
            "           nvl(sum(case flowtypeid when 2 then flowmoney end),0) outlay from systemflow\n" +
            "           where flowtime<to_date( #{year}+1||'/1/1','yyyy-mm-dd') and flowtime>to_date( #{year}||'/10/1','yyyy-mm-dd')) a")
    List<Map> getSystemFlowList(Integer year);
}
