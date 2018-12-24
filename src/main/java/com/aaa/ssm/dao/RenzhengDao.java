package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * className:RenzhengDao
 * discription:个人信息认证
 * author:jiasanshui
 * createTime:2018-12-10 21:24
 */
@Component
public interface RenzhengDao {

    @Update("update userinfo set realname=#{realname},age=#{age},birthday=to_date(substr(#{birth},1,10),'yyyy-mm-dd'),paypwd=#{paypwd}," +
            "idcard=#{idcard},icpica=#{picA},icpicb=#{picB},address=#{address},creditedu=50000,screditedu=50000,education=#{education},working=#{working},income=#{income} where uname=#{username}")
    int update(Map map);
}
