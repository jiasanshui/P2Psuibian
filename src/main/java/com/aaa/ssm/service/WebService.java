package com.aaa.ssm.service;

import java.util.List;
import java.util.Map;

/**
 * className:WebService
 * discription:
 * author:hulu
 * createTime:2018-12-18 11:18
 */
public interface WebService {
    List<Map> getWebList();
    List<Map> getMediaList();
    List<Map> getCompanyList( );
    List<Map> getTeamList( );
    List<Map> getPartnerList( );
    List<Map> getTeamSList( );
    List<Map> getList(Integer noticeid);

    /**
     * 获取网站公告分页总数量
     * @param map
     * @return
     */
    int getPageCount(Map map);
    /**
     * 获取媒体报道分页总数量
     * @param map
     * @return
     */
    int getPageCountM(Map map);
}
