package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

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
     * 根据借款人查询投标信息
     * @param map
     * @return
     */
    @Select("<script>select t.realname,t.tamount,t.ttime,t.tway from tender t,borrow b where t.borrower=b.applicant</script>")
    List<Map> getPage(Map map);


    @Select("<script>select t.realname,t.tamount,t.ttime,t.tway from tender t,borrow b where t.borrower=b.applicant " +
            "<if test=\"realName!=null and realName!=''\"> and t.realname like '%'||#{realName}||'%'</if> </script>")
    List<Map> getPageByParams(Map map);

    /**
     * 添加投标
     * @param map
     * @return
     */
    @Insert("insert into tender(id,realname,tamount,ttime,tway,userid) values(seq_tender_id.nextval,#{realName},#{tamount},to_date(to_char(sysdate,'yyyy-mm-dd','yyyy-mm-dd'),#{tway},#{userid})")
    int add(Map map);

}



