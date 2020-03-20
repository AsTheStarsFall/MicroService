package com.tianhy.springbootc3.config;

import com.tianhy.springbootc3.other.pojo.Squirrel;
import com.tianhy.springbootc3.pojo.*;
import com.tianhy.springbootc3.pojo.definition.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Field;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/15
 **/
@Slf4j
public class IoCTest {

    public static void main(String[] args) {

        //读AppConfig 配置，将配置里的bean 装配到 IOC
//        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
//        User bean = ctx.getBean(User.class);
//        log.info("获得bean {}", bean);

//        Person person = ctx.getBean(BussinessPerson.class);
//        log.info("获得bean {}", person);
//        person.service();

        //el 表达式
        ELPojo bean = ctx.getBean(ELPojo.class);
        log.info("{}",bean.getDog() == bean.getAnimal());
        Field[] declaredFields = bean.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            try {
                Object o = field.get(bean);
                System.out.println(o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        // 通过XML 配置bean
//        Squirrel squirrel = ctx.getBean(Squirrel.class);
//        squirrel.use();


        //作用域
//        ScopeBean scopeBean = ctx.getBean(ScopeBean.class);
//        ScopeBean scopeBean1 = ctx.getBean(ScopeBean.class);
//        System.out.println(scopeBean==scopeBean1);

//        //关闭IOC
//        ctx.close();


    }
}
