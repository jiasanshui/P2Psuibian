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
    @Select("select nvl(sum(case flowtypeid when 1 then flowmoney end),0) income,\n" +
            "nvl(sum(case flowtypeid when 2 then flowmoney end),0) outlay from systemflow\n" +
            "where flowtime<to_date('2018/4/1','yyyy-mm-dd') and flowtime>to_date('2018/1/1','yyyy-mm-dd') union all\n" +
            "\n" +
            "select nvl(sum(case flowtypeid when 1 then flowmoney end),0) income,\n" +
            "nvl(sum(case flowtypeid when 2 then flowmoney end),0) outlay from systemflow\n" +
            "where flowtime<to_date('2018/7/1','yyyy-mm-dd') and flowtime>to_date('2018/4/1','yyyy-mm-dd') union all\n" +
            "\n" +
            "select nvl(sum(case flowtypeid when 1 then flowmoney end),0) income,\n" +
            "nvl(sum(case flowtypeid when 2 then flowmoney end),0) outlay from systemflow\n" +
            "where flowtime<to_date('2018/11/1','yyyy-mm-dd') and flowtime>to_date('2018/7/1','yyyy-mm-dd') union all\n" +
            "\n" +
            "select nvl(sum(case flowtypeid when 1 then flowmoney end),0) income,\n" +
            "nvl(sum(case flowtypeid when 2 then flowmoney end),0) outlay from systemflow\n" +
            "where flowtime<to_date('2018/1/1','yyyy-mm-dd') and flowtime>to_date('2018/11/1','yyyy-mm-dd')")
    List<Map> getSystemFlowList();
}
