package com.aaa.ssm.dao;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * className:DemoDao
 * discription:
 * author:jiasanshui
 * createTime:2018-11-29 19:43
 */
@Component
public interface DemoDao {

    List<Map> getList();
}
