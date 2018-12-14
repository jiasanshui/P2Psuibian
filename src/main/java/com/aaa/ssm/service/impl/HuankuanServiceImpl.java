package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.HuankuanDao;
import com.aaa.ssm.service.HuankuanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:HuankuanServiceImpl
 * discription:
 * author:jiasanshui
 * createTime:2018-12-14 11:57
 */
@Service
public class HuankuanServiceImpl implements HuankuanService {

    @Autowired
    private HuankuanDao huankuanDao;

    @Override
    public List<Map> getListByUName(Map map) {
        return huankuanDao.getListByUName(map);
    }
}
