package com.aaa.ssm.util;

import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * className:demoUtil
 * discription:
 * author:jiasanshui
 * createTime:2018-12-19 17:22
 */

public class demoUtil {

    @Scheduled(cron = "0/2 * * * * *")
    public void test1(){
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
