package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * className:BorrowDao
 * discription:
 * author:hulu
 * createTime:2018-12-11 15:05
 */
@Component
public interface BorrowDao {

    /**
     * 借款人提交数据到后台（信用贷款）
     * @param map
     * @return
     */
    @Insert("insert into borrow(borrowid,applicant,tel,timelimit,purpose,des,apr,tapr,borrowmoney,danbaostyle,payment,username,stateid,days,borrownum,userid,bidapplydate,winbidmoney,yettime) " +
            "values(seq_tbborrowid.nextval,#{applicant},#{tel},#{timelimit},#{purpose},#{des}," +
            "#{apr},#{apr}-0.03,#{borrowmoney},#{danbaostyle},#{payment},#{username},1,#{days},#{borrownum},#{userid},sysdate,0,0)")
    int add(Map map);

    /**
     * 借款人提交数据到后台（房屋车辆抵押贷款）
     * @param map
     * @return
     */
    @Insert("insert into borrow(borrowid,applicant,tel,timelimit,purpose,des,apr,tapr,borrowmoney,danbaostyle,payment,username,stateid,days,borrownum,userid,bidapplydate,winbidmoney,cost,documentpic,physicapic,yettime) " +
            "values(seq_tbborrowid.nextval,#{applicant},#{tel},#{timelimit},#{purpose},#{des}," +
            "#{apr},#{apr}-0.03,#{borrowmoney},#{danbaostyle},#{payment},#{username},1,#{days},#{borrownum},#{userid},sysdate,0,#{cost},#{documentpic},#{physicapic},0)")
    int addOne(Map map);


    /**
     * 根据借款标的编号查询投标中的标的
     * @param userName
     * @return
     */
    @Select("select borrowid,applicant,tel,timelimit,purpose,starttime,des,quantity,cost," +
            "CONCAT(TO_CHAR(tapr*100,'990.99'),'%') tapr,borrowmoney,danbaostyle,payment," +
            "username,stateid,borrownum,winbidmoney,concat((winbidmoney/borrowmoney*100),'%') jindu," +
            "round(winbidmoney/borrowmoney*10) jindua  from borrow where username=#{userName} and stateid=2")
    List<Map> getListByUsername(String userName);

    /**
     * 通过用户名查询该用户是否有正在借款记录（未还清的借款）
     * @param userName
     * @return
     */
    @Select("select * from borrow where username=#{userName}")
    List<Map> getListByusername(String userName);

    /**
     * 根据借款标的编号查询投标
     * @param BORROWNUM
     * @return
     */
    @Select("select borrowid,applicant,starttime,tel,timelimit,purpose,des,quantity,cost,CONCAT(TO_CHAR(tapr*100,'990.99'),'%') tapr,borrowmoney,danbaostyle,payment,username,userid,stateid,borrownum,winbidmoney,concat((trunc(winbidmoney/borrowmoney,4)*100),'%') jindu,round(winbidmoney/borrowmoney*10) jindua,(winbidmoney/borrowmoney) as baifen  from borrow where borrownum=#{BORROWNUM}")
    List<Map> getListByBorrowNum(String BORROWNUM);

    /**
     * 投标成功后修改借款表里已借金额
     * @param map
     * @return
     */
    @Update("update borrow set winbidmoney = winbidmoney+#{tamount} where borrownum = #{borrowNum}")
    int update(Map map);

    /**
     * 借款页面提示
     * @param userid
     * @return
     */
    @Select("select stateid from " +
            "(select starttime,stateid from borrow where userid=#{userid} order by starttime desc) where rownum=1")
    List<Map> isBorrow(Integer userid);

    /**
     * 重新申请，提交借款材料
     * @param userid
     * @return
     */
    @Update("update borrow set stateid=0 where userid=#{userid}")
    int updatebidstate(Integer userid);
}
