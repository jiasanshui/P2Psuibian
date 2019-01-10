package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.RepayRecordDao;
import com.aaa.ssm.service.RepayRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:RepayRecordServiceImpl
 * discription:
 * author:yb
 * createTime:2019-01-09 22:02
 */
@Service
public class RepayRecordServiceImpl implements RepayRecordService {
//依赖注入
    @Autowired
    private RepayRecordDao repayRecordDao;
    @Override
    public List<Map> getRecordByRepay(String username) {
        return repayRecordDao.getRecordByRepay(username);
    }

    /**
     * 获取分页总数量
     * @param map
     * @return
     */
    @Override
    public int getPageCount(Map map) {
        List<Map> pageCount = repayRecordDao.getPageCount(map);
        //判断集合，如果不为空，返回总数量
        if(pageCount!=null&&pageCount.size()>0){
            return Integer.valueOf(pageCount.get(0).get("CNT")+"");
        }
        return 0;
    }

    /**
     * 还款列表分页
     * @param map
     * @return
     */
   @Override
    public List<Map> getRepayPage(Map map) {
        int pageNo=map.get("pageNo")==null?1:Integer.valueOf(map.get("pageNo")+"");
        int pageSize = map.get("pageSize")==null?10:Integer.valueOf(map.get("pageSize")+"");
        map.put("start",(pageNo-1)*pageSize);
        map.put("end",pageNo*pageSize+1);
        return repayRecordDao.getRepayPage(map);
    }
}
