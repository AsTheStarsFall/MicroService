package com.tianhy.springwebmvc.controller;

import com.tianhy.springwebmvc.model.Person;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Desc: Controller
 * @Author: thy
 * @CreateTime: 2019/4/9
 **/
@RestController
public class Controller {

    @GetMapping("/person/{id}")
    public Person person(@PathVariable Long id, @RequestParam(required = false) String name) {
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        return person;
    }

    @RequestMapping("/npe")
    public String npe() {
        throw new NullPointerException("故意抛异常！");
    }


    @PostMapping(value = "/person/json/to/properties",
            //响应自描述消息
            produces = "application/properties-person")
    public Person personJson2Properties(@RequestBody Person person) {
        return person;
    }


    @PostMapping(value = "/person/properties/to/json",
            consumes = "application/properties-person",
            //响应自描述消息
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Person personProperties2Json(@RequestBody Person person) { return person; }

    /**
     * ·
     * 404处理
     *
     * @param req
     * @return
     */
    @GetMapping("/404")
    public Map<String, Object> pageNotFound(HttpServletRequest req) {
        //servlet规定必须设定请求属性
        Map<String, Object> errs = new HashMap<>();
        errs.put("status_code", req.getAttribute("javax.servlet.error.status_code"));
        errs.put("requestUri", req.getAttribute("javax.servlet.error.request_uri"));
        return errs;
    }
}
