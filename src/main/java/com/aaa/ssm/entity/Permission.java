package com.aaa.ssm.entity;/**
 * className:Permission
 * discription:
 * author:jiasanshui
 * createTime:2018-12-06 18:38
 */

import java.util.List;

/**
 *className:Permission
 *discription:
 *author:jiasanshui
 *createTime:2018-12-06 18:38
 */
public class Permission {
    private int id;
    private String text;
    private int parentid;
    private String state;
    private String iconcls;
    private String url;
    private List<Permission> Children;
    //因为要选择树节点
    private String checked;

    public Permission(int id, String text, int parentid, String state, String iconcls, String url) {
        this.id = id;
        this.text = text;
        this.parentid = parentid;
        this.state = state;
        this.iconcls = iconcls;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIconcls() {
        return iconcls;
    }

    public void setIconcls(String iconcls) {
        this.iconcls = iconcls;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public List<Permission> getChildren() {
        return Children;
    }

    public void setChildren(List<Permission> children) {
        Children = children;
    }
}
