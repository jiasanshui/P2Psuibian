package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.EmpDao;
import com.aaa.ssm.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * className:EmpServiveImpl
 * discription:
 * author:yb
 * createTime:2018-12-17 15:07
 */
@Service
public class EmpServiveImpl implements EmpService {
    @Autowired
    private EmpDao empDao;

    @Override
    public List<Map> getList(Map map) {
        return empDao.getList(map);
    }

    @Override
    public List<Map> getempInfo(Integer deptid) {
        return empDao.getempInfo(deptid);
    }

    @Override
    public Map getEmpByEmpno(String empno) {
        List<Map> empByEmpno = empDao.getEmpByEmpno(empno);
        if (empByEmpno!=null&&empByEmpno.size()>0){
            return empByEmpno.get(0);
        }
        return null;
    }

    @Override
    public Map getEmpByPhone(String phone) {
        List<Map> empByPhone = empDao.getEmpByPhone(phone);
        if (empByPhone!=null&&empByPhone.size()>0){
            return empByPhone.get(0);
        }
        return null;
    }

    @Override
    public Map getEmpByIdcard(String idcard) {
        List<Map> empByIdcard = empDao.getEmpByIdcard(idcard);
        if (empByIdcard!=null&&empByIdcard.size()>0){
            return empByIdcard.get(0);
        }
        return null;
    }

//    /**
//     * 员工状态下拉
//     * @return
//     */
//    @Override
//    public List<Map> getEmpList() {
//        return empDao.getEmpList();
//    }

//    /**
//     * 省下拉框
//     * @return
//     */
//    @Override
//    public List<Map> getShengList() {
//        return empDao.getShengList();
//    }

//    /**
//     * 市下拉框
//     * @return
//     */
//    @Override
//    public List<Map> getShiList() {
//        return empDao.getShiList();
//    }

//    /**
//     * 区下拉框
//     * @return
//     */
//    @Override
//    public List<Map> getQuList() {
//        return empDao.getQuList();
//    }

    @Override
    public int add(Map map) {
        return empDao.add(map);
    }

    @Override
    public int update(Map map) {
        return empDao.update(map);
    }

    @Override
    public int delete(Integer id) {
        return empDao.delete(id);
    }

    @Override
    public int bathDelete(String ids) {
        String[] idsArray = ids.split(",");
        List list = new ArrayList();

        for (String s : idsArray) {
            list.add(s);

        }
        return empDao.batchDelete(list);
    }

}
