package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * className:NoticeDao
 * discription:网络公告
 * author:hulu
 * createTime:2018-12-17 09:05
 */
@Component
public interface NoticeDao {
    /**
     * 有参分页查询
     * 使用注解后，要实现动态sql 需要写<script></script>,且><为&gt; &lt;
     * @param map
     * @return
     */
    @Select("<script>select noticeid,title,picture,to_char(addtime,'yyyy-mm-dd')as addtime,content,typeid,id,type from \n" +
        "notice n left join noticetype t on n.typeid=t.id \n" +
        " <where> <if test=\"title!=null and title!=''\"> and title like '%'||#{title}||'%'</if>" +
        "<if test=\"typeid!=null and typeid!=''\"> and typeid=#{typeid}</if> </where></script>")
    List<Map> getPageByParam(Map map);
    /**
     * 获取公告类型的信息
     * @return
     */
    @Select("select id,type from noticetype")
    List<Map> getTypeList();

    @Insert("insert into notice values(#{TITLE},#{PICTURE},#{CONTENT},#{TYPEID},to_date(#{ADDTIME},'yyyy-mm-dd'),seq_notice_id.nextval)")
    int add(Map map);

    @Update("update notice set title=#{TITLE},picture=#{PICTURE},addtime=to_date(#{ADDTIME},'yyyy-mm-dd'),content=#{CONTENT},typeid=#{TYPEID} where noticeid=#{NOTICEID}")
    int update(Map map);

    @Delete("delete from notice where noticeid=#{noticeId}")
    int delete(int noticeId);
}
