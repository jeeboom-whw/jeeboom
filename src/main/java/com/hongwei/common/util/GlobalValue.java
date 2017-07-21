package com.hongwei.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 获取Appliction.yml配置文件中的属性值
 * @author WHW
 */
@Component
public class GlobalValue {

    public static String dbUrl;

    public static String dbUsername;

    public static String dbPassword;

    public static String dbDriver;

    public static String basepackage;

    public static String outRoot;

    @Value("${spring.datasource.url}")
    public void setDbUrl(String dbUrl){
        GlobalValue.dbUrl = dbUrl;
    }

    @Value("${spring.datasource.username}")
    public void setDbUsername(String dbUsername){
        GlobalValue.dbUsername = dbUsername;
    }

    @Value("${spring.datasource.password}")
    public void setDbPassword(String dbPassword){
        GlobalValue.dbPassword = dbPassword;
    }

    @Value("${spring.datasource.driverClassName}")
    public void setDbDriver(String dbDriver){
        GlobalValue.dbDriver = dbDriver;
    }

    @Value("${path.basepackage}")
    public static void setBasepackage(String basepackage) {
        GlobalValue.basepackage = basepackage;
    }

    @Value("${path.outRoot}")
    public static void setOutRoot(String outRoot) {
        GlobalValue.outRoot = outRoot;
    }
}
