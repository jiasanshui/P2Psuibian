package com.aaa.ssm.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * className:RandomUtil
 * discription:随机生成编号工具类
 * author:fhm
 * createTime:2018-12-13 14:43
 */
public class RandomUtil {
    /**
     * 随机生成编号
     * @return
     */
    public static String getBorrowNumByTime() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate=sdf.format(new Date());
        String result="";
        Random random=new Random();
        for(int i=0;i<3;i++){
            result+=random.nextInt(10);
        }
        return newDate+result;
    }

}
