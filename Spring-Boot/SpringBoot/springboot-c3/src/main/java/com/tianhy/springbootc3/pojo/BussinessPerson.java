package com.tianhy.springbootc3.pojo;

import com.tianhy.springbootc3.pojo.definition.Animal;
import com.tianhy.springbootc3.pojo.definition.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/15
 **/
@Component
@Slf4j
public class BussinessPerson implements Person{
//
//    public class BussinessPerson implements Person,
//            //加入生命周期和自定义
//            BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean,DisposableBean {

    //没有构造参数的情况
    /**
     * 根据属性的类型找到对应的Bean进行注入
     * Autowired的规则：
     * 1.首先根据类型找到对应的Bean，如果对应类型的Bean不是唯一的，会根据其属性名称和Bean名称进行匹配
     * 2.默认必须找到对应的Bean
     * 如果不确定标注的属性一定存在，可以设置null，required = false
     * Dog/cat是Animal的一种，所以会把Dog/cat的实例注入BussinessPerson中
     *
     * @Qualifier("dog"),通过类型和name找到Bean,Beanname是唯一的，所以就消除了歧义
     */
//    @Autowired
//    @Qualifier("dog")
//    private Animal animal = null; //Animal 有多个实现类，也就是多个类型


    @Value("#{bird}")
    private Animal animal = null;

    //有构造参数的情况
//    public BussinessPerson(@Autowired@Qualifier("dog") Animal animal) {
//        this.animal = animal;
//    }

    @Override
    public void service() {
        this.animal.use();
    }

    //还可以加到方法上
//    @Autowired @Qualifier("dog")
//    @Autowired
    @Override
    public void setAnimal(Animal animal) {
        log.info("延迟依赖注入...");
        this.animal = animal;
    }


//    @Override
//    public void setBeanName(String name) {
//        log.info("[{}]调用 BeanNameAware 的 setBeanName",this.getClass().getSimpleName());
//    }
//
//    @Override
//    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
//        log.info("[{}]调用 BeanFactoryAware 的 setBeanFactory",this.getClass().getSimpleName());
//
//    }
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        log.info("[{}]调用 ApplicationContextAware 的 setApplicationContext",this.getClass().getSimpleName());
//
//    }
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        log.info("[{}]调用 InitializingBean 的 afterPropertiesSet",this.getClass().getSimpleName());
//
//    }
//
//    @PostConstruct
//    public void init(){
//        log.info("[{}]注解 @PostConstruct 定义的自定义初始化方法",this.getClass().getSimpleName());
//
//    }
//
//    @PreDestroy
//    public void destroy1(){
//        log.info("[{}]注解 @PreDestroy 定义的自定义销毁方法",this.getClass().getSimpleName());
//
//    }
//
//    @Override
//    public void destroy() throws Exception {
//        //Disposable 一次性
//        log.info("[{}] DisposableBean 的 destroy 方法",this.getClass().getSimpleName());
//    }



}
