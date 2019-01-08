package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.AduitUserinfoDao;
import com.aaa.ssm.dao.BorrowDao;
import com.aaa.ssm.dao.UserInfoDao;
import com.aaa.ssm.service.UserInfoService;
import com.aaa.ssm.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:UserInfoServiceImpl
 * discription:
 * author:fhm
 * createTime:2018-12-10 17:12
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    //依赖注入dao层
    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private BorrowDao borrowDao;

    @Autowired
    private AduitUserinfoDao aduitUserinfoDao;

    @Override
    public List<Map> getList(Map map) {

        return userInfoDao.getList(map);
    }

    /**
     * 个人信息认证审核
     * 1、更新userinfo审核状态
     * 2、往userinfoaduit中插入数据
     * @param map
     * @return
     */
    @Override
    public int edit(Map map) {
        //1、更新userinfo审核状态
        Integer aduitresult = Integer.valueOf(map.get("ADUITRESULT")+"");
        int m=0;
        int n=0;
        if(aduitresult==1){//个人信息认证审核通过
            m=userInfoDao.update(map);
        }else {//个人信息认证审核失败
            n=userInfoDao.edit(map);
        }
        //2、往userinfoaduit中插入数据
        int s = aduitUserinfoDao.add(map);
        if (s!=0 &&( n!=0 || m!=0)){
            return 1;
        }
        return 0;
    }

    @Override
    public int updateFreezAmount(Map map) {
        return userInfoDao.updateFreezAmount(map);
    }

    @Override
    public int updateAmount(Map map) {
        return userInfoDao.updateAmount(map);
    }

    @Override
    public int addBohui(Map map) {
        return userInfoDao.addBohui(map);
    }

    @Override
    public List<Map> getAllList(Integer userId) {
        return userInfoDao.getAllList(userId);
    }

    public List<Map> getUserList(String username) {
        return userInfoDao.getUserList(username);
    }

    /**
     * 根据用户名获取用户信息，
     * 判断用户是否进行实名认证
     * 判断用户是否通过实名认证
     * @param username
     * @return
     */
    @Override
    public Map getUser(String username) {
        List<Map> userList = userInfoDao.getUser(username);
        Map map=new HashMap();
        if (userList!=null&&userList.size()>0){
            Object realname = userList.get(0).get("REALNAME");
            if(StringUtil.isEmpty(realname)){
                //判断真实姓名为空,没有进行实名认证
                map.put("msg","请检查你的实名认证是否通过！！");
                return map;
            }
            try {
                //真实姓名不为空（已提交实名认证材料，在判断审核是否通过）
                Integer stateid = Integer.valueOf(userList.get(0).get("STATEID")+"");
                if(stateid==2){//审核通过
                    return userList.get(0);
                }
                map.put("msg","请检查你的实名认证是否通过！！");
                return map;
            } catch (NumberFormatException e) {
                //实名认证没通过
                //e.printStackTrace();
                map.put("msg","请检查你的实名认证是否通过！！");
                return map;
            }
        }
        return null;
    }

    @Override
    public List<Map> getUserListById(Integer userid) {
        return userInfoDao.getUserListById(userid);
    }

    /**
     * 查询用户实名信息、账户、借款标的等信息
     * @param userid
     * @return
     */
    @Override
    public List<Map> getAllUserByuserid(Integer userid) {
        return userInfoDao.getAllUserByuserid(userid);
    }

    /**
     * 根据用户ID查询历史
     * @param map
     * @return
     */
    @Override
    public Map getHistory(Map map) {
        List<Map> history = userInfoDao.getHistory(map);
        Map map1=new HashMap();
        if(history!=null && history.size()>0){
            return history.get(0);
        }
        map1.put("REMARK","该用户信用良好，无不良记录！");
        return map1;
    }

    /**
     * 修改头像
     * @param map
     * @return
     */
    @Override
    public int updateHeadPhoto(Map map) {
        return userInfoDao.updateHeadPhoto(map);
    }

    /**
     * 判断用户是否上传了头像
     * @param userName
     * @return
     */
    @Override
    public String getHPByUNname(String userName) {
        return userInfoDao.getHPByUNname(userName);
    }

    /**
     * 根据userid查询用户的帐户
     * @param userId
     * @return
     */
    @Override
    public Map getUserAccount(Integer userId) {
        List<Map> userAccount=userInfoDao.getUserAccount(userId);
        if(userAccount!=null &&  userAccount.size()>0){
            return userAccount.get(0);
        }
        return null;
    }

    /**
     * 累计投资金额
     * @param userId
     * @return
     */
    @Override
    public double getTouderMoney(Integer userId) {
        List<Map> touderMoney = userInfoDao.getTouderMoney(userId);
        if (touderMoney!=null&&touderMoney.size()>0){
            return Double.parseDouble(touderMoney.get(0).get("TOUDERMONEY")+"");
        }
        return 0;
    }

    /**
     * 查询账户余额
     * @param userName
     * @return
     */
    @Override
    public double getAmountByUName(String userName) {
        return userInfoDao.getAmountByUName(userName);
    }
}
