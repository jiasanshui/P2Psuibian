package com.aaa.ssm.service.impl;

import com.aaa.ssm.dao.PowerDao;
import com.aaa.ssm.entity.Admin;
import com.aaa.ssm.entity.Permission;
import com.aaa.ssm.entity.TbRole;
import com.aaa.ssm.entity.TreeNode;
import com.aaa.ssm.service.PowerService;
import com.aaa.ssm.util.StringUtil;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
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

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

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

    @Override
    public Map<String, Object> getById(int powerId) {
        List<Map<String, Object>> powerList = powerDao.getById(powerId);
        if(powerList!=null&&powerList.size()>0){
            return powerList.get(0);
        }
        return null;
    }

    @Override
    public int update(Map map) {
        return powerDao.update(map);
    }

    /**
     * 权限菜单添加
     * @param map
     * @return
     */
    @Override
    public int add(Map map) {
        if(StringUtil.isEmpty(map.get("parentid"))){
            map.put("parentid",0);
        }
        return powerDao.add(map);
    }

    @Override
    public int delete(Integer powerId) {
        return powerDao.delete(powerId);
    }

    @Override
    public List<TreeNode> getCheckList(Integer roleid) {
        return powerDao.getListByRole(roleid);
    }

    @Override
    public int saveRolePower(TbRole role) {
        SqlSession sqlSession = null;
        Boolean flag = true;
       try {
            sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
            PowerDao powerDaos = sqlSession.getMapper(PowerDao.class);
            String powersid = role.getPowersid();
            Integer roleid = role.getId();
            if (powersid==""||powersid==null||powersid=="null"){
                int del = powerDao.delRolePower(roleid);
                return del;
            }
            String[] powerids = powersid.split(",");
            powerDao.delRolePower(roleid);
            for (String powerid : powerids) {
                int i = powerDaos.saveRolePower(roleid,Integer.valueOf(powerid));
                if (i==0) { flag = false;}
            }
            System.out.println(flag);
            if(flag) {
                sqlSession.commit();
                return 1;
            }else{
                sqlSession.rollback();
                return 0;
            }
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 权限树更新操作 根据id获取权限的信息
     * @param id
     * @return
     */
    @Override
    public TreeNode getPowerById(Integer id) {
        return powerDao.getPowerById(id);
    }

    /**
     * 获取所有的权限树（easyui）
     * @return
     */
    @Override
    public List<Permission> getTreeList() {
        List<Map> treeList=powerDao.getTreeList();
        //定义返回列表
        List<Permission> powerList = new ArrayList<Permission>();
        //判断是否为空
        if(treeList!=null&&treeList.size()>0){
            Permission treeNode = null;
            //循环遍历查询出来的权限，构造TreeNode对象，加入powerList，构造该角色能够看到的树
            for(Map powerMap:treeList){
                // treeNode = new TreeNode(id, text, parentId, state, iconCls, url);
                treeNode = new Permission(Integer.valueOf(powerMap.get("ID")+""), powerMap.get("LABEL")+"",
                        Integer.valueOf(powerMap.get("PARENTID")+""),null
                        , powerMap.get("ICONCLASS")+"", powerMap.get("URL")+"");
                powerList.add(treeNode);
            }
        }
        return powerList;
    }

    /**
     * 获取所有的权限树2（easyui）
     * @return
     */
    @Override
    public List<Permission> gettreeList() {
        List<Permission> powerAllList = getTreeList();
        //临时的powerList
        List<Permission> powerTempList = new ArrayList<Permission>();
        //判断是否为空
        if(powerAllList!=null&&powerAllList.size()>0){
            for(Permission ptreeNode:powerAllList){
                if(ptreeNode.getParentid()==0){//如果等于0,说明是一级节点
                    powerTempList.add(ptreeNode);
                    //递归绑定子节点
                    bindChild(ptreeNode,powerAllList);
                }
            }
        }
        return powerTempList;
    }

    /**
     * 递归绑定子节点(easyui)
     * @param curNode
     * @param list
     */
    private void bindChild(Permission curNode,List<Permission> list){
        for(Permission node : list) {
            //如果当前节点的id和循环节点的父ID相等，说明是当前节点的孩子
            if(curNode.getId()==node.getParentid()){
                //取出当前节点的孩子集合
                List<Permission> children = curNode.getChildren();
                //如果是没有孩子，孩子集合第一次取出是空的
                if(children==null){
                    children = new ArrayList<Permission>();
                }
                //添加孩子
                children.add(node);
                //设置当前孩子集合
                curNode.setChildren(children);
                //自己调用自己，找孩子
                bindChild(node,list);
            }
        }
    }
    /**
     * 修改(vue+element ui)
     * @param map
     * @return
     */
    @Override
    public int updateMenu(Map map) {
        return powerDao.updateMenu(map);
    }

    /**
     * 添加(vue+element ui)
     * @param map
     * @return
     */
    @Override
    public int addMenu(Map map) {
        return powerDao.addMenu(map);
    }

    /**
     * 表单唯一性验证
     * @param label
     * @return
     */
    @Override
    public List<Map> getLabel(String label) {
        return powerDao.getLabel(label);
    }
}
