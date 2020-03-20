package com.tianhy.springbootjdbc.webflux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/4/11
 **/
@Configuration
public class WebFluxConfiguration {

    @Bean
    public RouterFunction<ServerResponse> save(UserHandler userHandler){
        return route(POST("/web/flux/user/save"),userHandler::save);
    }
}
