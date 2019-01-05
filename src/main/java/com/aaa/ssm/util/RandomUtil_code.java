package com.aaa.ssm.util;

import java.util.Random;

/**
 * className:RandomUtil_code
 * discription:随机生成固定位数随机数
 * author:fhm
 * createTime:2019-01-04 18:33
 */
public class RandomUtil_code {
    public static String random(){
        //创建一个随机数
        Random r=new Random();
        int i=r.nextInt(900000);
        //循环条件大于7位
        while(i<100000){
            i= r.nextInt(900000);
        }
        String num=Integer.toString(i);
        //返回生成的随机数
        return num;
    }

    //测试方法
    public static void main(String[] args) {
        String i=RandomUtil_code.random();
        System.out.println(i);
    }

}
