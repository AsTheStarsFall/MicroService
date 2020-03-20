package com.tianhy.springbootmybatis;

import com.tianhy.springbootmybatis.enumeration.SexEnum;
import com.tianhy.springbootmybatis.pojo.Result;
import com.tianhy.springbootmybatis.pojo.User;
import com.tianhy.springbootmybatis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.nutz.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/16
 **/
@RunWith(SpringRunner.class)
//启用随机端口
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class RestTest {

    //rest 测试模板
    @Autowired
    private TestRestTemplate restTemplate = null;

    @Test
    public void getUserTest() {
        User templateForObject = restTemplate.getForObject("/user/{id}", User.class, 1L);
        Assert.assertNotNull(templateForObject);
    }

    @Test
    public void getUserBySexTest() {
        Result forObject = restTemplate.getForObject("/user/sex/{sex}/{pn}", Result.class, 2, 10);
        log.info(Json.toJson(forObject));
        Assert.assertNotNull(forObject);
    }

    @Test
    public void insertCacheTest() {
        User user = new User(null, "name_cache1", "note_cache1", SexEnum.FEMALE);
        Integer forObject = restTemplate.getForObject("/user/insert", Integer.class, user);
        log.info(forObject.toString());
    }

    @MockBean
    private UserService userService = null;

    @Test
    public void mockTest() {
        //构建虚拟对象 mockUser
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUserName("uname1");
        mockUser.setNote("note1");
        mockUser.setSex(SexEnum.FEMALE);

        //构建mock方法
        BDDMockito.given(userService.getUser(1L)).willReturn(mockUser);

        //mock 测试
        User user = userService.getUser(1L);
        Assert.assertTrue(user.getId() == 1L);

    }


}
