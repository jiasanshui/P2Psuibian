package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:RepayRecordDao
 * discription:还款记录表
 * author:yb
 * createTime:2019-01-09 21:50
 */
public interface RepayRecordDao {
    /**
     * 通过还款得到记录表
     * @param username
     * @return
     */
    @Select("select * from repayinfo where username=#{username}")
    List<Map> getRecordByRepay(String username);

    /**
     * 获取分页总数量
     * @param map
     * @return
     */
    @Select("<script>select count(*) cnt from repayinfo where 1=1 and username=#{userName} and stateid=2 " +
            "<if test=\"borrownum!=null and borrownum!=''\">  and borrownum =#{borrownum}</if></script>")
    List<Map> getPageCount(Map map);

    /**
     * 还款列表分页
     * @param map
     * @return
     */
    @Select("<script>select * from (select rownum rn,t.rid,t.borrownum,t.username,t.realname,t.benjin,t.lixi,t.timelimit," +
            "to_char(t.starttime,'yyyy-mm-dd HH24:mi:ss') starttime,to_char(t.huankuantime,'yyyy-mm-dd HH24:mi:ss') huankuantime,to_char(t.repaylimit,'yyyy-mm-dd HH24:mi:ss') repaylimit " +
            "from repayinfo t where rownum &lt; #{end} and username=#{userName} and t.stateid=2 " +
            "<if test=\"borrownum!=null and borrownum!=''\">  and borrownum =#{borrownum}</if> ) " +
            "a where a.rn &gt; #{start}</script>")
    List<Map> getRepayPage(Map map);
}

