package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public interface HuiKuaiDao {

    /**
     * 生成回款计划表  id,tUserid,tamount,backtime,backmoney,borrownum
     * @param map
     * @return
     */
    @Insert("insert into backmoney(id,userid,tamount,backtime,backmoney,borrownum,status) " +
            "values(seq_backmoney_id.nextval,#{tUserid},#{tamount},add_months(sysdate,#{TIMELIMIT}),#{backmoney},#{BORROWNUM},'未回款')")
    int addBackMoneyPlan(Map map);


    /**
     * 账户总揽中回款计划(按月付息，到期还本、等额本息)
     * @param map
     * @return
     */
    @Select("select b.des,bm.tamount,bm.backtime,bm.backmoney from backmoney bm " +
            "left join borrow b on bm.borrownum=b.borrownum " +
            "where bm.userid = #{userId}")
    List<Map> gethuaikuanList(Map map);


}
