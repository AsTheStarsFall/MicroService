package com.tianhy.springbootc3.pojo;

import com.tianhy.springbootc3.pojo.definition.Animal;
import lombok.Data;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/15
 **/
@Data
@Component("elPojo")
public class ELPojo implements Serializable {


    @Autowired
    private Animal dog = null;

    @Autowired
    @Qualifier("dog")
    private Animal animal = null;

    @Value("#{T(System).currentTimeMillis()}")
    private Long time = null;

    @Value("#{'Spring EL 表达式赋值字符串'}")
    private String str = null;

    @Value("#{9.3E3}")
    private double d;

    @Value("#{3.14}")
    private float f;

    @Value("#{1+2}")
    private int run;

    @Value("#{1+3}")
    private int run1;

//    @Value("#{dog == animal}")
//    private boolean flag;

//    @Value("#{run > run1 ? '大于' : '小于'}")
//    private String result = null;

}

