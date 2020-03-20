package com.tianhy.springbootrest.exception;

import com.tianhy.springbootrest.pojo.Result;
import com.tianhy.springbootrest.pojo.User;
import com.tianhy.springbootrest.service.UserService;
import com.tianhy.springbootrest.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/20
 **/
@ControllerAdvice(
        //指定拦截包的控制器
        basePackages = {"com.tianhy.springbootrest.*"},
        //限定这些注解的类才被拦截
        annotations = {Controller.class, RestController.class}
)
public class VoControllerAdvice {


    @Autowired
    private UserService userService;

    //异常处理
    //可以定义异常类型进行拦截处理
    @ExceptionHandler(value = NotFoundExeception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> exeception(HttpServletRequest request, NotFoundExeception ex) {

        Map<String, Object> result = new HashMap<>();
        result.put("code", ex.getCode());
        result.put("msg", ex.getCustomMsg());
        return result;
    }


    @GetMapping(value = "/user/exp/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserVo getUserForExp(@PathVariable Long id) {
        User user = userService.getUser(id);
        if (user == null) {
            throw new NotFoundExeception(1L, "找不到用户");
        }
        return po2Vo(user);
    }

    public UserVo po2Vo(User user) {
        UserVo userVo = new UserVo(user.getId(), user.getUserName(), user.getNote(), user.getSex().getId(),
                user.getSex().getName());
        return userVo;
    }
}
