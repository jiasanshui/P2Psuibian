package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * className:ProjectDao
 * discription:
 * author:jiasanshui
 * createTime:2018-12-12 16:39
 */
@Component
public interface ProjectDao {

    /**
     * 查询房屋抵押项目
     * @return
     */
    @Select("select borrowid,applicant,tel,timelimit,purpose,des,quantity,cost,CONCAT(TO_CHAR(apr*100,'990.99'),'%') apr,borrowmoney,danbaostyle,payment,userid,stateid from borrow where rownum<3")
    List<Map> getHousePro();
}