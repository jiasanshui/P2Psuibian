package com.aaa.ssm.mapper;

import com.aaa.ssm.entity.User;
import org.springframework.stereotype.Component;

/**
 * className:UserMapper
 * discription:
 * author:jiasanshui
 * createTime:2018-12-06 20:34
 */
@Component
public interface UserMapper {

    User findByUserName(String name);

    User findById(Integer userId);
}
