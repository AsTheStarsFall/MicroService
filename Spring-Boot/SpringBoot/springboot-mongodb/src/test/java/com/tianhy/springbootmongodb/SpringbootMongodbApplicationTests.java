package com.tianhy.springbootmongodb;

import com.alibaba.fastjson.JSON;
import com.tianhy.springbootmongodb.pojo.Role;
import com.tianhy.springbootmongodb.pojo.User;
import com.tianhy.springbootmongodb.repository.UserRepository;
import com.tianhy.springbootmongodb.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringbootMongodbApplicationTests {

    @Autowired
    private UserService userService = null;


    @Autowired
    private UserRepository userRepository = null;

    @Test
    public void save() {
        int count = 10;
        for (int i = 1; i < count; i++) {
            User user = new User();
            user.setId(Long.valueOf(i));
            user.setNote("note_" + i);
            user.setUserName("user_name_" + i);
            List<Role> roles = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                Role role = new Role();
                role.setId(Long.valueOf(j));
                role.setNote("note_" + j);
                role.setRoleName("role_" + j);
                roles.add(role);
            }
            user.setRoles(roles);
            userService.saveUser(user);
        }
    }

    @Test
    public void save1() {
        User user = new User();
        user.setId(Long.valueOf(1));
        user.setNote("note_1");
        user.setUserName("user_name_1");
        userService.saveUser(user);
    }


    @Test
    public void findAll() {
        List<User> allUsers = userService.findAllUsers();
        System.out.println(JSON.toJSONString(allUsers));
    }

    @Test
    public void nakeLike() {
        List<User> allUsers = userRepository.findByUserNameLike("user_name_1");
        System.out.println(JSON.toJSONString(allUsers));
    }

    @Test
    public void findByIdOrUserName(){
        User user_name_1 = userRepository.findUserByIdOrUserName(1L, "user_name_1");
        System.out.println(user_name_1.toString());
    }
}
