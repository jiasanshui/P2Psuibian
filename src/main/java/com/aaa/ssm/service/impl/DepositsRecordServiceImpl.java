package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.DepositsRecordDao;
import com.aaa.ssm.service.DepositsRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:DepositsRecordServiceImpl
 * discription:
 * author:yb
 * createTime:2019-01-04 17:32
 */
@Service
public class DepositsRecordServiceImpl implements DepositsRecordService {

    @Autowired
    private  DepositsRecordDao depositsRecordDao;
    @Override
    public List<Map> getRecordByDeposits(Integer userid) {
        return depositsRecordDao.getRecordByDeposits(userid);
    }

    /**
     * 获取分页总数量
     * @param map
     * @return
     */
    @Override
    public int getPageCount(Map map) {
        List<Map> pageCount = depositsRecordDao.getPageCount(map);
        //判断集合，如果不为空，返回总数量
        if(pageCount!=null&&pageCount.size()>0){
            return Integer.valueOf(pageCount.get(0).get("CNT")+"");
        }
        return 0;
    }

    /**
     * 投资列表分页
     * @param map
     * @return
     */
    @Override
    public List<Map> getTender(Map map) {
        int pageNo=map.get("pageNo")==null?1:Integer.valueOf(map.get("pageNo")+"");
        int pageSize = map.get("pageSize")==null?10:Integer.valueOf(map.get("pageSize")+"");
        map.put("start",(pageNo-1)*pageSize);
        map.put("end",pageNo*pageSize+1);
        return depositsRecordDao.getTenderPage(map);
    }
}
