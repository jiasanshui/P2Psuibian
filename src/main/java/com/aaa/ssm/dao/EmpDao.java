package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * className:EmpDao
 * discription:
 * author:yb
 * createTime:2018-12-16 20:19
 */
public interface EmpDao {
    /**
     * 获取员工列表
     */
    @Select("<script>select e.id,e.empno,e.ename,e.idcard,e.phone,e.address,to_char(e.ruzhitime,'yyyy-mm-dd') as ruzhitime," +
            "e.state,e.deptid,e.roleid,e.adminid,e.comm,e.sal,e.shengname,e.shiname,e.quname,d.dname,d.describes,to_char(d.dtime,'yyyy-mm-dd') as dtime,d.dstatus,s.eid,s.states " +
            " from emp e left join dept d on e.deptid=d.deptid left join empstate s on e.state=s.eid  where e.state=1 " +
            "<if test=\"empNo!=null and empNo!=''\"> and e.empno=#{empNo}</if> " +
            "<if test=\"ename!=null and ename!=''\"> and e.ename like '%'||#{ename}||'%'</if> " +
            "<if test=\"deptid!=null and deptid!=''\"> and e.deptid =#{deptid}</if> " +
            "<if test=\"state!=null and state!=''\"> and e.state =#{state}</if> " +
            "</script>")
        List<Map> getList(Map map);
    /**
     * 员工添加
     * @param map
     * @return
     */
    @Insert("insert into emp values(seq_empid.nextval,#{EMPNO},#{ENAME},#{IDCARD},#{PHONE},#{ADDRESS},sysdate,1,#{DEPTID},#{ROLEID},#{ADMINID},#{COMM},#{SAL},#{SHENGNAME},#{SHINAME},#{QUNAME})")
    int add(Map map);
    /**
     * 员工更新
     * @param map
     * @return
     */
    @Update("update emp set empno=#{EMPNO},ename=#{ENAME},idcard=#{IDCARD},phone=#{PHONE},address=#{ADDRESS},ruzhitime=to_date(#{RUZHITIME},'yyyy-mm-dd'),state=#{STATE},deptid=#{DEPTID},roleid=#{ROLEID},adminid=#{ADMINID},COMM=#{COMM},SAL=#{SAL},shengname=#{SHENGNAME},shiname=#{SHINAME},quname=#{QUNAME} where id=#{ID}")
    int update(Map map);
    /**
     * 员工删除
     * @param id
     * @return
     */
    @Update("update emp set state=2 where id=#{ID}")
    int delete(Integer id);
    /**
     * 批量删除
     */
    @Update("<script>update emp set  state=2  and id in" +
            "<foreach collection='list' item='en' open='(' close=')' separator=','>#{en}</foreach>" +
            "</script>")
    int  batchDelete(List list);
    /**
     * 根据部门编号获取员工信息
     */
    @Select("select * from emp where deptid=#{deptid}")
    List<Map>getempInfo(Integer deptid);
    /**
     * 唯一性校验
     */
    //员工编号
     @Select("select * from emp where empno=#{empno}")
    List<Map>getEmpByEmpno(String empno);
    //电话号码
    @Select("select * from emp where phone=#{phone}")
    List<Map>getEmpByPhone(String phone);
    //身份证号
    @Select("select * from emp where idcard=#{idcard}")
    List<Map>getEmpByIdcard(String idcard);
}
