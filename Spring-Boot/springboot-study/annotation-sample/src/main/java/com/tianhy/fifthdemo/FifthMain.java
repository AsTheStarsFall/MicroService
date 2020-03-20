package com.tianhy.fifthdemo;

import com.tianhy.core.Core;
import com.tianhy.fourthdemo.EnableDemoMain;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * {@link}
 *
 * @Desc: SPI机制
 * @Author: thy
 * @CreateTime: 2019/10/2 14:34
 **/
@SpringBootApplication
public class FifthMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(EnableDemoMain.class, args);

        //获取到其他工程的bean
        System.out.println(context.getBean(Core.class).say());
    }

}
