package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public interface HuiKuaiDao {
    /**
     * 查询每个用户每笔回款信息
     * @param map
     * @return
     */
    @Select("<script> select * from (select rownum rn,t.tamount,t.ttime,b.tapr,to_char(add_months(r.starttime,b.timelimit),'yyyy-mm-dd') " +
            "as endtime, " +
            "b.des,b.timelimit,b.danbaostyle from tender t " +
            "left join borrow b on t.borrownum=b.borrownum left join " +
            "repayinfo r on b.borrownum=r.borrownum where t.userid=#{userId} and r.stateid!=1 and b.timelimit = r.timelimit and rownum &lt; #{end} " +
            " <if test=\"selecttoday!=null and selecttoday!=''\">  and trunc(t.ttime)=trunc(sysdate) </if> " +
            " <if test=\"selectmonth!=null and selectmonth!=''\">  and t.ttime > sysdate - interval '1' month </if> "  +
            " <if test=\"selectsix!=null and selectsix!=''\">  and t.ttime > sysdate - interval '6' month </if>) " +
            "a where a.rn &gt; #{start}</script>")
    List<Map> getHuiKuaiList(Map map);


    /**
     * 查询还款方式
     * @param map
     * @return
     */
    @Select("select b.payment from tender t left join borrow b on t.borrownum=b.borrownum left join repayinfo r on b.borrownum=r.borrownum where t.userid=#{userId} and r.stateid=2 ")
    List<Map> getpaymentList(Map map);

    /**
     * 到期付本付息
     * @param map
     * @return
     */
    @Select("select * from (select rownum rn,t.tamount,t.ttime,b.tapr,to_char(add_months(r.starttime,b.timelimit),'yyyy-mm-dd') " +
            " as endtime,b.des,b.timelimit,b.danbaostyle from tender t left join borrow b on t.borrownum=b.borrownum left join " +
            "repayinfo r on b.borrownum=r.borrownum where t.userid=#{userId} and r.stateid=2 and b.payment='到期付本付息' and rownum &lt; #{end}" +
            "<if test=\"selecttoday!=null and selecttoday!=''\">  and trunc(t.ttime)=trunc(sysdate) </if> " +
            "<if test=\"selectmonth!=null and selectmonth!=''\">  and t.ttime > sysdate - interval '1' month </if> " +
            "<if test=\"selectsix!=null and selectsix!=''\">  and t.ttime > sysdate - interval '6' month </if>)  " +
            "a where a.rn &gt; #{start}</script>")
    List<Map> getHuiKuanList(Map map);
    /**
            * 获取分页总数量
     * @param map
     * @return
             */
    @Select("<script> select count(*) cnt from (select rownum,t.ttime from tender t left join borrow b on b.borrownum = t.borrownum left join " +
            "repayinfo r on b.borrownum=r.borrownum where t.userid=#{userId}  and r.stateid=2 and b.timelimit = r.timelimit " +
            " <if test=\"selecttoday!=null and selecttoday!=''\">  and trunc(t.ttime)=trunc(sysdate) </if> " +
            " <if test=\"selectmonth!=null and selectmonth!=''\">  and t.ttime > sysdate - interval '1' month </if> " +
            " <if test=\"selectsix!=null and selectsix!=''\">  and t.ttime > sysdate - interval '6' month </if>)</script>")
    List<Map> getPageCount(Map map);

    /**
     * 生成回款计划表  id,tUserid,tamount,backtime,backmoney,borrownum
     * @param map
     * @return
     */
    @Insert("insert into backmoney(id,userid,tamount,backtime,backmoney,borrownum,status) " +
            "values(seq_backmoney_id.nextval,#{tUserid},#{tamount},add_months(sysdate,#{TIMELIMIT}),#{backmoney},#{BORROWNUM},'未回款')")
    int addBackMoneyPlan(Map map);


}
