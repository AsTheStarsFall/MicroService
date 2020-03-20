package com.tianhy.springbootmall;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tianhy.springbootmall.pojo.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringbootMallApplicationTests {
    @Autowired
    private TestRestTemplate restTemplate = null;

    @Test
    public void contextLoads() {
        int count = 30000;
        Map<String, Integer> map = new HashMap<>();
        map.put("userId", 1);
        map.put("productId", 1);
        map.put("quantity", 1);
        String s = JSON.toJSONString(map);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> req = new HttpEntity<>(s, headers);

        for (int i = 0; i < count; i++) {
            restTemplate.postForObject("/purchase", req, Result.class);
        }
    }
}
