package com.tianhy.fourthdemo;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/10/2 13:51
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({AImportSelector.class, BDefinitionRegistrar.class})
public @interface EnableDefinitionService {

    Class<?>[] exclude() default {};

}
