package com.aaa.ssm;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * className:SpringBootApplication
 * discription:
 * author:jiasanshui
 * createTime:2018-11-29 18:41
 */
@ServletComponentScan
@EnableTransactionManagement
@SpringBootApplication
@MapperScan({"com.aaa.ssm.dao","com.aaa.ssm.mapper"})
 public class SpringBootMainApp {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SpringApplication.run(SpringBootMainApp.class);
    }
}
