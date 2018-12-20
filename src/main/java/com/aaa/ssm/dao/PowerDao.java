package com.aaa.ssm.dao;

import com.aaa.ssm.entity.TreeNode;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

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
            "(select roleid from emp where adminid=" +
            "(select id from admin where id=#{userid})))")
    List<TreeNode> getPowerList(Integer userid);

}
