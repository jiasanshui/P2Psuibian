package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.UserMaterialsDao;
import com.aaa.ssm.service.UserMaterialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * className:UserMaterialsServiceImpl
 * discription:借款服务层实现类
 * author:yb
 * createTime:2018-12-11 15:34
 */
@Service
public class UserMaterialsServiceImpl implements UserMaterialsService {
    //依赖注入
    @Autowired
    private UserMaterialsDao userMaterialsDao;

    /**
     * 添加用户借款
     * @param map
     * @return
     */
    @Override
    public int addUserMaterials(Map map) {
        return userMaterialsDao.addUserMaterials(map);
    }
}
