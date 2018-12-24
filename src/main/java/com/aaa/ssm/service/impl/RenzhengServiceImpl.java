package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.RenzhengDao;
import com.aaa.ssm.service.RenzhengService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * className:RenzhengServiceImpl
 * discription:
 * author:jiasanshui
 * createTime:2018-12-11 08:26
 */
@Service
public class RenzhengServiceImpl implements RenzhengService{

    @Autowired
    private RenzhengDao renzhengDao;

    @Override
    public int update(Map map) {
        return renzhengDao.update(map);
    }

    @Override
    public Map isRenZheng(String userName) {
        return renzhengDao.isRenZheng(userName);
    }
}
