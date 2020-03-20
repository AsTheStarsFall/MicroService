package com.tianhy.study.config;

import com.netflix.config.DynamicConfiguration;
import org.apache.commons.configuration.*;


/**
 * @Author: thy
 * @Date: 2020/2/14 17:37
 * @Desc: 通用配置
 */
public class GenericConfigDemo {

    public static void main(String[] args) {

        //Java系统属性
        print(System.getProperty("name"));
        print(System.getProperty("name", "thy"));
        //OS环境属性
        print(System.getenv());

        //将Properties转为Integer类型
        print(Integer.getInteger("age"));
        print(Boolean.getBoolean("male"));

        //commons-configuration
        Configuration systemConfiguration = new SystemConfiguration();
        Configuration databaseConfiguration = new DatabaseConfiguration(null, "",
                "", "");
        Configuration xmlConfiguration = new XMLConfiguration();
        Configuration propertiesConfiguration = new PropertiesConfiguration();
        Configuration environmentConfiguration = new EnvironmentConfiguration();
        Configuration compositeConfiguration = new CompositeConfiguration();

        //Netflix
        Configuration dynamicConfiguration = new DynamicConfiguration();
    }

    private static void print(Object o) {
        System.out.println(o);
    }
}
