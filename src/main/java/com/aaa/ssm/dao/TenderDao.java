package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * className:TenderDao
 * discription:
 * author:hulu
 * createTime:2018-12-12 15:29
 */
public interface TenderDao {
    /**
     * 用户投标审核信息列表查询
     * @param map
     * @return
     * 如果使用注解的方式，动态sql必须在标签<script></script>
     * 如果使用<script></script>标签，mybatis 大于小于，必须使用&gt; &lt;
     */
    @Select("<script>select u.realname,b.userid,b.applicant,b.tel,b.timelimit,b.purpose,b.des,b.danbaostyle,b.quantity,b.cost,b.borrowmoney,b.payment,b.borrowid \n" +
            "from borrow b left join userInfo u on b.USERID=u.USERID where b.STATEID=1 \n "+
            "<if test=\"applicant!=null and applicant!=''\">  and applicant like '%'||#{applicant}||'%'</if>" +
            "<if test=\"tel!=null and tel!=''\">  and tel =#{tel}</if>" +
            "</script>")
    List<Map> getList(Map map);

    /**
     * 审核通过，更新用户信息表审核状态
     * @param borrowId
     * @return
     */
    @Update("update borrow set stateid=2 where userId=#{userId}")
    int update(Integer borrowId);

    /**
     * 审核驳回，更新用户信息表审核状态
     * @param map
     * @return
     */
    @Update("update borrow set stateid=3 where userId=#{userId}")
    int edit(Map map);

    /**
     * 审核驳回,向驳回表中插入数据
     * @param map
     * @return
     */
    @Insert("insert into tenderbohui(id,reason,opraterid) values(seq_tenderbohui_id.nextval,#{REASON},#{OPRATERID})")
    int addBohui(Map map);
}
