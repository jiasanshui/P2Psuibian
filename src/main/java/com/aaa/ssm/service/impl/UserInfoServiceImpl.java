package com.aaa.ssm.service.impl;

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

    @Override
    public List<Map> getList(Map map) {

        return userInfoDao.getList(map);
    }

    @Override
    public int update(Integer userId) {
        return userInfoDao.update(userId);
    }

    @Override
    public int edit(Map map) {
        return userInfoDao.edit(map);
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
     * 是否有正在借款记录（未还清的借款）
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
                    List<Map> borrowList = borrowDao.getListByusername(username);
                    if(borrowList!=null&&borrowList.size()>0){
                        Integer borrowStateid = Integer.valueOf(borrowList.get(0).get("STATEID")+"");
                        if(borrowStateid==10){//还款结束，可以再借款申请
                            return borrowList.get(0);
                        }
                        //map.put("msg","您上次的申请尚在进行中");
                        map.put("msg","1");
                    }
                    //无借款记录
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
}
