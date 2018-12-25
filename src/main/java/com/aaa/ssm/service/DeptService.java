package com.aaa.ssm.service;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * className:DeptService
 * discription:
 * author:yb
 * createTime:2018-12-16 20:29
 */
public interface DeptService {
    /**
     * 部门列表查询员工
     * @return
     */
    List<Map> getList();
    /**
     * 根据参数获取部门信息
     * @param map
     * @return
     */
    List<Map> getDeptList(Map map);
    /**
     * 获取部门状态信息下拉
     */
    List<Map>getState();
    /**
     * 部门添加
     */
    int add (Map map);
    /**
     * 部门更新
     */
    int update(Map map);
    /**
     * 部门删除
     */
    int delete(Integer deptid);
    /**
     * 根据部门编号获取部门名称
     */
    List<Map>getDeptByDname(String dname);

}
