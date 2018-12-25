package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * className:BiaodeDao
 * discription:
 * author:fhm
 * createTime:2018-12-15 09:32
 */
@Component
public interface BiaodeDao {
    /**
     * 用户借款标的列表查询（待审核）
     * @param map
     */
    @Select("<script>select borrowid,username,userid,applicant,tel,danbaostyle,quantity,cost,days,borrowmoney,timelimit,apr," +
            "purpose,des,payment,borrownum,winbidmoney from borrow where stateid=1 \n "+
            "<if test=\"userid!=null and userid!=''\">  and userid=#{userid}</if>" +
            "<if test=\"applicant!=null and applicant!=''\">  and applicant like '%'||#{applicant}||'%'</if>" +
            "<if test=\"borrownum!=null and borrownum!=''\">  and borrownum =#{borrownum}</if>" +
            "</script>")
    List<Map> getList(Map map);

    /**
     * 招标中的标的分页列表
     * @param map
     */
    @Select("<script>select borrowid,username,userid,applicant,tel,danbaostyle,quantity,cost,days,borrowmoney,timelimit,apr," +
            "purpose,des,payment,borrownum,winbidmoney from borrow where stateid=2 \n "+
            "<if test=\"applicant!=null and applicant!=''\">  and applicant like '%'||#{applicant}||'%'</if>" +
            "<if test=\"borrownum!=null and borrownum!=''\">  and borrownum =#{borrownum}</if>" +
            "</script>")
    List<Map> getPage(Map map);

    /**
     * 获取待放款的投标信息
     * @param map
     */
    @Select("<script>select borrowid,username,userid,applicant,tel,danbaostyle,quantity,cost,days,borrowmoney,timelimit,apr," +
            "purpose,des,payment,borrownum,winbidmoney from borrow where stateid=6 \n "+
            "<if test=\"applicant!=null and applicant!=''\">  and applicant like '%'||#{applicant}||'%'</if>" +
            "<if test=\"borrownum!=null and borrownum!=''\">  and borrownum =#{borrownum}</if>" +
            "</script>")
    List<Map> getPageByLoan(Map map);

    /**
     * 审核通过，更新用户信息表审核状态,招标开始时间
     * @param map
     * @return
     */
    @Update("update borrow set stateid=2,starttime=sysdate,endtime=(#{DAYS}+sysdate) where borrowid=#{BORROWID}")
    int update(Map map);

    /**
     * 初审失败，更新用户借款标审核状态
     * @param map
     * @return
     */
    @Update("update borrow set stateid=3 where borrowid=#{BORROWID}")
    int edit(Map map);

    /**
     * 满标一审失败，更新用户借款标审核状态
     * @param map
     * @return
     */
    @Update("update borrow set stateid=5 where borrowid=#{BORROWID}")
    int fkCheck(Map map);

    /**
     * 满标一审通过，更新用户借款标审核状态
     * @param map
     * @return
     */
    @Update("update borrow set stateid=7 where borrowid=#{BORROWID}")
    int fkCheckOne(Map map);

    /**
     * 满标二审失败，更新用户借款标审核状态
     * @param map
     * @return
     */
    @Update("update borrow set stateid=11 where borrowid=#{BORROWID}")
    int updatebidStatu(Map map);

    /**
     * 满标二审通过，更新用户借款标审核状态
     * @param map
     * @return
     */
    @Update("update borrow set stateid=8 where borrowid=#{BORROWID}")
    int updatebidStatuOne(Map map);

    /**
     * 更改标的状态
     * @param map
     * @return
     */
    @Update("update borrow set stateid=#{STATEID} where borrownum=#{BORROWNUM} ")
    int updateBidState(Map map);

    /**
     * 根据标的编号查询历史审核信息
     * @param borrownum
     * @return
     */
    @Select("select aduitbid.borrownum,aduitbid.operatorid,to_char(aduitbid.aduittime,'yyyy-mm-dd') aduittime," +
            "aduitbid.aduitresult,aduitbid.aduitremark,emp.ename from aduitbid " +
            "left join emp on aduitbid.operatorid=emp.id where borrownum=#{borrownum}")
    List<Map> getAduitBid(String borrownum);
}
