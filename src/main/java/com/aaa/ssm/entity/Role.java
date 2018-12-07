package com.aaa.ssm.entity;/**
 * className:Role
 * discription:
 * author:jiasanshui
 * createTime:2018-12-06 18:37
 */

import java.util.List;

/**
 *className:Role
 *discription:
 *author:jiasanshui
 *createTime:2018-12-06 18:37
 */
public class Role {
    private Integer rid;
    private String rname;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    private List<Permission> permissions;
}
