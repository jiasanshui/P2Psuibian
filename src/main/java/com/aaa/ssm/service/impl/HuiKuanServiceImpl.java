package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.HuiKuaiDao;
import com.aaa.ssm.service.HuiKuanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:HuiKuanServiceImpl
 * discription:
 * author:xiefuzhi
 * createTime:2019-01-09 23:27
 */
@Service
public class HuiKuanServiceImpl implements HuiKuanService {
    //依赖注入
    @Autowired
    private HuiKuaiDao huiKuaiDao;


    @Override
    public List<Map> getHuiKuaiList(Map map) {
        int pageNo=map.get("pageNo")==null?1:Integer.valueOf(map.get("pageNo")+"");
        int pageSize = map.get("pageSize")==null?7:Integer.valueOf(map.get("pageSize")+"");
        map.put("start",(pageNo-1)*pageSize);
        map.put("end",pageNo*pageSize+1);
        return huiKuaiDao.getHuiKuaiList(map);
    }

    @Override
    public int getPageCount(Map map) {
        List<Map> pageCount = huiKuaiDao.getPageCount(map);
        //判断集合，如果不为空，返回总数量
        if(pageCount!=null&&pageCount.size()>0){
            return Integer.valueOf(pageCount.get(0).get("CNT")+"");
        }
        return 0;
    }
}
