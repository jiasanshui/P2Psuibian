package com.aaa.ssm.service;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * className:EmpService
 * discription:
 * author:yb
 * createTime:2018-12-17 15:05
 */
public interface EmpService {
    /**
     * 获取员工列表
     */
    List<Map> getList(Map map);
    /**
     *获取员工状态信息(员工状态下拉)
     */
    List<Map>getEmpList();
    /**
     * 员工添加
     * @param map
     * @return
     */
    int add(Map map);

    /**
     * 员工更新
     * @param map
     * @return
     */
    int update(Map map);

    /**
     * 员工删除
     * @param id,
     * @return
     */
    int delete(Integer id);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int bathDelete(String ids);
    /**
     * 根据部门编号获取员工信息
     */
    List<Map>getempInfo(Integer deptid);
    /**
     * 唯一性校验
     */
    //员工编号
    Map getEmpByEmpno(String empno);
    //手机号码
    Map getEmpByPhone(String phone);
    //身份证号
    Map getEmpByIdcard(String idcard);
}
