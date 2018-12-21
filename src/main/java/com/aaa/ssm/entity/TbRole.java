package com.aaa.ssm.entity;

import java.util.Date;

/**
 * className:TbRole
 * discription:角色实体类
 * author:fhm
 * createTime:2018-12-19 08:47
 */
public class TbRole {
    private Integer id;
    private String name;
    private String descp;
    private String state;
    private Date addtime;
    private String powersid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getPowersid() {
        return powersid;
    }

    public void setPowersid(String powersid) {
        this.powersid = powersid;
    }
}
