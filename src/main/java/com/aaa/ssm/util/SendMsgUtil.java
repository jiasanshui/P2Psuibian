package com.aaa.ssm.util;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * className:SendMsgUtil
 * discription:发送短信工具类
 * author:fhm
 * createTime:2018-12-10 08:18
 * appcode:c79abd4066734ca8a6a297d1a5cded83
 * appcode:83604d48b8dd49ba8aa501b77ad4a164
 * appcode:a9b30219e50b4fc085c213714919b59c(1次)
 */
public class SendMsgUtil {
    private String phone;

    public String sendMsg(String phone){
        //手机号可用，发送验证码
        String host = "https://fesms.market.alicloudapi.com";
        String path = "/sms/";
        String method = "GET";
        String appcode = "c79abd4066734ca8a6a297d1a5cded83";
        Map<String, String> headers = new HashMap<String, String>();
        String code="";
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        int  mobile_code = (int) ((Math.random()*9+1)*10000);
        code=String.valueOf(mobile_code);
        querys.put("code", code);
        querys.put("phone", phone);
        querys.put("skin", "2");
        //querys.put("sign", "1");
        //JDK 1.8示例代码请在这里下载：  http://code.fegine.com/Tools.zip
        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 或者直接下载：
             * http://code.fegine.com/HttpUtils.zip
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             * 相关jar包（非pom）直接下载：
             * http://code.fegine.com/aliyun-jar.zip
             */
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            //System.out.println(response.toString());如不输出json, 请打开这行代码，打印调试头部状态码。
            //状态码: 200 正常；400 URL无效；401 appCode错误； 403 次数用完； 500 API网管错误
            //获取response的body
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }
}
