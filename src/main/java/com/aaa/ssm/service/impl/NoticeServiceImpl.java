package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.NoticeDao;
import com.aaa.ssm.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:NoticeServiceImpl
 * discription:
 * author:hulu
 * createTime:2018-12-17 09:06
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeDao noticeDao;
    @Override
    public List<Map> getPageByParam(Map map) {
        return noticeDao.getPageByParam(map);
    }

    @Override
    public List<Map> getTypeList() {
        return noticeDao.getTypeList();
    }

    @Override
    public int add(Map map) {
        return noticeDao.add(map);
    }

    @Override
    public int update(Map map) {
        return noticeDao.update(map);
    }

    @Override
    public int delete(int noticeId) {
        return noticeDao.delete(noticeId);
    }
    @Override
    public int batchDelete(String noticeIds) {
        String[] idsArray = noticeIds.split(",");//1,2,3,4, [1,2,3,4]
        boolean isDel =true;
        for (String s : idsArray) {
            if("13".equals(s)){
                System.out.println(1/0);
            }
            int i = noticeDao.delete(Integer.valueOf(s));
            if(i<1){
                isDel=false;
            }
        }
        if(isDel)
            return 1;
        else
            return 0;
    }

}
