package com.tianhy.springbootrest.Controller;

import com.tianhy.springbootrest.enumeration.SexEnum;
import com.tianhy.springbootrest.pojo.Result;
import com.tianhy.springbootrest.pojo.User;
import com.tianhy.springbootrest.service.UserService;
import com.tianhy.springbootrest.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/20
 **/
@Controller
public class UserController {

    @Autowired
    private UserService userService = null;

    @GetMapping("/restful")
    public String index() {
        return "restful";
    }

    @PostMapping("/user")
    @ResponseBody
    public Result insertUser(@RequestBody UserVo userVo) {
        User user = this.vo2Po(userVo);
        userService.insertUser(user);
        return Result.success();
    }


    @GetMapping("/user/{id}")
    @ResponseBody
    public Result getUser(@PathVariable Long id) {
        UserVo userVo = po2Vo(userService.getUser(id));
        return Result.success().add("user", userVo);
    }

    @PutMapping("/user/{id}")
    @ResponseBody
    public Result updateUser(@PathVariable Long id, @RequestBody UserVo userVo) {
        User user = vo2Po(userVo);
        user.setId(id);
//        userService.updateUser(user);
//        UserVo userVo1 = po2Vo(user1);

        UserVo po2Vo = po2Vo(userService.updateUser(user));
        return Result.success().add("user", po2Vo);
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
