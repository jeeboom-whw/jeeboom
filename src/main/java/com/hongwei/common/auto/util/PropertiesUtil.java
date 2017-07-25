package com.hongwei.common.auto.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 配置文件工具类
 */
public class PropertiesUtil {

    private static Properties properties = null;

    public static Properties getProperties(){
        try {
            if(properties == null){
                properties = new Properties();
                String fileDir = PropertiesUtil.class.getClassLoader().getResource("generator.xml").getFile().replace("20%"," ");
                properties.loadFromXML(new FileInputStream(fileDir));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    /** 通过key获取配置文件中的值 */
    public static String getValue(String key){
        return getProperties().get(key).toString();
    }
}
