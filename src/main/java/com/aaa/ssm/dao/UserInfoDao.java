package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;
import java.util.Map;

/**
 * className:UserInfoDao
 * discription:
 * author:fhm
 * createTime:2018-12-10 16:31
 */
public interface UserInfoDao {
    /**
     * 用户列表查询
     * @param map
     * @return
     * 如果使用注解的方式，动态sql必须在标签<script></script>
     * 如果使用<script></script>标签，mybatis 大于小于，必须使用&gt; &lt;
     */
    @Select("<script>select userid,uname,realname,age,to_char(birthday,'yyyy-mm-dd') birthday,phone,email,address,idcard,icpica,icpicb,remark from userinfo where stateid=1 \n "+
            "<if test=\"uname!=null and uname!=''\">  and uname like '%'||#{uname}||'%'</if>" +
            "<if test=\"phone!=null and phone!=''\">  and phone =#{phone}</if>" +
            "</script>")
    List<Map> getList(Map map);

    /**
     * 审核通过，更新用户信息表审核状态
     * @param userId
     * @return
     */
    @Update("update userinfo set stateid=2 where userid=#{userId}")
    int update(Integer userId);

    /**
     * 审核驳回，更新用户信息表审核状态
     * @param map
     * @return
     */
    @Update("update userinfo set stateid=3 where userid=#{USERID}")
    int edit(Map map);

    /**
     * 审核驳回,向驳回表中插入数据
     * @param map
     * @return
     */
    @Insert("insert into bohui(bid,reason,opraterid) values(seq_bohui_bid.nextval,#{REASON},#{OPRATERID})")
    int addBohui(Map map);

    /**
     * 查询当前ID所有信息(投标审核页面用)
     * @param userId
     * @return
     */
    @Select("<script>select userid,uname,realname,age,to_char(birthday,'yyyy-mm-dd') birthday, " +
            " phone,email,address,idcard,icpica,icpicb,remark from userinfo where userid=#{USERID} "+
            "</script>")
            List<Map> getAllList(Integer userId);

     /** 根据用户名获取用户信息
     * @param username
     * @return
     */
    @Select("select * from userinfo where uname=#{username}")
    List<Map> getUserList(String username);

    /**
     * 根据用户ID获取用户信息
     * @param userid
     * @return
     */
    @Select("select * from userinfo where userid=#{userid}")
    List<Map> getUserListById(Integer userid);



    /** 根据用户名获取用户信息，判断用户是否实名认证
     * @param username
     * @return
     */
    @Select("select * from userinfo where uname=#{username}")
    List<Map> getUser(String username);
}
