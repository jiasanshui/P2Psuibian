package com.aaa.ssm.service.impl;/**
 * className:PCAServiceImpl
 * discription:
 * author:jiasanshui
 * createTime:2018-12-08 15:39
 */

import com.aaa.ssm.dao.PCADao;
import com.aaa.ssm.service.PCAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *className:PCAServiceImpl
 *discription:
 *author:jiasanshui
 *createTime:2018-12-08 15:39
 */
@Service
public class PCAServiceImpl implements PCAService{

    @Autowired
    private PCADao pcaDao;

    @Override
    public List<Map> getProvince() {
        return pcaDao.getProvince();
    }

    @Override
    public List<Map> getCity(Integer Pid) {
        return pcaDao.getCity(Pid);
    }

    @Override
    public List<Map> getArea(Integer Cid) {
        return pcaDao.getArea(Cid);
    }
}
