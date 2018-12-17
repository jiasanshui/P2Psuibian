package com.aaa.ssm.entity;

/**
 * className:Admin
 * discription:后台管理员登录实体类
 * author:fhm
 * createTime:2018-12-16 22:54
 */
public class Admin {
    private Integer id;
    private String aname;
    private String apassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getApassword() {
        return apassword;
    }

    public void setApassword(String apassword) {
        this.apassword = apassword;
    }
}
