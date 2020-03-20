package com.tianhy.springbootjdbc.controller;

import com.tianhy.springbootjdbc.domain.User;
import com.tianhy.springbootjdbc.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.concurrent.*;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/4/11
 **/
@RestController
public class UserController {

    private final UserRepository userRepository;

    /**
     * 定义一个储存5个线程的线程池
     */
    private final ExecutorService executorService = Executors.newFixedThreadPool(5);

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/web/mvc/user/save")
    public Boolean save(@RequestBody User user) throws ExecutionException, InterruptedException {
        System.out.printf("[Thread : %s ] UserController starts saving user : %s \n", Thread.currentThread().getName(), user);
//        //5次save()
//        Future<Boolean> future = executorService.submit(()->{
//           return userRepository.save(user);
//        });
//        return future.get();

//        return userRepository.save(user);
        return userRepository.PlatformTransSave(user);
//        return userRepository.transactionalSave(user);

    }
}
