package com.tianhy.study.config;

import java.io.IOException;
import java.util.Properties;

/**
 * @Author: thy
 * @Date: 2020/2/14 17:35
 * @Desc:
 */
public class PropertiesDemo {

    public static void main(String[] args) throws IOException {
        pro2xml();
    }

    private static void pro2xml() throws IOException {
        //可通过设置JVM属性 -Dname=thy实现
        Properties properties = new Properties();
        properties.setProperty("name", "thy");
        properties.setProperty("age", "27");
        properties.storeToXML(System.out, "注释", "UTF-8");
    }
}
