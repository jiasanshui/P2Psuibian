package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.RoleDao;
import com.aaa.ssm.entity.TbRole;
import com.aaa.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:RoleServiceImpl
 * discription:
 * author:fhm
 * createTime:2018-12-19 09:02
 */
@Service
public class RoleServiceImpl implements RoleService {
    //依赖注入
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<TbRole> getRoles() {
        return roleDao.getRoles();
    }

    @Override
    public List<TbRole> getRolesByState() {
        return roleDao.getRolesByState();
    }

    @Override
    public int add(TbRole role) {
        return roleDao.add(role);
    }

    @Override
    public int update(TbRole role) {
        String rolestate = role.getState();
        if (rolestate.equals("不可用")) {
            //查询该角色下是否有员工
            int i = roleDao.haveEmp(role.getId());
            if (i>0) {
                return -1;
            }
        }
        return roleDao.update(role);
    }

    @Override
    public int delete(Integer roleid) {
        //查询该角色下是否有员工
        int i = roleDao.haveEmp(roleid);
        if(i>0){
            return -1;
        }
        return roleDao.delete(roleid);
    }

    @Override
    public Map getRoleByName(String name) {
        List<Map> mapList = roleDao.getRoleByName(name);
        if (mapList!=null&&mapList.size()>0){
            return mapList.get(0);
        }
        return null;
    }

    /**
     * 查询所有的角色名称
     * @return
     */
    @Override
    public List<Map> getNames() {
        return roleDao.getNames();
    }
}
