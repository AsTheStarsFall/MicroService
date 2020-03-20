package com.tianhy.study.controller;

import com.tianhy.study.service.GzipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: thy
 * @Date: 2020/2/22 4:02
 * @Desc:
 */
@RestController
public class GzipFeignController {

    @Autowired
    private GzipService helloService;

    @GetMapping("/search/github")
    public ResponseEntity<byte[]> searchGithubRepoByStr(@RequestParam("str") String queryStr) {
        return helloService.searchRepo(queryStr);
    }
}
