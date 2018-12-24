package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    @Insert("insert into borrow(borrowid,applicant,tel,timelimit,purpose,des,quantity,cost,apr,borrowmoney,danbaostyle,payment,username,stateid,days,borrownum,winbidmoney) " +
            "values(seq_tbborrowid.nextval,#{applicant},#{tel},#{timelimit},#{purpose},#{des},#{quantity},#{cost}," +
            "#{apr},#{borrowmoney},#{danbaostyle},#{payment},#{username},1,#{days},#{borrownum},0)")
    int add(Map map);

    /**
     * 根据借款标的编号查询投标中的标的
     * 根据借款标的编号查询投标中的标的
     * @param BORROWNUM
     * @return
     */
    @Select("select borrowid,applicant,starttime,tel,timelimit,purpose,des,quantity,cost,CONCAT(TO_CHAR(apr*100,'990.99'),'%') apr,borrowmoney,danbaostyle,payment,username,stateid,borrownum,winbidmoney,concat((trunc(winbidmoney/borrowmoney,4)*100),'%') jindu,round(winbidmoney/borrowmoney*10) jindua,(winbidmoney/borrowmoney) as baifen  from borrow where borrownum=#{BORROWNUM}")
    List<Map> getListByUsername(String BORROWNUM);

    /**
     * 投标成功后修改借款表里已借金额
     * @param map
     * @return
     */
    @Update("update borrow set winbidmoney = winbidmoney+#{tamount} where borrownum = #{borrowNum}")
    int update(Map map);
}
