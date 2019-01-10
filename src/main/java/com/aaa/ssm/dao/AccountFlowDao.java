package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
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

    /**
     * 查询流水类型
     * @return
     */
    @Select("select * from flowtype")
    List<Map> getFlowtype();

    /**
     * 获取分页总数量
     * @param map
     * @return
     */
    @Select("<script>select count(*) cnt from account_flow where 1=1 and userid=#{userId} " +
            "<if test=\"flowtypeid!=null and flowtypeid!=''\">  and flowtypeid =#{flowtypeid}</if></script>")
    List<Map> getPageCount(Map map);

    /**
     * 获取资金记录流水分页
     * @param map
     * @return
     */
    @Select("<script>select * from (select rownum rn,f.amount,f.flowdate,f.changeamount,f.flowtypeid,p.type " +
            "from account_flow f left join flowtype p on p.id=f.flowtypeid where rown.,um &lt; #{end} and f.userid=#{userId} " +
            "<if test=\"flowtypeid!=null and flowtypeid!=''\">  and f.flowtypeid =#{flowtypeid}</if> ) a " +
            "where a.rn &gt; #{start}</script>")
    List<Map> getAccountFlow(Map map);
}
