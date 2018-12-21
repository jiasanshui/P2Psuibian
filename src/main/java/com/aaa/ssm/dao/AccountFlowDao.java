package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface AccountFlowDao {


    /**
     * 根据userid查询用户流水标
     * @param userid
     * @return
     */
    @Select("<script>select a.id,a.userid,a.amount,a.flowdate,f.type,a.banknum from account_flow a left join flowtype f on a.flowtypeid=f.id where userid=#{userid}</script>")
    List<Map> getPageByparams(String userid);

    /**
     * 投标添加用户流水
     * @param map
     * @return
     */
    @Insert("insert into account_flow(id,userid,amount,flowdate,flowtypeid,banknum,changeamount) " +
            "values(seq_account_flow_id.nextval,#{userid},#{amount},sysdate,2,#{bankNum},#{tamount})")
    int add(Map map);


    /**
     * 更改用户流水
     * @param map
     * @return
     */
    @Update("update account_flow set amount=#{amount},flowdate#{flowdate},flowtypeid=#{flowtypeid},banknum=#{banknum} where userid = #{userid}")
    int update(Map map);
}
