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
import java.util.Map;

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

    /**
     * 查询树的节点
     * @return
     */
    public List<TreeNode> getList(){
        List<Map> powerAllList = treeMenuDao.getList();
        System.out.println("========"+powerAllList);
        List<TreeNode> treeNodeList = new ArrayList();
        if(powerAllList!=null&&powerAllList.size()>0){
            TreeNode treeNode = null;
            for (Map map : powerAllList) {
                treeNode = new TreeNode(Integer.valueOf(map.get("ID")+""),map.get("NAME")+"",Integer.valueOf(map.get("PID")+""),
                        map.get("STATE")+"",map.get("ICONCLS")+"",map.get("URL")+"");
                treeNodeList.add(treeNode);
            }
        }
        return treeNodeList;
    }

    /**
     * 查询树列表
     */
    public List<TreeNode> getTreeList() {
        List<TreeNode> powerTreeList = getList();
        List<TreeNode> powerTempList = new ArrayList<TreeNode>();
        if(powerTreeList!=null&&powerTreeList.size()>0){
            for (TreeNode ptreeNode : powerTreeList) {
                if(ptreeNode.getPid()==0){ //如果父节点为0，说明是一级节点
                    System.out.println("--------------"+ptreeNode.getId());
                    System.out.println("--------------+++"+ptreeNode.getPid());
                    powerTempList.add(ptreeNode);
                    bindChildren(ptreeNode,powerTreeList);
                }
            }
        }
        return powerTempList;
    }

    /**
     * 递归绑定子节点
     * @param ptreeNode
     * @param powerTreeList
     */
    private void bindChildren(TreeNode ptreeNode, List<TreeNode> powerTreeList) {
        //遍历所有节点找孩子节点
        for (TreeNode childrenTreeNode : powerTreeList) {
            if (ptreeNode.getId() == childrenTreeNode.getPid()) {//是孩子节点
                List<TreeNode> children = ptreeNode.getChildren();
                if (children == null) { // 如果孩子节点为空
                    List<TreeNode> childrenList = new ArrayList<TreeNode>();
                    childrenList.add(childrenTreeNode);
                    ptreeNode.setChildren(childrenList);
                } else { // 如果不为空
                    children.add(childrenTreeNode);
                }
                bindChildren(childrenTreeNode, powerTreeList);
            }
        }
    }

    /**
     * 查询树列表
     * @return
     */
    /*public List<TreeNode> getTreeList(){
        List<TreeNode> powerTreeList = getList();
        System.out.println(powerTreeList);
        List<TreeNode> powerTempList = new ArrayList();
        if(powerTreeList!=null&&powerTreeList.size()>0){
            for (TreeNode treeNode : powerTreeList) {
                if (treeNode.getPid()==0){      //如果该节点的父节点是0，则说明是一级节点
                    powerTempList.add(treeNode);
                    bindChildren(treeNode,powerTreeList); //绑定子节点
                }
            }
        }
        return powerTempList;
    }*/

    /**
     * 绑定子节点
     * @param treeNode
     * @param powerTreeList
     */
    /*public void bindChildren(TreeNode treeNode,List<TreeNode> powerTreeList){
        //遍历所有节点，找该节点的子节点
        for (TreeNode node : powerTreeList) {
            if (node.getPid()==treeNode.getId()){   //是孩子节点
                List<TreeNode> children = treeNode.getChildren();
                if(children==null){
                    List<TreeNode> childrenList = new ArrayList();
                    childrenList.add(node);
                    treeNode.setChildren(childrenList);
                }else{
                    children.add(node);
                }
                bindChildren(node,powerTreeList);
            }
        }
    }*/
}
