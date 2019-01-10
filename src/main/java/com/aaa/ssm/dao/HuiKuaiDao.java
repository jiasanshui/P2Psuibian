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
    @Select("<script>select t.tamount,b.apr,b.endtime,b.des,b.timelimit,b.danbaostyle from tender t,borrow b " +
            "where t.borrownum=b.borrownum and t.userid = ${userId}" +
            "<if test=\"endtime!=null and endtime!=''\"> and b.endtime=${endTime}</if></script>")
    List<Map> getHuiKuaiList(Integer userId);
    /**
            * 获取分页总数量
     * @param map
     * @return
             */
    @Select("<script>select count(*) cnt from tender where 1=1 and userid=#{userId} " +
            "<if test=\"endtime!=null and endtime!=''\"> and b.endtime=${endTime}</if></script>")
    int getPageCount(Map map);
}
