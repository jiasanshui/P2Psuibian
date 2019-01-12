package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * className:DepositsRecordDao
 * discription:
 * author:yb
 * createTime:2019-01-04 17:18
 */
@Component
public interface DepositsRecordDao {
    /**
     * 通过投资得到记录表
     * @param userid
     * @return
     */
    @Select("select * from tender where userid=#{userid}")
    List<Map> getRecordByDeposits(Integer userid);

    /**
     * 获取分页总数量
     * @param map
     * @return
     */
    @Select("<script>select count(*) cnt from tender where 1=1 and userid=#{userId} " +
            "<if test=\"borrownum!=null and borrownum!=''\">  and borrownum =#{borrownum}</if></script>")
    List<Map> getPageCount(Map map);

    /**
     * 投资列表分页
     * @param map
     * @return
     */
    @Select("<script>select * from (select rownum rn,t.id,t.realname,t.tamount," +
            "to_char(t.ttime,'yyyy-mm-dd HH24:mi:ss') ttime,t.tway,t.borrownum,t.userid,t.tendernum " +
            "from tender t where rownum &lt; #{end} and t.userid=#{userId}" +
            "<if test=\"borrownum!=null and borrownum!=''\">  and t.borrownum =#{borrownum}</if> ) " +
            "a where a.rn &gt; #{start}</script>")
    List<Map> getTenderPage(Map map);

    /**
     * 账户总揽3条投资记录
     * @param map
     * @return
     */
    @Select("select rownum,id,realname,tamount,to_char(ttime,'yyyy-mm-dd HH24:mi:ss') ttime," +
            "tway,borrownum,userid,tendernum from tender where userid=#{userId} and rownum<4")
    List<Map> getThreeTender(Map map);
}
