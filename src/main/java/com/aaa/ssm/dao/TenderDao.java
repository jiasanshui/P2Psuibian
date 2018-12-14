package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface TenderDao {


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
