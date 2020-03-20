package com.tianhy.study.demo;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: thy
 * @Date: 2020/2/17 16:04
 * @Desc: Spring 事件/监听
 */
public class SpringEventListenerDemo {

    public static void main(String[] args) {
        //注解驱动上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册监听
/*        applicationContext.addApplicationListener(new ApplicationListener<MyApplicationEvent>() {
            @Override
            public void onApplicationEvent(MyApplicationEvent event) {
                System.out.println("接收到事件：" + event.getSource());
            }
        });*/

        applicationContext.addApplicationListener((ApplicationListener<MyApplicationEvent>)
                event -> System.out.println("接收到事件:" + event.getSource()));
        applicationContext.refresh();
        //发布事件
        applicationContext.publishEvent(new MyApplicationEvent("Hi"));
        applicationContext.publishEvent(new MyApplicationEvent(123));
    }

    private static class MyApplicationEvent extends ApplicationEvent {

        /**
         * Create a new ApplicationEvent.
         *
         * @param source the object on which the event initially occurred (never {@code null})
         */
        public MyApplicationEvent(Object source) {
            super(source);
        }


    }


}
