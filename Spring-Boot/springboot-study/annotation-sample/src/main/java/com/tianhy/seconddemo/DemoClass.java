package com.tianhy.seconddemo;


import org.springframework.stereotype.Service;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/10/2 12:04
 **/
//希望这个类被spring托管
@Service
public class DemoClass {

    public void say(){
        System.out.println("hello world!");
    }
}
