package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.HuiKuaiDao;
import com.aaa.ssm.service.HuiKuanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:HuiKuanServiceImpl
 * discription:
 * author:xiefuzhi
 * createTime:2019-01-09 23:27
 */
@Service
public class HuiKuanServiceImpl implements HuiKuanService {
    //依赖注入
    @Autowired
    private HuiKuaiDao huiKuaiDao;


    @Override
    public List<Map> getHuiKuaiList(Integer userId) {
        return huiKuaiDao.getHuiKuaiList(userId);
    }

    @Override
    public int getPageCount(Map map) {
        return huiKuaiDao.getPageCount(map);
    }
}
