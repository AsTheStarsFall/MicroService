package com.tianhy.springbootc3.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * {@link}
 *
 * @Desc: 定义作用域类
 * @Author: thy
 * @CreateTime: 2019/5/15
 **/
@Component
//Bean 的作用域
//@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
//@Scope(Configura
// bleBeanFactory.SCOPE_PROTOTYPE)
/**
 *请求作用域
 * SCOPE_REQUEST、SCOPE_SESSION、SCOPE_APPLICATION
 */
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ScopeBean {
}
