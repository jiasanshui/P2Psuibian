package com.aaa.ssm.service.impl;/**
 * className:PCAServiceImpl
 * discription:
 * author:jiasanshui
 * createTime:2018-12-08 15:39
 */

import com.aaa.ssm.dao.PCADao;
import com.aaa.ssm.service.PCAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *className:PCAServiceImpl
 *discription:
 *author:jiasanshui
 *createTime:2018-12-08 15:39
 */
@Service
public class PCAServiceImpl implements PCAService{

    @Autowired
    private PCADao pcaDao;

    @Override
    public List<Map> getProvince() {
        return pcaDao.getProvince();
    }

    @Override
    public List<Map> getCity(String pname) {
        return pcaDao.getCity(pname);
    }

    @Override
    public List<Map> getArea(String cname) {
        return pcaDao.getArea(cname);
    }

    /**
     * 通过pid找对应的市
     * @param pId
     * @return
     */
    @Override
    public List<Map> getCityByProvinceId(String pId) {
        List<Map> cityList=pcaDao.getCityByProvinceId(pId);
        if(cityList!=null&&cityList.size()>0){
            return cityList;
        }
        return null;
    }
    /**
     * 通过cid找对应的区
     * @param cId
     * @return
     */
    @Override
    public List<Map> getAreaByCityId(String cId) {
        List<Map> areaList= pcaDao.getAreaByCityId(cId);
        if(areaList!=null&&areaList.size()>0){
            return  areaList;
        }
        return null;
    }
}
