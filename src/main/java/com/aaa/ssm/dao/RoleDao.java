package com.aaa.ssm.dao;

import com.aaa.ssm.entity.TbRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * className:RoleDao
 * discription:
 * author:fhm
 * createTime:2018-12-19 08:50
 */
@Component
public interface RoleDao {
    /**
     * 获取所有角色信息
     * @return
     */
    @Select("select id,name,descp,addtime,state from tb_role order by id")
    List<TbRole> getRoles();

    /**
     * 获取所有正常使用的角色信息
     * @return
     */
    @Select("select id,name,descp,addtime,state from tb_role where state='可用' order by id")
    List<TbRole> getRolesByState();
    /**
     * 角色添加
     * @param role
     * @return
     */
    @Insert("insert into tb_role(id,name,descp,addtime,state) values(seq_roleid.nextval,#{name},#{descp},to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd'),#{state})")
    int add(TbRole role);

    /**
     * 角色更新
     * @param role
     * @return
     */
    @Update("update tb_role set name=#{name},descp=#{descp},state=#{state} where id=#{id}")
    int update(TbRole role);

    /**
     * 注销角色时，查询该角色下是否有员工
     * @param roleid
     * @return
     */
    @Select("select count(*) from emp where roleid=#{roleid}")
    int haveEmp(Integer roleid);

    /**
     * 注销角色(删除)
     * @param roleid
     * @return
     */
    @Update("update tb_role set state='不可用' where id=#{roleid}")
    int delete(Integer roleid);
}
