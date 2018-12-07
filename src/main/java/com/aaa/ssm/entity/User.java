package com.aaa.ssm.entity;/**
 * className:User
 * discription:
 * author:jiasanshui
 * createTime:2018-12-06 18:36
 */

/**
 *className:User
 *discription:
 *author:jiasanshui
 *createTime:2018-12-06 18:36
 */
public class User {

    private Integer userId;
    private String username;
    private String password;
    private String perms;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }
}
