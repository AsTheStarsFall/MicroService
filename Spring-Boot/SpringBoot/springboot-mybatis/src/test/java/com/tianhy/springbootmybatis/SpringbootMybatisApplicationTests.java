package com.tianhy.springbootmybatis;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tianhy.springbootmybatis.enumeration.SexEnum;
import com.tianhy.springbootmybatis.pojo.Result;
import com.tianhy.springbootmybatis.pojo.User;
import com.tianhy.springbootmybatis.service.UserBatchService;
import com.tianhy.springbootmybatis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringbootMybatisApplicationTests {

    @Autowired
    private UserService userService = null;

    @Autowired
    private UserBatchService userBatchService=null;


    @Test
    public void insertUser() {
        User user = new User(null, "name_2", "note_2", SexEnum.FEMALE);
        int i = userService.insertUser(user);
        System.out.println("----------------------------");
        System.out.println(i);
    }

    @Test
    public void updateUser() {
        User user = new User(1L, "name_1", "note_1",SexEnum.getSexEnumById(1));
        User user1 = userService.updateUser(user);
        System.out.println("----------------------------");
        System.out.println(user1);
    }

    //批量插入
    @Test
    public void batchInsert() {
        List<User> list = new ArrayList<>();
        int count = 100;
        for (int i = 0; i < count; i++) {
            User user = new User(null, "testName" + i, "testNote" + i, (i % 2) == 1 ? SexEnum.FEMALE : SexEnum.MALE);
            list.add(user);
        }
        userService.batchInsert(list);
    }

    @Test
    public void selectBySex() {
        List<User> users = userService.selectBySex(2);
        System.out.println(users.size());
    }

    //逻辑分页
    @Test
    public void rowBoundsTest() {
        //从start+1开始取pageSize条记录
        int start = 5;
        int pageSize = 10;
        RowBounds rowBounds = new RowBounds(start, pageSize);
        List<User> users = userService.selectAll(rowBounds);
        //==>  Preparing: SELECT id, user_name userName, note, sex FROM t_user LIMIT ?, ?
        //==> Parameters: 5(Integer), 10(Integer)
//        for (User user : users) {
//            System.out.println(user.toString());
//        }
    }

    //物理分页
    @Test
    public void limit() {
        //第5页的10条记录
        //从41条开始取10条记录
        List<User> users = userService.selectListLimit(5, 10);
        log.debug(users.get(0).toString());
        //==>  Preparing: SELECT * FROM t_user LIMIT ?,?
        //==> Parameters: 40(Integer), 10(Integer)
    }

    //pageHelper
    @Test
    public void pageHelperTest() {
        PageHelper.startPage(5, 10);
        List<User> users = userService.selectBySex(2);
        PageInfo pageInfo = new PageInfo(users, 10);
//        log.info("pageInfo {}", Json.toJson(pageInfo));
        int[] navigatepageNums = pageInfo.getNavigatepageNums();
        List<User> list = pageInfo.getList();
        for (int i = 0; i < navigatepageNums.length; i++) {
            log.info("第 {} 页：{}", i + 1, Json.toJson(list.get(i)));
        }
        Result.success().add("pageInfo", pageInfo);
    }

    //测试传播行为
    @Test
    public void propagationTest() {
        int count = 5;
        List<User> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            User user = new User(null, "txname" + i, "txnote" + i, (i % 2) == 1 ? SexEnum.FEMALE : SexEnum.MALE);
            list.add(user);
        }
        userBatchService.insertUsers(list);
        //默认情况
        //Fetched SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@3a17b2e3] from current transaction
        //Propagation.REQUIRES_NEW的情况

    }
}
