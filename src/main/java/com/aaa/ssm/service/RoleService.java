package com.aaa.ssm.service;

import com.aaa.ssm.entity.TbRole;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:RoleService
 * discription:
 * author:fhm
 * createTime:2018-12-19 09:01
 */
public interface RoleService {
    /**
     * 获取所有角色信息
     * @return
     */
    List<TbRole> getRoles();
    /**
     * 获取所有正常使用的角色信息
     * @return
     */
    List<TbRole> getRolesByState();

    /**
     * 角色添加
     * @param role
     * @return
     */
    int add(TbRole role);

    /**
     * 角色更新
     * @param role
     * @return
     */
    int update(TbRole role);

    /**
     * 角色删除（注销）
     * @param roleid
     * @return
     */
    int delete(Integer roleid);
    /**
     * 唯一性校验
     * @param name
     * @return
     */
    //角色名称
    Map getRoleByName(String name);

    /**
     * 查询所有的角色名称
     * @return
     */
    List<Map> getNames();
}
