package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.ChongZhiDao;
import com.aaa.ssm.service.ChongzhiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * className:ChongZhiServiceImpl
 * discription:
 * author:jiasanshui
 * createTime:2018-12-29 15:37
 */
@Service
public class ChongZhiServiceImpl implements ChongzhiService {

    @Autowired
    private ChongZhiDao chongZhiDao;

    @Override
    public Map getBankByCard(String BCIDSix) {
        return chongZhiDao.getBankByCard(BCIDSix);
    }
}
