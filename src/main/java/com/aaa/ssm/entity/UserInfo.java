package com.aaa.ssm.entity;

import java.util.Date;

/**
 * className:UserInfo
 * discription:
 * author:xiefuzhi
 * createTime:2018-12-07 21:43
 */

public class UserInfo {
    private Integer userId;
    private String uname;
    private String upwd;
    private String phone;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
