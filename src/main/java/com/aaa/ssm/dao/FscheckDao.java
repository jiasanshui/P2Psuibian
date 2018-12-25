package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * className:FscheckDao
 * discription:满标审核表的创建
 * author:yb
 * createTime:2018-12-14 16:49
 */
@Component
public interface FscheckDao {
    /**
     * 用户满标待审核列表查询
     */
    @Select("<script>select borrowid,username,userid,applicant,tel,danbaostyle,quantity,cost,days,borrowmoney,timelimit,apr," +
            "purpose,des,payment,borrownum,winbidmoney from borrow where stateid=6 \n "+
            "<if test=\"userid!=null and userid!=''\">  and userid=#{userid}</if>" +
            "<if test=\"applicant!=null and applicant!=''\">  and applicant like '%'||#{applicant}||'%'</if>" +
            "<if test=\"borrownum!=null and borrownum!=''\">  and borrownum =#{borrownum}</if>" +
            "</script>")
    List<Map>getList(Map map);

    /**
     * 添加满标
     */
    @Insert("insert into fscheck(borrownum,username,purpose,applicant,borrowmoney,meltmoney,timelimit,repaystyle,managerid) values(#{borrownum},#{username},#{purpose},#{applicant},#{borrowmoney},#{meltmoney},#{timelimit},#{repaystyle},#{managerid}))")
    int add(Map map);
}
