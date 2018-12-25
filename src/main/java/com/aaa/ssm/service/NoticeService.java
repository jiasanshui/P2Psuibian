package com.aaa.ssm.service;

import java.util.List;
import java.util.Map;

/**
 * className:NoticeService
 * discription:
 * author:hulu
 * createTime:2018-12-17 09:05
 */
public interface NoticeService {
    List<Map> getPageByParam(Map map);
    List<Map> getTypeList();
    int add(Map map);
    int update(Map map);
    int delete(int noticeId);
    int batchDelete(String noticeIds);
}
