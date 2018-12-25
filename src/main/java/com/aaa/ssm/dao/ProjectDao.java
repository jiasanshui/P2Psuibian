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
    @Select("select borrowid,applicant,tel,timelimit,purpose,des,quantity,cost,CONCAT(TO_CHAR(apr*100,'990.99'),'%') apr,borrowmoney,danbaostyle,payment,username,stateid,borrownum,winbidmoney,concat((trunc(winbidmoney/borrowmoney,4)*100),'%') jindu,round(winbidmoney/borrowmoney*10) jindua  from borrow where rownum<3  and timelimit<=1")
    List<Map> getHousePro();

    /**
     * 查询所有标的
     * @return
     */
    @Select("select borrowid,applicant,tel,timelimit,purpose,des,quantity,cost,CONCAT(TO_CHAR(apr*100,'990.99'),'%') apr,borrowmoney,danbaostyle,payment,username,stateid,borrownum,winbidmoney,concat((trunc(winbidmoney/borrowmoney,4)*100),'%') jindu,round(winbidmoney/borrowmoney*10) jindua  from borrow")
    List<Map> getHousePro1();

    /**
     *查询全部项目
     * @return
     */
    @Select("select borrowid,applicant,tel,timelimit,purpose,des,quantity,cost,CONCAT(TO_CHAR(apr*100,'990.99'),'%') apr,borrowmoney,danbaostyle,payment,username,stateid,borrownum,winbidmoney,concat((trunc(winbidmoney/borrowmoney,4)*100),'%') jindu,round(winbidmoney/borrowmoney*10) jindua,(winbidmoney/borrowmoney)as baifen  from borrow")
    List<Map> getHouseProAll();

    /**
     * 根据编号查询招标进度
     * @param borrowNum
     * @return
     */
    @Select("select borrowid,applicant,tel,timelimit,purpose,des,quantity,cost," +
            "CONCAT(TO_CHAR(apr*100,'990.99'),'%') apr,borrowmoney,danbaostyle,payment,username,stateid," +
            "borrownum,winbidmoney from borrow where rownum<3")
    List<Map> getJinduByNum(String borrowNum);
    /*---------------------------------------------*/

    /**
     * 查询抵押项目(房屋、车辆、信用)
     * @return
     */
    @Select("select borrowid,applicant,tel,timelimit,purpose,des,quantity,cost,CONCAT(TO_CHAR(apr*100,'990.99'),'%') apr,borrowmoney,danbaostyle,payment,username,stateid,borrownum,winbidmoney,concat(TO_CHAR((winbidmoney/borrowmoney*100),'990.99'),'%') jindu,round(winbidmoney/borrowmoney*10) jindua  from borrow where rownum<3 and DANBAOSTYLE like '%'||#{parama}||'%'")
    List<Map> getList(Map map);

    /**
     * 根据借款编号查询信息（进infor页面）
     * @param borrownum
     * @return
     */
    @Select("select borrowid,applicant,tel,timelimit,purpose,des,quantity,cost,CONCAT(TO_CHAR(apr*100,'990.99'),'%') apr,borrowmoney,danbaostyle,payment,username,stateid,borrownum,winbidmoney,concat(TO_CHAR((winbidmoney/borrowmoney*100),'990.99'),'%') jindu,round(winbidmoney/borrowmoney*10) jindua  from borrow where borrownum=#{borrownum}")
    List<Map> getBorrowList(String borrownum);
}
