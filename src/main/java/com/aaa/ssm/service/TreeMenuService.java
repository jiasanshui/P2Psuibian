package com.aaa.ssm.service;

import com.aaa.ssm.entity.TreeNode;

import java.util.List;

/**
 * className:TreeMenuService
 * discription:
 * author:jiasanshui
 * createTime:2018-12-04 19:24
 */

public interface TreeMenuService {
    /**
     * 获取树的json数据
     * @return
     */
    List<TreeNode> getList();

}
