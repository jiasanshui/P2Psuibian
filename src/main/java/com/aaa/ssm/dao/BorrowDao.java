package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:BorrowDao
 * discription:
 * author:hulu
 * createTime:2018-12-11 15:05
 */
public interface BorrowDao {

    /**
     * 借款人提交数据到后台
     * @param map
     * @return
     */
    @Insert("insert into borrow(borrowid,applicant,tel,timelimit,purpose,des,quantity,cost,apr,borrowmoney,danbaostyle,payment,userid,stateid,days,borrownum) " +
            "values(seq_tbborrowid.nextval,#{applicant},#{tel},#{timelimit},#{purpose},#{des},#{quantity},#{cost}," +
            "#{apr},#{borrowmoney},#{danbaostyle},#{payment},#{userid},1,#{days},#{borrownum})")
    int add(Map map);
}
