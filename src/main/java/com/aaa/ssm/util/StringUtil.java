package com.aaa.ssm.util;

/**
 * className:StringUtil
 * discription:判断对象是否为空工具类
 * author:fhm
 * createTime:2018-12-14 11:49
 */
public class StringUtil {
    /**
     * 判断对象是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(Object str) {
        if(str == null || "".equals(str)){
            return true;
        }
        return false;
    }
}
