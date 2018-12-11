package com.aaa.ssm.entity;/**
 * className:TreeNode
 * discription:
 * author:jiasanshui
 * createTime:2018-12-04 19:29
 */

import java.util.List;

/**
 *className:TreeNode
 *discription:
 *author:jiasanshui
 *createTime:2018-12-04 19:29
 */
public class TreeNode {
    private Integer id;
    private String label;
    private Integer parentid;
    private String iconClass;
    private String url;
    private List<TreeNode> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
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
