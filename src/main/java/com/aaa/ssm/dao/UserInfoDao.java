package com.aaa.ssm.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * className:UserInfoDao
 * discription:
 * author:fhm
 * createTime:2018-12-10 16:31
 */
@Component
public interface UserInfoDao {
    /**
     * 个人信息认证审核列表
     * @param map
     * @return
     * 如果使用注解的方式，动态sql必须在标签<script></script>
     * 如果使用<script></script>标签，mybatis 大于小于，必须使用&gt; &lt;
     */
    @Select("<script>select userid,uname,realname,age,to_char(birthday,'yyyy-mm-dd') birthday,phone,email,address," +
            "idcard,icpica,icpicb,education,working,income,wageflow,creditreport from userinfo where stateid=1 \n "+
            "<if test=\"uname!=null and uname!=''\">  and uname like '%'||#{uname}||'%'</if>" +
            "<if test=\"phone!=null and phone!=''\">  and phone =#{phone}</if>" +
            "</script>")
    List<Map> getList(Map map);

    /**
     * 个人信息认证审核通过,更新用户信息表审核状态
     * @param map
     * @return
     */
    @Update("update userinfo set stateid=2 where userid=#{USERID}")
    int update(Map map);

    /**
     * 个人信息认证审核通过,更新用户信息表审核状态
     * @param map
     * @return
     */
    @Update("update userinfo set stateid=3 where userid=#{USERID}")
    int edit(Map map);

    /**
     * 投标成功后改变账户余额
     * @param map
     * @return
     */
    @Update("update userinfo set amount = amount - #{tamount} where userid = #{userid}")
    int updateAmount(Map map);

    /**
     * 投标成功后改变冻结金额
     * @param map
     * @return
     */
    @Update("update userinfo set freezamount = freezamount + #{tamount} where userid = #{userid}")
    int updateFreezAmount(Map map);

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

    /**
     * 查询用户实名信息、账户、借款标的等信息
     * @param userid
     * @return
     */
    @Select("select userinfo.*,borrow.borrowid,borrow.applicant,borrow.tel," +
            "borrow.timelimit,borrow.purpose,borrow.des,borrow.apr,borrow.borrowmoney,borrow.winbidmoney," +
            "borrow.danbaostyle,borrow.payment,borrow.stateid,to_char(borrow.starttime,'yyyy-mm-dd') starttime,borrow.days," +
            "to_char(borrow.endtime,'yyyy-mm-dd') endtime,borrow.bidapplydate,borrow.userid,bidstate.name,aduitbid.*,emp.ename from userinfo " +
            "left join borrow on userinfo.userid=borrow.userid " +
            "left join bidstate on bidstate.id=borrow.stateid " +
            "left join aduitbid on aduitbid.borrownum=borrow.borrownum " +
            "left join emp on aduitbid.operatorid=emp.id where userinfo.userid=#{userid} and borrow.stateid=6")
    List<Map> getAllUserByuserid(Integer userid);

    /**
     * 更新用户账户(房屋贷、车辆贷)
     * 借款人账户余额：+借款金额；待还金额：本息和
     * @return
     */
    @Update("update userinfo set amount=amount + #{BORROWMONEY},replaceamount= #{total} where userid= #{USERID}")
    int updateAccount(Map map);

    /**
     * 更新用户账户（信用贷）
     * 借款人账户余额：+借款金额；待还金额：本息和; 剩余信用额度=原剩余信用额度-招标金额
     * @return
     */
    @Update("update userinfo set amount = amount + #{BORROWMONEY},replaceamount= #{total},screditedu= creditedu - #{BORROWMONEY} where userid=#{USERID}")
    int updateAccountOne(Map map);

    /**
     * 插入账户流水列表(借款人)
     * @param map
     * @return
     */
    @Insert("insert into account_flow(id,userid,amount,flowdate,flowtypeid,changeamount) " +
            "values(seq_account_flow_id.nextval,#{USERID},#{amount}+#{BORROWMONEY},sysdate,1,#{BORROWMONEY})")
    int addAccountFlow(Map map);

    /**
     * 更新用户账户
     * 投资人：应收总利息=原利息+现利息；现冻结金额：冻结金额-投标的金额；应收金额=原应收金额+投标金额
     * @param tamount
     * @param collectlixi
     * @param tUserid
     * @return  FREEZAMOUNT  COLLECTPRINCIPAL(应收金额)  COLLECTINTEREST(应收总利息)
     */
    @Update("update userinfo set freezamount=freezamount - #{tamount},collectprincipal=collectprincipal + #{tamount}," +
            "collectinterest=collectinterest + #{collectlixi} where userid=#{tUserid}")
    int updateTrenderAccount(@Param("tamount") double tamount,@Param("collectlixi") double collectlixi,@Param("tUserid") Integer tUserid);

    /**
     * 插入账户流水列表（投资人）
     * @param map
     * @return
     */
    @Insert("insert into account_flow(id,userid,amount,flowdate,flowtypeid,changeamount) " +
            "values(seq_account_flow_id.nextval,#{USERID},#{taccount},sysdate,2,#{tamount})")
    int addTaccountFlow(Map map);

    /**
     * 根据用户ID查询历史
     * @param map
     * @return
     */
    @Select("select * from blacklist where userid=#{USERID}")
    List<Map> getHistory(Map map);

    /**
     * 修改头像
     * @param map
     * @return
     */
    @Update("update userinfo set headphoto=#{headphoto} where uname=#{username}")
    int updateHeadPhoto(Map map);

    /**
     * 判断用户是否上传了头像
     * @param userNmae
     * @return
     */
    @Select("select headphoto from userinfo where uname=#{userName}")
    String getHPByUNname(String userNmae);

    /**
     * 根据userId查找用户账户
     * @param
     * @return
     */
    @Select("select * from userinfo where userid=#{userId}")
    List<Map> getUserAccount(Integer userId);

    /**
     * 累计投资金额
     * @param userId
     * @return
     */
    @Select("select sum(tamount) toudermoney from tender where userid=#{userId}")
    List<Map> getTouderMoney(Integer userId);

    /**
     * 查询账户余额
     * @param userName
     * @return
     */
    @Select("select amount from userinfo where uname=#{userName}")
    double getAmountByUName(String userName);

    /**
     * 提现
     * @param map
     * @return
     */
    @Update("update userinfo set amount=amount-#{actualMoney} where uname=#{userName}")
    int withdraw(Map map);

    /**
     * 充值修改账户余额
     * @param map
     * @return
     */
    @Update("update userinfo set amount=#{amount} where userid=#{userId}")
    int chongzhi(Map map);

}
