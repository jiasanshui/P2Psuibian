package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.DeptDao;
import com.aaa.ssm.dao.EmpDao;
import com.aaa.ssm.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:DeptServiceImpl
 * discription:
 * author:yb
 * createTime:2018-12-16 20:31
 */
@Service
public class DeptServiceImpl implements DeptService {
    //依赖注入
    @Autowired
    private DeptDao deptDao;
    @Autowired
    private EmpDao empDao;

    @Override
    public List<Map> getDeptByDname(String dname) {
        return deptDao.getDeptByDname(dname);
    }



    @Override
    public List<Map> getList() {
        return deptDao.getList();
    }

    @Override
    public List<Map> getDeptList(Map map) {
        return deptDao.getDeptList(map);
    }

//    /**
//     * 部门状态下拉
//     * @return
//     */
//    @Override
//    public List<Map> getState() {
//        return deptDao.getState();
//    }


    @Override
    public int add(Map map) {
        return deptDao.add(map);
    }

    @Override
    public int update(Map map) {
        return deptDao.update(map);
    }

    /**
     * 当部门下没有员工时，删除该部门
     * @param deptid
     * @return
     */
    @Override
    public int delete(Integer deptid) {

        List<Map> list = empDao.getempInfo(deptid);

        if (list!=null&&list.size()>0){
            return 0;
        }else{
            return deptDao.delete(deptid);
        }
    }


}
