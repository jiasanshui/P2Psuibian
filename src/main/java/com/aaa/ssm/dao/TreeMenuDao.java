package com.aaa.ssm.dao;

import com.aaa.ssm.entity.TreeNode;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * className:TreeMenuDao
 * discription:
 * author:jiasanshui
 * createTime:2018-12-04 19:21
 */
@Component
public interface TreeMenuDao {

    /**
     * 获取树的json数据
     * @return
     */
    @Select("select * from tb_power")
    List<TreeNode> getList();

}
