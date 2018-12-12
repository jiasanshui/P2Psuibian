package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * className:UserMaterialsDao
 * discription:
 * author:yb
 * createTime:2018-12-11 15:38
 */
public interface UserMaterialsDao {
    //    @Select("<script>select * from borrowing"+
//    "<where><if test=\"borrowId!=null and borrowId!=''\" and borrowId=#{borrowId}</if>"+
//    "<if test=\"applicant!=null and applicant!=''\"and applicant=#{applicant}</if"+
//    "")
    /**
     * 添加用户借款
     */
    //@Insert("insert into borrow values(seq_tbborrowid.nextval,#{APPLICANT},#{TEL},#{TIMELIMIT},#{PURPOSE},#{DES},#{CONDITIONID},#{PAYMENTID},#{QUANTITY},#{COST},#{APR},#{BORROWMONEY})")
    int addUserMaterials(Map map);
}
