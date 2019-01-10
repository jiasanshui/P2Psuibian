package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public interface HuiKuaiDao {
    /**
     * 查询每个用户每笔回款信息
     * @param userId
     * @return
     */
    @Select("<script> select * from (select rownum rn,t.tamount,b.tapr,to_char(add_months(r.starttime,b.timelimit),'yyyy-mm-dd') " +
            "as starttime, " +
            "b.des,b.timelimit,b.danbaostyle from tender t " +
            "left join borrow b on t.borrownum=b.borrownum left join " +
            "repayinfo r on b.borrownum=r.borrownum where t.userid=#{userId} and rownum &lt; #{end} " +
            "<if test=\"starttime!=null and starttime!=''\"> and r.starttime=#{startTime}</if>) " +
            "a where a.rn &gt; #{start}</script>")
    List<Map> getHuiKuaiList(Map map);
    /**
            * 获取分页总数量
     * @param map
     * @return
             */
    @Select("<script> select count(*) cnt from tender where 1=1 and userid=#{userId} " +
            "<if test=\"endtime!=null and endtime!=''\"> and b.endtime=#{endTime}</if></script>")
    List<Map> getPageCount(Map map);
}
