package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface ZlRenzhengDao {
    /**
     * 根据用户id查询用户资料
     * @param userid
     * @return
     */
    @Select("select * from zlrenzhneg where usrid=#{userid}")
    List<Map> getZlList(Integer userid);

    /**
     * 添加用户资料认证
     * @param map
     * @return
     */
    @Insert("insert into zlrenzheng(id,userid,path1,path2,path3,path4) values(seq_zlrenzheng_id.nextval,#{userid},#{path1},#{path2},#{path3},#{path4})")
    int add(Map map);
}
