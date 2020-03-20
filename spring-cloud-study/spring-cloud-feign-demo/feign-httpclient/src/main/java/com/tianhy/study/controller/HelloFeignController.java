package com.tianhy.study.controller;

import com.tianhy.study.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: thy
 * @Date: 2020/2/22 4:02
 * @Desc:
 */
@RestController
public class HelloFeignController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/search/github")
    public String searchGithubRepoByStr(@RequestParam("str") String queryStr) {
        return helloService.searchRepo(queryStr);
    }
}
