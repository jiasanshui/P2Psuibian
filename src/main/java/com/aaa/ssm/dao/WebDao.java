package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:WebDao
 * discription:前台页面关于网站的dao
 * author:hulu
 * createTime:2018-12-18 11:17
 */
public interface WebDao {
    /**
     * 根据类型查询网站公告
     * @param
     * @return
     */
    @Select("select rownum,noticeid,title,picture,content,typeid,to_char(addtime,'yyyy-mm-dd')as addtime from notice where typeid=1")
    List<Map> getWebList();
    /**
     * 根据类型查询媒体公告
     * @param
     * @return
     */
    @Select("select rownum,noticeid,title,picture,content,typeid,to_char(addtime,'yyyy-mm-dd')as addtime from notice where typeid=2")
    List<Map> getMediaList( );

    /**
     * 根据类型查询公司简介
     * @param
     * @return
     */
    @Select("select noticeid,title,picture,content,typeid,to_char(addtime,'yyyy-mm-dd')as addtime from notice where typeid=3")
    List<Map> getCompanyList( );
    /**
     * 根据类型查询管理团队
     * @param
     * @return
     */
    @Select("select noticeid,title,picture,content,typeid,to_char(addtime,'yyyy-mm-dd')as addtime from notice where typeid=4")
    List<Map> getTeamList( );

    /**
     * 根据类型查询合作团队
     * @param
     * @return
     */
    @Select("select noticeid,title,picture,content,typeid,to_char(addtime,'yyyy-mm-dd')as addtime from notice where typeid=5")
    List<Map> getPartnerList( );
    /**
     * 根据类型查询团队风采
     * @param
     * @return
     */
    @Select("select noticeid,title,picture,content,typeid,to_char(addtime,'yyyy-mm-dd')as addtime from notice where typeid=6")
    List<Map> getTeamSList( );
    /**
     * 根据类型查询理财知识
     * @param
     * @return
     */
    @Select("select noticeid,title,picture,content,typeid,to_char(addtime,'yyyy-mm-dd')as addtime from notice where typeid=7")
    List<Map> getLiCaiList( );

    /**
     * 根据ID查询各条信息
     * @param noticeid
     * @return
     */
    @Select("select noticeid,title,picture,content,typeid,to_char(addtime,'yyyy-mm-dd')as addtime,type from notice left join noticetype on typeid=id where noticeid=#{noticeid}")
    List<Map> getList(Integer noticeid);


    /**
     * 获取网站公告分页总数量
     * @param map
     * @return
     */
    @Select("select count(*) cnt from notice where typeid=1")
    int getPageCount(Map map);
    /**
     * 获取媒体报道分页总数量
     * @param map
     * @return
     */
    @Select("select count(*) cnt from notice where typeid=2")
    int getPageCountM(Map map);
}
