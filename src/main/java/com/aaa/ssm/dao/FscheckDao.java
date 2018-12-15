package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:FscheckDao
 * discription:满标审核表的创建
 * author:yb
 * createTime:2018-12-14 16:49
 */
public interface FscheckDao {
    /**
     * 用户满标审核信息列表查询
     */
    @Select("<script>select borrownum,username,purpose,applicant,borrowmoney,meltmoney,timelimit,repaystyle,manager from fscheck"+
            "<where><if test=\"applicant!=null and applicant!=''\"> and applicant like '%' || '%'</if>"+
            "</where> </script>")
    List<Map>getList(Map map);
    /**
     * 添加满标
     */
    @Insert("insert into fscheck(borrownum,username,purpose,applicant,borrowmoney,meltmoney,timelimit,repaystyle,manager values(#{borrownum},#{username},#{purpose},#{applicant},#{borrowmoney},#{meltmoney},#{timelimit},#{repaystyle},#{manager}))")
    int add(Map map);
}
