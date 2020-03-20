package com.tianhy.study.route;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author: thy
 * @Date: 2020/2/18 3:54
 * @Desc:
 */
@Component
public class ZuulPropertiesRefresher implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    private RouteLocator routeLocator;

    @ApolloConfigChangeListener(value = "TEST1.zuul-config")
    public void onChange(ConfigChangeEvent event) {
        boolean zuulConfigChanged = false;
        for (String changedKey : event.changedKeys()) {
            if (changedKey.startsWith("zuul.")) {
                zuulConfigChanged = true;
                break;
            }
        }
        if (zuulConfigChanged) {
            refreshZuulProperties(event);
        }
    }

    private void refreshZuulProperties(ConfigChangeEvent event) {
        System.out.println("refreshing zuul properties");
        this.applicationContext.publishEvent(new EnvironmentChangeEvent(event.changedKeys()));
        this.applicationContext.publishEvent(new RoutesRefreshedEvent(routeLocator));
        System.out.println("zuul properties has been refreshed");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

    }
}
