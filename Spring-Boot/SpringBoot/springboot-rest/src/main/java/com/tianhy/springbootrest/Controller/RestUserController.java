package com.tianhy.springbootrest.Controller;

import com.tianhy.springbootrest.enumeration.SexEnum;
import com.tianhy.springbootrest.pojo.User;
import com.tianhy.springbootrest.service.UserService;
import com.tianhy.springbootrest.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/20
 **/
@RestController
public class RestUserController {

    @Autowired
    private UserService userService = null;

    @GetMapping(value = "/restful2")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("restful");
        return mv;
    }

    @PostMapping("/user2/entity")
    public ResponseEntity<UserVo> insertUser(@RequestBody UserVo uservo) {

        User user = vo2Po(uservo);
        int i = userService.insertUser(user);
        HttpHeaders httpHeader = new HttpHeaders();
        String success = (user == null || i == 0)? "false" : "true";
        httpHeader.add("success",success);
        return new ResponseEntity<>(user,httpHeader, HttpStatus.CREATED);
    }

    @PostMapping("/user2/anno")
    @ResponseStatus(HttpStatus.CREATED)
    public UserVo insertUserAnno(@RequestBody UserVo userVo){
        User user = vo2Po(userVo);
        userService.insertUser(user);
        return userVo;
    }


    //VO--PO
    public User vo2Po(UserVo userVo) {
        User user = new User(userVo.getId(), userVo.getUserName(), userVo.getNote(),
                SexEnum.getSexEnumById(userVo.getSexCode()));
        return user;
    }

    //PO--VO
    public UserVo po2Vo(User user) {
        UserVo userVo = new UserVo(user.getId(), user.getUserName(), user.getNote(), user.getSex().getId(),
                user.getSex().getName());
        return userVo;
    }

    //List<VO>--List<PO>
    public List<User> voes2Poes(List<UserVo> userVos) {
        List<User> users = new ArrayList<>();
        for (UserVo userVo : userVos) {
            User user = vo2Po(userVo);
            users.add(user);
        }
        return users;
    }

    //List<PO>--List<VO>
    public List<UserVo> poes2Voes(List<User> users) {
        List<UserVo> userVoes = new ArrayList<>();
        for (User user : users) {
            UserVo userVo = po2Vo(user);
            userVoes.add(userVo);
        }
        return userVoes;
    }



}
