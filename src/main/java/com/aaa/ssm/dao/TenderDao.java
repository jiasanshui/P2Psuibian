package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public interface TenderDao {

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

    /**
     * 根据借款订单号查询投标信息
     * @param BORROWNUM
     * @return
     */
    @Select("<script>select t.realname,t.tamount,t.ttime,t.tway \n" +
            "from tender t left join borrow b on t.borrownum=b.borrownum where t.borrownum = #{BORROWNUM}</script>")
    List<Map> getPage(String BORROWNUM);


    /**
     * 根据借款人查询投标信息
     * @param map
     * @return
     */
    @Select("<script>select t.realname,t.tamount,t.ttime,t.tway \n" +
            "from tender t left join borrow b on t.userid=b.userid where t.userid = #{userid}</script>")
    List<Map> getTenderPage(Map map);


    @Select("<script>select t.realname,t.tamount,t.ttime,t.tway from tender t,borrow b where t.borrower=b.applicant " +
            "<if test=\"realName!=null and realName!=''\"> and t.realname like '%'||#{realName}||'%'</if> </script>")
    List<Map> getPageByParams(Map map);

    /**
     * 添加投标
     * @param map,BORROWNUM
     * @return
     */
    @Insert("insert into tender(id,realname,tamount,ttime,tway,userid,borrownum,tendernum) " +
            "values(seq_tender_id.nextval,#{realName},#{tamount},sysdate,#{tway},#{userid},#{borrowNum})")
    int add(Map map);

    /**
     * 根据借款编号查询投标详情以及投标人信息
     * @param borrownum
     * @return
     */
    @Select("select tender.id,tender.realname,tender.tamount,tender.borrownum,tender.userid,tender.tendernum,to_char(tender.ttime,'yyyy-mm-dd') ttime,userinfo.idcard," +
            "userinfo.phone,userinfo.address from tender left join userinfo on userinfo.userid=tender.userid where borrownum=#{borrownum}")
    List<Map> getTenderinfoByParam(String borrownum);

    /**
     * 通过投资人tendernum查询投资列表
     * @param tendernum
     * @return
     */
    @Select("select id,realname,tamount,ttime,tway,userid,tendernum from tender " +
            "where tendernum=#{rendernum}")
    List<Map> getListByTenderNum(String tendernum);
}



