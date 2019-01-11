package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * className:DeptDao
 * discription:
 * author:yb
 * createTime:2018-12-16 19:56
 */
@Component
public interface DeptDao {
    /**
     * 获取部门信息（员工下拉框用）
     */
    @Select("select deptid,dname,describes,to_char(dtime,'yyyy-mm-dd')as dtime,dstatus from dept where dstatus=1")
    List<Map> getList();

    /**
     * 根据参数获取部门信息
     * @param map
     * @return
     */
    @Select("<script>select d.deptid,d.dname,d.describes,to_char(d.dtime,'yyyy-mm-dd')as dtime,d.dstatus,s.id,s.state from dept d left join deptstate s on d.dstatus=s.id"+
            "<if test=\"dname!=null and dname!=''\"> and dname like '%'||#{dname}||'%'</if>"+
            "<if test=\"dstatus!=null and dstatus!=''\"> and dstatus=#{dstatus}</if>"+
            "</script>")
    List<Map> getDeptList(Map map);

    /**
     * 部门添加
     */
    @Insert("insert into dept values(seq_deptid.nextval,#{DNAME},#{DESCRIBES},sysdate,1)")
    int add (Map map);
    /**
     * 部门更新
     */
    @Update("update dept set dname=#{DNAME},describes=#{DESCRIBES},dtime=to_date(#{DTIME},'yyyy-mm-dd'),dstatus=#{DSTATUS} where deptid=#{DEPTID}")
    int update(Map map);
    /**
     * 部门假删除
     */
    @Delete("update dept set dstatus=2 where deptid=#{deptid}")
    int delete(Integer deptid);
    /**
     * 根据部门编号获取部门名称
     */
    @Select("select * from dept where dname=#{dname}")
    List<Map>getDeptByDname(String dname);

    /**
     * 部门状态下拉框
     * @return
     */
    @Select("select id,state from deptstate")
    List<Map>getDeptInfo();
}
