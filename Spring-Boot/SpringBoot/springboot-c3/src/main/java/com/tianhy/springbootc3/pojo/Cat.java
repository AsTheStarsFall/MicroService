package com.tianhy.springbootc3.pojo;

import com.tianhy.springbootc3.pojo.definition.Animal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
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
//@Primary 优先使用cat，如果需要多个优先的，也不能实现，可以使用@Qualifier
//@Primary
public class Cat implements Animal {

    @Override
    public void use() {
        log.info("猫猫 [{}] 捉老鼠",Cat.class.getSimpleName());
    }
}
