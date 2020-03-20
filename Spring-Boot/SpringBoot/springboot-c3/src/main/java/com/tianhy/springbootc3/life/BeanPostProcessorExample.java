package com.tianhy.springbootc3.life;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Component;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/15
 **/
@Component
@Slf4j
//public class BeanPostProcessorExample implements BeanPostProcessor {
    public class BeanPostProcessorExample {

    /**
     * BeanPostProcessor 的预初始化方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//
//        log.info("BeanPostProcessor 调用 postProcessBeforeInitialization 方法 \n" +
//                "参数 ({},{})",bean.getClass().getSimpleName(),beanName);
//        return bean;
//    }
//
//    /**
//     * BeanPostProcessor 的后初始化方法
//     * @param bean
//     * @param beanName
//     * @return
//     * @throws BeansException
//     */
//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        log.info("BeanPostProcessor 调用 postProcessAfterInitialization 方法 \n" +
//                "参数 ({},{})",bean.getClass().getSimpleName(),beanName);
//
//        return bean;
//    }
}
