package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.UserLoginDao;
import com.aaa.ssm.entity.Admin;
import com.aaa.ssm.entity.UserRegister;
import com.aaa.ssm.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * className:UserLoginServiceImpl
 * discription:
 * author:fhm
 * createTime:2018-12-08 20:45
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {

    //依赖注入dao层
    @Autowired
    private UserLoginDao userLoginDao;

    @Autowired
    private HttpSession session;

    @Override
    public UserRegister getUser(Map map) {
        List<UserRegister> userList = userLoginDao.getUser(map);
        if (userList!=null&&userList.size()>0){
            return userList.get(0);
        }
        return null;
    }

    /**
     * 根据用户登录ID找用户（后台）
     * @return
     */
    @Override
    public List<Map> getEmpByAdminid() {
        Admin user=(Admin) session.getAttribute("admin");
        Integer adminid = user.getId();
        List<Map> empList = userLoginDao.getEmpByAdminid(adminid);
        return empList;
    }
}
