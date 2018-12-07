package com.aaa.ssm.entity;/**
 * className:TreeNode
 * discription:
 * author:jiasanshui
 * createTime:2018-12-04 19:29
 */

import java.util.Date;
import java.util.List;

/**
 *className:TreeNode
 *discription:
 *author:jiasanshui
 *createTime:2018-12-04 19:29
 */
public class TreeNode {
    private int id;
    private String text;
    private int pid;
    private String state;
    private String iconcls;
    private String url;
    private Date addTime;
    private List<TreeNode> children;

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /*public TreeNode(Integer id, String name, Integer integer, String state, String iconcls, String url) {
    }
*/
    public TreeNode(int id, String name, int pid, String state, String iconcls, String url) {
        this.id = id;
        this.text = name;
        this.pid = pid;
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

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
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

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}
