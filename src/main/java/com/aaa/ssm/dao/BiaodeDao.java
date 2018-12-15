package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * className:BiaodeDao
 * discription:
 * author:fhm
 * createTime:2018-12-15 09:32
 */
public interface BiaodeDao {
    /**
     * 用户借款标的列表查询
     * @param map
     */
    @Select("<script>select borrowid,username,applicant,tel,danbaostyle,quantity,cost,days,borrowmoney,timelimit,apr," +
            "purpose,des,payment,borrownum from borrow where stateid=1 \n "+
            "<if test=\"applicant!=null and applicant!=''\">  and applicant like '%'||#{applicant}||'%'</if>" +
            "<if test=\"tel!=null and tel!=''\">  and tel =#{tel}</if>" +
            "</script>")
    List<Map> getList(Map map);

    /**
     * 审核通过，更新用户信息表审核状态,招标开始时间
     * @param map
     * @return
     */
    @Update("update borrow set stateid=2,starttime=sysdate where borrowid=#{BORROWID}")
    int update(Map map);

    /**
     * 审核驳回，更新用户借款表审核状态
     * @param map
     * @return
     */
    @Update("update borrow set stateid=3 where borrowid=#{BORROWID}")
    int edit(Map map);

}
