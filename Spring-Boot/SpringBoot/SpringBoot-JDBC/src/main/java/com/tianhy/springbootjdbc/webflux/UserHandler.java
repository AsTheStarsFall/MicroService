package com.tianhy.springbootjdbc.webflux;

import com.tianhy.springbootjdbc.domain.User;
import com.tianhy.springbootjdbc.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/4/11
 **/
@Component
public class UserHandler {
    private final UserRepository userRepository;

    public UserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Spring Web Flux 方法(异步)
     * @param request
     * @return
     */
    public Mono<ServerResponse>save(ServerRequest request){
        // 在Spring Web Flux 中用 ServerRequest
        //在Spring Web Mvc 中用 @RequestBody
        System.out.printf("[Thread : %s ] UserHandler starts saving user... \n", Thread.currentThread().getName());
        Mono<User> userMono = request.bodyToMono(User.class);
        Mono<Boolean> booleanMono = userMono.map(userRepository::save);
        return ServerResponse.ok().body(booleanMono,Boolean.class);

    }
}
