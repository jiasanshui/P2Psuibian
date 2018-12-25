package com.aaa.ssm.controller;/**
 * className:TreeMenuController
 * discription:
 * author:jiasanshui
 * createTime:2018-12-04 20:19
 */

import com.aaa.ssm.entity.TreeNode;
import com.aaa.ssm.service.TreeMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *className:TreeMenuController
 *discription:
 *author:jiasanshui
 *createTime:2018-12-04 20:19
 */
@RestController
@RequestMapping("/tree")
public class TreeMenuController {

    @Autowired
    private TreeMenuService treeMenuService;

    /**
     * 树列表
     * @return
     */
    @RequestMapping("/list")
    public Object getTreeList(){
        List<TreeNode> treeList = treeMenuService.getList();
        return treeList;
    }

}
