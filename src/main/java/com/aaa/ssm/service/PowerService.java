package com.aaa.ssm.service;

import com.aaa.ssm.entity.Permission;
import com.aaa.ssm.entity.TbRole;
import com.aaa.ssm.entity.TreeNode;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * className:PowerService
 * discription:
 * author:fhm
 * createTime:2018-12-16 20:43
 */
@Component
public interface PowerService {
    /**
     * 获取权限数据(获取登陆人的角色对应所有权限)
     * @return
     */
    List<TreeNode> getPowerList();

    /**
     * 根据登陆角色信息，查询对应权限，出对应的权限树
     * @param
     * @return
     */
    List<TreeNode> getCheckList(Integer roleid);

    /**
     * tb_role_power 往角色权限关联表添加数据
     * @param role
     * @return
     */
    int saveRolePower(TbRole role);

    /**
     * 根据ID获取集合对象(不用)
     * @param powerId
     * @return
     */
    Map<String,Object> getById(int powerId);

    /**
     * 权限树更新操作 根据id获取权限的 信息
     * @param id
     * @return
     */
    TreeNode getPowerById(Integer id);

    /**
     * 修改
     * @param map
     * @return
     */
    int update(Map map);

    /**
     * 添加
     * @param map
     * @return
     */
    int add(Map map);

    /**
     * 删除
     * @param powerId
     * @return
     */
    int delete(Integer powerId);

    /**
     * 获取所有的权限树（easyui）
     * @return
     */
    List<Permission> getTreeList();

    /**
     * 获取所有的权限树2（easyui）
     * @return
     */
    List<Permission> gettreeList();

    /**
     * 修改(vue+element ui)
     * @param map
     * @return
     */
    int updateMenu(Map map);

    /**
     * 添加(vue+element ui)
     * @param map
     * @return
     */
    int addMenu(Map map);


    /**
     * 表单唯一性验证
     * @param label
     * @return
     */
    List<Map> getLabel(String label);
}
