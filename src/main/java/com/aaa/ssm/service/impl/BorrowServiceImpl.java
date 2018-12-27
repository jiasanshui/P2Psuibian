package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.BorrowDao;
import com.aaa.ssm.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:BorrowServiceImpl
 * discription:
 * author:hulu
 * createTime:2018-12-11 15:15
 */
@Service
public class BorrowServiceImpl implements BorrowService {
    //依赖注入dao
    @Autowired
    private BorrowDao borrowDao;

    /**
     * 借款人提交数据到后台(信用贷款)
     * @param map
     * @return
     */
    @Override
    public int add(Map map) {
        return borrowDao.add(map);
    }

    /**
     * 借款人提交数据到后台（房屋抵押贷款）
     * @param map
     * @return
     */
    @Override
    public int addOne(Map map) {
        return borrowDao.addOne(map);
    }


    @Override
    public List<Map> getListByusername(String username) {
        return borrowDao.getListByusername(username);
    }

    @Override
    public List<Map> getListByBorrowNum(String BORROWNUM) {
        return borrowDao.getListByBorrowNum(BORROWNUM);
    }

    @Override
    public int update(Map map) {
        return borrowDao.update(map);
    }

    @Override
    public List<Map> getListByUsername(String BORROWNUM) {
        return borrowDao.getListByUsername(BORROWNUM);
    }

    /**
     * 借款页面提示
     * @param userid
     * @return
     */
    @Override
    public Map isBorrow(Integer userid) {
        Map map=new HashMap();
        List<Map> borrowList=borrowDao.isBorrow(userid);
        if(borrowList!=null && borrowList.size()>0){
            return borrowList.get(0);
        }
        //没有提交过借款申请
        map.put("STATEID","0");
        return map;
    }

    /**
     * 重新申请，提交借款材料
     * @param userid
     * @return
     */
    @Override
    public int updatebidstate(Integer userid) {
        return borrowDao.updatebidstate(userid);
    }
}
