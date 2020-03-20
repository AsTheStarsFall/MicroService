package com.tianhy.springbootrest;

import com.tianhy.springbootrest.pojo.User;
import com.tianhy.springbootrest.vo.UserVo;
import org.springframework.http.*;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * {@link}
 *
 * @Desc: RestTemplate应用
 * @Author: thy
 * @CreateTime: 2019/5/20
 **/
public class RestTemplateController {

    public static UserVo getUser(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        UserVo forObject = restTemplate.getForObject("http://localhost:8080/user{id}", UserVo.class, id);
        System.out.println(forObject);
        return forObject;
    }

    public static List<UserVo> findUser(String userName,
                                        String note, int start, int limit) {
        RestTemplate restTmpl = new RestTemplate();
        // 使用Map进行封装多个参数，以提高可读性
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userName", "user");
        params.put("note", "note");
        params.put("start", start);
        params.put("limit", limit);
        // Map中的key和URI中的参数一一对应
        String url = "http://localhost:8080/users/{userName}/{note}/{start}/{limit}";
        // 请求后端
        ResponseEntity<List> responseEntity = restTmpl.getForEntity(url, List.class, params);
        List<UserVo> userVoes = responseEntity.getBody();
        return userVoes;
    }


    //POST请求传递json请求体
    public static User insertUser(UserVo userVo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        //请求实体对象
        HttpEntity<UserVo> requert = new HttpEntity<>(userVo, headers);
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.postForObject("http://localhost:8080/user", requert, User.class);
        System.out.println(user);
        return user;
    }

    public User insertUserEntity(UserVo userVo) {
        //请求头
        HttpHeaders headers = new HttpHeaders();
        //请求类型
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        //绑定请求体和头
        HttpEntity<UserVo> request = new HttpEntity<>(userVo, headers);
        RestTemplate restTmpl = new RestTemplate();
        // 请求服务器
        ResponseEntity<User> userEntity = restTmpl.postForEntity(
                "http://localhost:8080/user2/entity", request, User.class);
        // 获取响应体
        User user = userEntity.getBody();
        // 获取响应头
        HttpHeaders respHeaders = userEntity.getHeaders();
        // 获取响应属性
        List<String> success = respHeaders.get("success");
        // 响应的HTTP状态码
        int status = userEntity.getStatusCodeValue();
        System.out.println(user.getId());
        return user;
    }

    public static User userExchange(UserVo userVo, Long id) {
        // 请求头
        HttpHeaders headers = new HttpHeaders();
        // 请求类型
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        // 绑定请求体和头
        HttpEntity<UserVo> request = new HttpEntity<>(userVo, headers);
        RestTemplate restTmpl = new RestTemplate();
        String url = "http://localhost:8080/user2/entity";
        // 请求服务器
        ResponseEntity<User> userEntity
                = restTmpl.exchange(url, HttpMethod.POST, request, User.class);
        // 获取响应体
        User user = userEntity.getBody();
        // 获取响应头
        HttpHeaders respHeaders = userEntity.getHeaders();
        // 响应头属性
        List<String> success = respHeaders.get("success");
        // 响应的HTTP状态码
        int status = userEntity.getStatusCodeValue();
        System.out.println(user.getId());
        // 修改URL获取资源
        url = "http://localhost:8080/user/{id}";
        // 传递URL地址参数
        ResponseEntity<UserVo> userVoEntity
                = restTmpl.exchange(url, HttpMethod.GET, null, UserVo.class, id);
        // 获取响应体
        UserVo userVo1 = userVoEntity.getBody();
        System.out.println(userVo1.getUserName());
        return user;
    }


    //HTTP Client 适配
    public static void restTemplates() {
        RestTemplate restTemplate = new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }


}
