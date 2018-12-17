package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.PowerDao;
import com.aaa.ssm.entity.Admin;
import com.aaa.ssm.entity.TreeNode;
import com.aaa.ssm.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * className:PowerServiceImpl
 * discription:
 * author:fhm
 * createTime:2018-12-16 20:46
 */
@Service
public class PowerServiceImpl implements PowerService{

    @Autowired
    private HttpSession session;

    //依赖注入
    @Autowired
    private PowerDao powerDao;

    @Override
    public List<TreeNode> getPowerList() {
        Admin user = (Admin)session.getAttribute("user");
        Integer userid=0;
        if(user!=null) {
            userid=user.getId();
        }
        List<TreeNode> powerAllList = powerDao.getPowerList(userid);
        //临时的powerList
        List<TreeNode> powerTempList = new ArrayList<TreeNode>();
        //判断是否为空
        if(powerAllList!=null&&powerAllList.size()>0){
            for(TreeNode ptreeNode:powerAllList){
                if(ptreeNode.getParentid()==0){//如果等于0,说明是一级节点
                    powerTempList.add(ptreeNode);
                    //递归绑定子节点
                    bindChildren(ptreeNode,powerAllList);
                }
            }
        }
        return powerTempList;
    }
    /**
     * 递归绑定子节点
     * @param curNode
     * @param list
     */
    private void bindChildren(TreeNode curNode,List<TreeNode> list){
        for(TreeNode node : list) {
            //如果当前节点的id和循环节点的父ID相等，说明是当前节点的孩子
            if(curNode.getId()==node.getParentid()){
                //取出当前节点的孩子集合
                List<TreeNode> children = curNode.getChildren();
                //如果是没有孩子，孩子集合第一次取出是空的
                if(children==null){
                    children = new ArrayList<TreeNode>();
                }
                //添加孩子
                children.add(node);
                //设置当前孩子集合
                curNode.setChildren(children);
                //自己调用自己，找孩子
                bindChildren(node,list);
            }
        }
    }
}
