package com.aaa.ssm.service.impl;
/**
 * className:TreeMenuServiceImpl
 * discription:
 * author:jiasanshui
 * createTime:2018-12-04 19:24
 */

import com.aaa.ssm.dao.TreeMenuDao;
import com.aaa.ssm.entity.TreeNode;
import com.aaa.ssm.service.TreeMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *className:TreeMenuServiceImpl
 *discription:
 *author:jiasanshui
 *createTime:2018-12-04 19:24
 */
@Service
public class TreeMenuServiceImpl implements TreeMenuService {

    @Autowired
    private TreeMenuDao treeMenuDao;


    @Override
    public List<TreeNode> getList() {
        List<TreeNode> list = treeMenuDao.getList();
        //临时集合，用于返回数据
        List<TreeNode> tempList = new ArrayList<TreeNode>();
        if (list!=null&&list.size()>0){
            for (TreeNode node : list) {
                if(node.getParentid()==0){
                    tempList.add(node);
                    //递归方法，查询子节点
                    bindChildren(node,list);
                }
            }
        }
        return tempList;
    }


    /**
     * 递归查询子节点
     * @param pNode
     * @param list
     */
    private void bindChildren(TreeNode pNode,List<TreeNode> list){
        for (TreeNode node : list) {
            //如果当前节点的id和循环节点的父id相等，说明是当前节点的孩子
            if(pNode.getId()==node.getParentid()){
                List<TreeNode> children = pNode.getChildren();
                //如果没有孩子，第一次取出是空的
                if(children==null){
                    children = new ArrayList<TreeNode>();
                }
                //添加孩子
                children.add(node);
                //设置当前孩子集合
                pNode.setChildren(children);
                //自己调用自己
                bindChildren(node,list);
            }
        }
    }
}
