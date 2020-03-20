package com.tianhy.autoconfiguration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * {@link}
 *
 * @Desc: 配置类，对应配置文件前缀为：format.info 的信息
 * @Author: thy
 * @CreateTime: 2019/10/3 5:18
 **/
/**
 * application.properties 配置文件中的前缀
 */
@ConfigurationProperties(prefix = FormatPropterties.FORMAT_PREFIX)
public class FormatPropterties {

    //前缀
    public static final String FORMAT_PREFIX = "format.info";

    private Map<String,Object> info;

    public Map<String, Object> getInfo() {
        return info;
    }

    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }
}
