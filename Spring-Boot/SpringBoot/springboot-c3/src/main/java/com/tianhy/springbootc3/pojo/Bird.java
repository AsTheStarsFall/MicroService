package com.tianhy.springbootc3.pojo;

import com.tianhy.springbootc3.pojo.definition.Animal;
import org.springframework.stereotype.Component;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/15
 **/
@Component("bird")
public class Bird implements Animal {
    @Override
    public void use() {
        System.out.println("小鸟叽叽喳喳");
    }
}
