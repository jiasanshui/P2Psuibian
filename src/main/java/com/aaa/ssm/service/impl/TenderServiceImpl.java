package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.TenderDao;
import com.aaa.ssm.service.TenderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:TenderServiceImpl
 * discription:
 * author:xiefuzhi
 * createTime:2018-12-11 19:25
 */
@Service
public class TenderServiceImpl implements TenderService {
    //依赖注入
    private TenderDao tenderDao;
    @Override
    public List<Map> getPage(Map map) {
        return tenderDao.getPage(map);
    }

    @Override
    public List<Map> getPageByParams(Map map) {
        return tenderDao.getPageByParams(map);
    }

    @Override
    public int add(Map map) {
        return tenderDao.add(map);
    }
}
