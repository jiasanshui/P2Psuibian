package com.aaa.ssm.dao;

import com.aaa.ssm.entity.TreeNode;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * className:PowerDao
 * discription:
 * author:fhm
 * createTime:2018-12-16 20:19
 */
@Component
public interface PowerDao {

    /**
     * 获取权限数据(获取登陆人的角色对应所有权限)
     * @return
     */
    @Select("select * from tb_power where id in" +
            "(select powerid from tb_role_power where roleid=" +
            "(select roleid from emp where emp.state=1 and adminid=" +
            "(select id from admin where id=#{userid})))")
    List<TreeNode> getPowerList(Integer userid);

    /**
     * 根据登陆角色信息，查询对应权限，出对应的权限树
     * @param roleid
     * @return
     */
    @Select("select * from tb_power where id in " +
            "(select powerid from tb_role_power where roleid=#{roleid})")
    List<TreeNode> getListByRole(Integer roleid);

    /**
     * 根据ID获取集合对象(不用)
     * @param powerId
     * @return
     */
    @Select("select * from tb_power where id=#{powerId}")
    List<Map<String,Object>> getById(int powerId);

    /**
     * 权限树去更新操作 根据id获取权限的信息
     * @param id
     * @return
     */
    @Select("select * from tb_power where id=#{id}")
    TreeNode getPowerById(Integer id);

    /**
     * 添加(easyui)
     * @param map
     * @return
     */
    @Insert("insert into tb_power(id,label,url,parentid,iconclass) " +
            "values(seq_tbpower_id.nextval,#{label},#{url},#{parentid},#{iconclass})")
    int add(Map map);

    /**
     * 修改(easyui)
     * @param map
     * @return
     */
    @Update("update tb_power set label=#{label},url=#{url},parentid=#{parentid}," +
            "iconclass=#{iconclass} where id=#{id}")
    int update(Map map);

    /**
     * 删除
     * @param powerId
     * @return
     */
    @Delete("delete from tb_power where id=#{powerId}")
    int delete(Integer powerId);

    /**
     * tb_role_permission 往角色权限关联表添加数据
     * @param roleid powerid
     * @return
     */
    @Insert("insert into tb_role_power (id,roleid,powerid) values (seq_roleid_power_id.nextval,#{param1},#{param2})")
    int saveRolePower(Integer roleid,Integer powerid);

    /**
     * 给角色添加权限时的删除方法
     * @param roleid
     * @return
     */
    @Delete("delete from tb_role_power where roleid=#{roleid}")
    int delRolePower(Integer roleid);

    /**
     * 获取所有的权限树（easyui）
     * @return
     */
    @Select("select * from tb_power")
    List<Map> getTreeList();

    /**
     * 添加(vue+element)
     * @param map
     * @return
     */
    @Insert("insert into tb_power(id,label,url,parentid,iconclass) " +
            "values(seq_tbpower_id.nextval,#{label},#{url},#{parentid},#{iconClass})")
    int addMenu(Map map);

    /**
     * 修改(vue+element)
     * @param map
     * @return
     */
    @Update("update tb_power set label=#{label},url=#{url},parentid=#{parentid}," +
            "iconclass=#{iconClass} where id=#{id}")
    int updateMenu(Map map);

    /**
     * 表单唯一性验证
     * @param label
     * @return
     */
    @Select("select * from tb_power where label=#{label}")
    List<Map> getLabel(String label);
}
