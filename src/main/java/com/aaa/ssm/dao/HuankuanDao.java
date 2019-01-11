package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * className:HuankuanDao
 * discription:
 * author:jiasanshui
 * createTime:2018-12-14 11:58
 */
@Component
public interface HuankuanDao {

    /**
     * 根据个人信息查询借款情况
     * @param map
     * @return
     */
    @Select("<script>select borrownum,purpose,applicant,borrowmoney,danbaostyle,payment,timelimit,yettime from borrow where username=#{userName} and stateid=8 \n "+
            "<if test=\"borrowNum!=null and borrowNum!=''\">  and borrownum=#{borrowNum}</if>" +
            "<if test=\"danbaoStyle!=null and danbaoStyle!=''\">  and danbaostyle =#{danbaoStyle}</if>" +
            "</script>")
    List<Map> getListByUName(Map map);

    /**
     * 根据订单编号查询还款情况
     * @param map
     * @return
     */
    @Select("select * from (select r.overtimelixi,r.borrownum,r.timelimit,r.currentmoney currentmoney," +
            "to_char(r.repaylimit,'yyyy-mm-dd') repaylimit,s.statename state from repayinfo r join returnstate s on " +
            "r.stateid=s.sid where borrownum=#{borrownum} and (r.stateid=1 or  r.stateid=3) order by r.timelimit asc)" +
            " where rownum=1")
    List<Map> getReturnCurrent(Map map);


    /**
     *根据不同的查询条件查询还款信息（提前还款，未还款，已还款）
     * @param map
     * @return
     */
    @Select("select r.overtimelixi,r.borrownum,r.timelimit,r.currentmoney currentmoney,to_char(r.repaylimit,'yyyy-mm-dd') repaylimit,s.statename state " +
            "from repayinfo r join returnstate s on r.stateid=s.sid where borrownum=#{borrownum} and (r.stateid=1 or  r.stateid=3)" +
            "order by timelimit asc")
    List<Map> getReturnInfo(Map map);

    /**
     * 重新加载当前还款期数
     * @return
     */
    @Select("select * from (select r.overtimelixi,r.borrownum,r.timelimit,r.currentmoney currentmoney," +
            "to_char(r.repaylimit,'yyyy-mm-dd') repaylimit,s.statename state from repayinfo r join returnstate s on " +
            "r.stateid=s.sid where borrownum=#{borrownum} and (r.stateid=1 or  r.stateid=3) order by r.timelimit asc)" +
            " where rownum=1")
    List<Map> reGetReturnCurrent(Map map);

    /**
     * 查询未还款的信息
     * @param map
     * @return
     */
    @Select("select r.timelimit,r.currentmoney currentmoney,to_char(r.repaylimit,'yyyy-mm-dd') repaylimit,s.statename state " +
            "from repayinfo r join returnstate s on r.stateid=s.sid where borrownum=#{borrownum} and r.stateid=1")
    List<Map> noReturnInfo(Map map);

    /**
     * 查询已还款的信息
     * @param map
     * @return
     */
    @Select("select r.timelimit,r.currentmoney currentmoney,to_char(r.repaylimit,'yyyy-mm-dd') repaylimit,s.statename state " +
            "from repayinfo r join returnstate s on r.stateid=s.sid where borrownum=#{borrownum} and r.stateid=2")
    List<Map> haveReturnInfo(Map map);

    /**
     * 查询还款总额
     * @param borrownum
     * @param limit
     * @return
     */
   @Select("select (currentmoney+overtimelixi) currentmoney from repayinfo where borrownum=#{borrownum} and timelimit=#{limit}")
    double getMoneyAll(@Param("borrownum") String borrownum,@Param("limit") Integer limit);

    /**
     * 判断密码是否正确
     * @param username
     * @return
     */
   @Select("select paypwd from userinfo where uname=#{username}")
   String balancePwd(String username);

    /**
     * 还款修改状态信息
     * @param borrownum
     * @param timelimit
     * @return
     */
   @Update("update repayinfo set stateid=2,huankuantime=sysdate where borrownum=#{borrownum} and timelimit=#{timelimit}")
    int balanceUpdateLimit(@Param("borrownum") String borrownum,@Param("timelimit") Integer timelimit);

    /**
     * 查询还款信息
     * @param limit
     * @param borrownum
     * @return
     */
    @Select("select to_char(huankuantime,'yyyy-mm-dd hh:mi:ss') from repayinfo where borrownum=#{borrownum} and timelimit=#{limit}")
    String gethuankuanTime(@Param("limit") Integer limit,@Param("borrownum") String borrownum);

    /**
     * 添加已还期数
     * @param borrownum
     * @param sumTime
     * @return
     */
    @Update("update borrow set yettime=yettime+#{sumTime} where borrownum=#{borrownum}")
    int updateBorrowYetLimit(@Param("borrownum") String borrownum,@Param("sumTime") Integer sumTime);

    /**
     * 查询订单的期数
     * @param borrownum
     * @return
     */
    @Select("select timelimit,payment from borrow where borrownum=#{borrownum}")
    Map getlimits(@Param("borrownum") String borrownum);

    /**
     * 修改已还完得订单状态
     * @param borrownum
     * @return
     */
    @Update("update borrow set stateid=10 where borrownum=#{borrownum} and stateid=8")
    int updateStatus(@Param("borrownum") String borrownum);

    /**
     * 修改用户账户余额
     * @param map
     * @return
     */
    @Update("update userinfo set amount=amount-#{huanMoney} where uname=#{userName}")
    int updateAmount(Map map);
}
