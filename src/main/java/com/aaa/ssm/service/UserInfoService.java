package com.aaa.ssm.service;



import java.util.List;
import java.util.Map;

/**
 * className:UserInfoService
 * discription:
 * author:fhm
 * createTime:2018-12-10 16:50
 */
public interface UserInfoService {

    /**
     * 用户列表查询
     * @return
     */
    List<Map> getList(Map map);


    /**
     * 审核通过，更新用户信息表审核状态
     * @param map
     * @return
     */
    int edit(Map map);

    /**
     * 投标成功后改变账户余额
     * @param map
     * @return
     */
    int updateAmount(Map map);


    /**
     * 投标成功后改变冻结金额
     * @param map
     * @return
     */
    int updateFreezAmount(Map map);

    /**
     *审核驳回,向驳回表中插入数据
     * @param map
     * @return
     */
    int addBohui(Map map);

    /**
     * 查询当前ID所有信息
     * @param userId
     * @return
     */
    List<Map> getAllList(Integer userId);
     /** 根据用户名获取用户信息
     * @param username
     * @return
     */
    List<Map> getUserList(String username);
    /**
     * 根据用户ID获取用户信息
     * @param userid
     * @return
     */
    List<Map> getUserListById(Integer userid);


    /** 根据用户名获取用户信息，判断用户是否实名认证
     * @param username
     * @return
     */
    Map getUser(String username);

    /**
     * 查询用户实名信息、账户、借款标的等信息
     * @param userid
     * @return
     */
    List<Map> getAllUserByuserid(Integer userid);

    /**
     * 根据用户ID查询历史
     * @param map
     * @return
     */
    Map getHistory(Map map);

    /**
     * 修改头像
     * @param map
     * @return
     */
    int updateHeadPhoto(Map map);

    /**
     * 判断用户是否上传了头像
     * @param userName
     * @return
     */
    String getHPByUNname(String userName);

    /**
     * 根据userid查询用户的帐户
     * @param userId
     * @return
     */
    Map getUserAccount(Integer userId);

    /**
     * 累计投资金额
     * @param userId
     * @return
     */
    double getTouderMoney(Integer userId);

    /**
     * 查询账户余额
     * @param userName
     * @return
     */
    double getAmountByUName(String userName);

    /**
     * 提现
     * @param map
     */
    Boolean withdraw(Map map);

    /**
     * 充值
     * @param map
     * @return
     */
    Boolean chongzhi(Map map);
}
