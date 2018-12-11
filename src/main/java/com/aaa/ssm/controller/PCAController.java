package com.aaa.ssm.controller;/**
 * className:PCAController
 * discription:
 * author:jiasanshui
 * createTime:2018-12-08 15:36
 */

import com.aaa.ssm.service.PCAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *className:PCAController
 *discription:省市县级联
 *author:jiasanshui
 *createTime:2018-12-08 15:36
 */
@RestController
@RequestMapping("pca")
public class PCAController {

    @Autowired
    private PCAService pcaService;

    /**
     *  查询省
     * @return
     */
    @RequestMapping("province")
    public Object getProvince(){
        return pcaService.getProvince();
    }

    /**
     * 根据省查询城市
     * @param pname
     * @return
     */
    @RequestMapping("city")
    public Object getCity(String pname){
        return pcaService.getCity(pname);
    }

    /**
     * 根据城市查询县区
     * @param cname
     * @return
     */
    @RequestMapping("area")
    public Object getArea(String cname){
        return pcaService.getArea(cname);
    }
}
