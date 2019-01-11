package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.HuiKuaiDao;
import com.aaa.ssm.service.HuiKuanService;
import com.aaa.ssm.util.StringUtil;
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
        List<Map> huiKuanList =null;
        if(StringUtil.isEmpty(map.get("selecttime"))){
            huiKuanList =  huiKuaiDao.getHuiKuaiList(map);
        }else if (Integer.valueOf(map.get("selecttime")+"")==0){//今天
            map.put("selecttoday",2);
            huiKuanList =  huiKuaiDao.getHuiKuaiList(map);
        }else if (Integer.valueOf(map.get("selecttime")+"")==1){//近一个月
            map.put("selectmonth",1);
            huiKuanList =  huiKuaiDao.getHuiKuaiList(map);
        }else if (Integer.valueOf(map.get("selecttime")+"")==6){//近6个月
            map.put("selectsix",6);
            huiKuanList =  huiKuaiDao.getHuiKuaiList(map);
        }else {//最近一周
            map.put("selectseven",7);
            huiKuanList =  huiKuaiDao.getHuiKuaiList(map);
        }
        return huiKuanList;
    }

    @Override
    public int getPageCount(Map map) {
        List<Map> pageCount=null;
        if(StringUtil.isEmpty(map.get("selecttime"))){
            pageCount = huiKuaiDao.getPageCount(map);
        }else if (Integer.valueOf(map.get("selecttime")+"")==0){//今天
            map.put("selecttoday",2);
            pageCount = huiKuaiDao.getPageCount(map);
        }else if (Integer.valueOf(map.get("selecttime")+"")==1){//近一个月
            map.put("selectmonth",1);
            pageCount = huiKuaiDao.getPageCount(map);
        }else {//近6个月
            map.put("selectsix",6);
            pageCount = huiKuaiDao.getPageCount(map);
        }
        //判断集合，如果不为空，返回总数量
        if(pageCount!=null&&pageCount.size()>0){
            return Integer.valueOf(pageCount.get(0).get("CNT")+"");
        }
        return 0;
    }
}
