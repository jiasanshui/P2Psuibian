package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.PageDao;
import com.aaa.ssm.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:PageServiceImpl
 * discription:
 * author:hulu
 * createTime:2018-12-21 14:31
 */
@Service
public class PageServiceImpl implements PageService{
    @Autowired
    private PageDao pageDao;
    @Override
    public List<Map<String, Object>> getPage(Map map) {
        return null;
    }

    @Override
    public Integer getPageCount(Map map) {
        return null;
    }
}
