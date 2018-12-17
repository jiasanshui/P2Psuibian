package com.aaa.ssm.util;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * className:IDCardUtil
 * discription:
 * author:fhm
 * createTime:2018-12-16 15:15
 */
public class IDCardUtil {
    /**
     *  通过身份证号码获取出生日期、性别、年龄
     * @param idCard
     * @return 返回的出生日期格式：1990-01-01   性别格式：女，男
     */
    public static Map<String,String> getBirAgeSex(String idCard){
        String birthday = "";
        String age = "";
        String sexCode = "";
        int year = Calendar.getInstance().get(Calendar.YEAR);
        char[] number = idCard.toCharArray();
        boolean flag = true;
        if(number.length==15){
            for (int x=0;x<number.length;x++) {
                if (!flag) return new HashMap<String, String>();
                flag = Character.isDigit(number[x]);
            }
        }else if (number.length==18){
            for (int x=0;x<number.length - 1;x++) {
                if (!flag) return new HashMap<String, String>();
                flag = Character.isDigit(number[x]);
            }
        }
        if (flag && idCard.length()==15){
            birthday="19"+idCard.substring(6,8)+"-"
                    +idCard.substring(8,10)+"-"
                    +idCard.substring(10,12);
            sexCode=Integer.parseInt(idCard.substring(idCard.length()-4,idCard.length()-1))%2==0?"女":"男";
            age = (year - Integer.parseInt("19" + idCard.substring(6, 8))) + "";
        }else if (flag && idCard.length() == 18){
            birthday=idCard.substring(6,10)+"-"
                    +idCard.substring(10,12)+"-"
                    +idCard.substring(12,14);
            sexCode=Integer.parseInt(idCard.substring(idCard.length()-3,idCard.length()))%2==0?"女":"男";
            age = (year - Integer.parseInt(idCard.substring(6, 10))) + "";
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("birthday", birthday);
        map.put("age", age);
        map.put("sexCode", sexCode);
        return map;
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        Map<String, String> birAgeSex = IDCardUtil.getBirAgeSex("411224199507108126");
        System.out.println("生日："+birAgeSex.get("birthday")+",年龄："+birAgeSex.get("age")+",性别："+birAgeSex.get("sexCode"));
    }
}
