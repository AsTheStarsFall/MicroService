package com.tianhy.springbootc3.pojo;

import com.tianhy.springbootc3.pojo.definition.Animal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/15
 **/
@Slf4j
@Component
public class Dog implements Animal {

    @Override
    public void use() {
        log.info("狗狗 [{}] 看门",Dog.class.getSimpleName());
    }
}
