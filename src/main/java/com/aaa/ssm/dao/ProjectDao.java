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
    @Select("select borrowid,applicant,tel,timelimit,purpose,des,quantity,cost,CONCAT(TO_CHAR(apr*100,'990.99'),'%') apr,borrowmoney,danbaostyle,payment,username,stateid,borrownum,winbidmoney,concat((round(winbidmoney/borrowmoney,4)*100),'%') jindu,round(winbidmoney/borrowmoney*10) jindua  from borrow where rownum<3")
    List<Map> getHousePro();

    /**
     * 查询所有标的
     * @return
     */
    @Select("select borrowid,applicant,tel,timelimit,purpose,des,quantity,cost,CONCAT(TO_CHAR(apr*100,'990.99'),'%') apr,borrowmoney,danbaostyle,payment,username,stateid,borrownum,winbidmoney,concat((round(winbidmoney/borrowmoney,4)*100),'%') jindu,round(winbidmoney/borrowmoney*10) jindua  from borrow")
    List<Map> getHousePro1();

    /**
     *查询全部项目
     * @return
     */
    @Select("<script>select * from (select rownum rn,borrowid,applicant,tel,timelimit,purpose,des,quantity,cost," +
            "CONCAT(TO_CHAR(apr*100,'990.99'),'%') apr,borrowmoney,danbaostyle,payment,username," +
            "stateid,borrownum,winbidmoney,concat((round(winbidmoney/borrowmoney,4)*100),'%') jindu," +
            "round(winbidmoney/borrowmoney*10) jindua from borrow where rownum &lt; #{end} " +
            "<if test=\"pt!=null and pt!=''\"> and danbaostyle=#{danbao}</if>" +
            "<if test=\"bi!=null and bi!=''\"> and apr &gt;= #{startapr} and apr &lt;= #{endapr}</if>" +
            "<if test=\"sm!=null and sm!=''\"> and timelimit &gt;= #{startlimit} and timelimit &lt;= #{endlimit}</if>" +
            "<if test=\"rs!=null and rs!=''\"> and payment=#{repayment}</if>" +
            ") a where a.rn &gt; #{start} </script>")
    List<Map> getHouseProAll(Map map);

    /**
     * 根据编号查询招标进度
     * @param borrowNum
     * @return
     */
    @Select("select borrowid,applicant,tel,timelimit,purpose,des,quantity,cost," +
            "CONCAT(TO_CHAR(apr*100,'990.99'),'%') apr,borrowmoney,danbaostyle,payment,username,stateid," +
            "borrownum,winbidmoney from borrow where rownum<3")
    List<Map> getJinduByNum(String borrowNum);

    /**
     * 获取分页总数量
     * @param map
     * @return
     */
    @Select("<script> select count(*) cnt from borrow where 1=1 " +
            "<if test=\"pt!=null and pt!=''\"> and danbaostyle=#{danbao}</if>" +
            "<if test=\"bi!=null and bi!=''\"> and apr &gt;= #{startapr} and apr &lt;= #{endapr}</if>" +
            "<if test=\"sm!=null and sm!=''\"> and timelimit &gt;= #{startlimit} and timelimit &lt;= #{endlimit}</if>" +
            "<if test=\"rs!=null and rs!=''\"> and payment=#{repayment}</if> " +
            "</script>")
    int getPageCount(Map map);
}
