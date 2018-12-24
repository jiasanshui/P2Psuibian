package com.aaa.ssm.service.impl;

import com.aaa.ssm.service.ZlRenzhengService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:ZlRenzhengServiceImpl
 * discription:
 * author:xiefuzhi
 * createTime:2018-12-24 14:30
 */
@Service
public class ZlRenzhengServiceImpl implements ZlRenzhengService {
    //依赖注入
    private ZlRenzhengService zlRenzhengService;
    @Override
    public List<Map> getZlList(Integer userid) {
        return zlRenzhengService.getZlList(userid);
    }

    @Override
    public int add(Map map) {
        return zlRenzhengService.add(map);
    }
}
