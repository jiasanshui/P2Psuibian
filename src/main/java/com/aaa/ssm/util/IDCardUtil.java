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
            //sexCode=Integer.parseInt(idCard.substring(idCard.length()-4,idCard.length()-1))%2==0?"女":"男";
            sexCode=Integer.parseInt(String.valueOf(idCard.charAt(idCard.length() - 2)))%2==0?"女":"男";
            age = (year - Integer.parseInt("19" + idCard.substring(6, 8))) + "";
        }else if (flag && idCard.length() == 18){
            birthday=idCard.substring(6,10)+"-"
                    +idCard.substring(10,12)+"-"
                    +idCard.substring(12,14);
            //sexCode=Integer.parseInt(idCard.substring(idCard.length()-3,idCard.length()))%2==0?"女":"男";
            sexCode=Integer.parseInt(String.valueOf(idCard.charAt(idCard.length() - 2)))%2==0?"女":"男";
            age = (year - Integer.parseInt(idCard.substring(6, 10))) + "";
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("birthday", birthday);
        map.put("age", age);
        map.put("sexCode", sexCode);
        return map;
    }

    /**
     * @Description:返回身份证是否符合规则
     * 校验规则： 1、将前面的身份证号码17位数分别乘以不同的系数。第i位对应的数为[2^(18-i)]mod11。从第一位到第十七位的系数分别为：7
     * 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2 ； 2、将这17位数字和系数相乘的结果相加；
     * 3、用加出来和除以11，看余数是多少？； 4、余数只可能有0 1 2 3 4 5 6 7 8 9
     * 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3 2；
     * @return 返回false说明，身份证号码不符合规则，返回true说明身份证号码符合规则
     * @author Mr_zhao
     * @date 2016-1-11 上午9:45:03
     */
    public static boolean checkCardId(String cid) {
        boolean flag = false;
        int len = cid.length();
        int kx = 0;
        // 身份证号第一位到第十七位的系数装入到一个整型数组
        int Weight[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
        // 需要进行运算的是身份证前17位
        for (int i = 0; i < len - 1; i++) {
            // 把身份证的数字分拆成一个个数字
            int x = Integer.parseInt(String.valueOf(cid.charAt(i)));
            // 然后相加起来
            kx += Weight[i] * x;
        }
        // 用加出来和模以11，看余数是多少？
        int mod = kx % 11;
        // 最后一位身份证的号码的对应号码,一一对应
        // (0,1,2,3,4,5,6,7,8,9,10)
        // (1,0,X,9,8,7,6,5,4,3,2)
        Character[] checkMods = { '1', '0', 'X', '9', '8', '7', '6', '5', '4',
                '3', '2' };
        // 获取身份证最后的一个验证码
        Character lastCode = cid.charAt(len - 1);
        // 判断是否对应
        String idNumber = lastCode.toString().toLowerCase();
        String checkMods2 = checkMods[mod].toString().toLowerCase();
        if (checkMods2.equals(idNumber)) {
            flag = true;
        }
        return flag;
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        Map<String, String> birAgeSex = IDCardUtil.getBirAgeSex("410522199811112245");
        System.out.println("生日："+birAgeSex.get("birthday")+",年龄："+birAgeSex.get("age")+",性别："+birAgeSex.get("sexCode"));
    }
}
