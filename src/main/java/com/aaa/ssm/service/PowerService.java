package com.aaa.ssm.service;

import com.aaa.ssm.entity.TreeNode;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * className:PowerService
 * discription:
 * author:fhm
 * createTime:2018-12-16 20:43
 */
@Component
public interface PowerService {
    /**
     * 获取权限数据(获取登陆人的角色对应所有权限)
     * @return
     */
    List<TreeNode> getPowerList();
}
